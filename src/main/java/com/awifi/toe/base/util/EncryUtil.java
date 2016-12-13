package com.awifi.toe.base.util;

import java.net.URLDecoder;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DigestUtils;

import com.awifi.toe.base.constants.Constants;

/**
 * @Description: 加密/解密 工具类
 * @Title: EncryUtil.java
 * @Package com.awifi.toe.base.util
 * @author niuhuafeng
 * @date 2015年7月3日 下午2:29:21
 * @version V1.0
 */
public class EncryUtil {

    /** 日志 */
    private static final Log logger = LogFactory.getLog(EncryUtil.class);
    
    /**
     * 加密算法
     * @param data 加密数据
     * @param key 秘钥
     * @return 加密结果
     */
    public static byte[] desEnCryt(byte[] data, byte[] key) {
        byte[] result = null;
        try {
            SecureRandom sr = new SecureRandom();
            SecretKeyFactory keyFactory;
            DESKeySpec dks = new DESKeySpec(key);
            keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(dks);
            // 创建Cipher对象
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            // 初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, sr);
            // 加解密
            result = cipher.doFinal(data);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return result;
    }

    /**
     * 解密算法
     * @param data 解密数据
     * @param key 秘钥
     * @return 解密结果
     */
    public static byte[] desDeCryt(byte[] data, byte[] key) {
        byte[] result = null;
        try {
            SecureRandom sr = new SecureRandom();
            SecretKeyFactory keyFactory;
            DESKeySpec dks = new DESKeySpec(key);
            keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretkey, sr);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return result;
    }

    /**
     * DES加密方法
     * @param message 加密数据
     * @param keyString 密钥
     * @return 加密结果
     */
    public static String encryptByDes(String message, String keyString) {
        try {
            if(StringUtils.isEmpty(message) || StringUtils.isEmpty(keyString)){//当 加密数据或密钥 为空时，不做加密操作
                return StringUtils.EMPTY;
            }
            String keyHexString = stringToHexString(keyString);
            byte[] key = hexStringToBytes(keyHexString);
            String dataHexString = stringToHexString(message);
            byte[] data = hexStringToBytes(dataHexString);
            SecureRandom sr = new SecureRandom();
            SecretKeyFactory keyFactory;
            DESKeySpec dks = new DESKeySpec(key);
            keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, sr);
            byte[] result = cipher.doFinal(data);
            return new String(Base64.encodeBase64(result));
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return StringUtils.EMPTY;
    }
    

    /**
     * DES解密方法
     * @param dataHexString 解密数据
     * @param keyString 密钥
     * @return 解密后的值 
     */
    public static String decryptByDes(String dataHexString, String keyString) {
        if(StringUtils.isEmpty(dataHexString) || StringUtils.isEmpty(keyString)){//当 解密数据或密钥 为空时，不做加密操作
            return StringUtils.EMPTY;
        }
        String keyHexString = stringToHexString(keyString);
        byte[] key = hexStringToBytes(keyHexString);
        byte[] result = null;
        try {
            SecureRandom sr = new SecureRandom();
            SecretKeyFactory keyFactory;
            DESKeySpec dks = new DESKeySpec(key);
            keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretkey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretkey, sr);
            result = cipher.doFinal(Base64.decodeBase64(dataHexString));
            return new String(result).trim();
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return null;
    }
    

    /**
     * 字符串转16进制字符串
     * @param str 字符串
     * @return 转后的字符串
     */
    public static String stringToHexString(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转成byte数组
     * @param hexString 字符串
     * @return 转后的字符串
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        int hexLength = length;
        while (hexLength % 8 != 0) {
            hexLength++;
        }
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[hexLength];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 
     * @param c 字符
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 16进制字符串转字符串
     * @param str 字符串
     * @return 字符串
     */
    public static String hexStringToString(String str) {
        byte[] baKeyword = new byte[str.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                ErrorUtil.printException(e, logger);
            }
        }
        try {
            str = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return str;
    }

    /**
     * MD5加密
     * @param str 字符串
     * @return 加密后的字符串
     * @author 许小满
     * @date 2015年7月8日 下午4:50:39
     */
    public static String getMd5Str(String str) {
        if (str == null){
            return StringUtils.EMPTY;
        }
        byte[] pb = null;
        try {
            pb = str.getBytes("utf-8");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return DigestUtils.md5DigestAsHex(pb);
    }
    
    /**
     * 根据传入的参数计算token
     * @param secretKey 秘钥
     * @param timestamp 时间戳
     * @return  token
     * @author 许小满  
     * @date 2015年11月30日 下午3:06:59
     */
    public static String generateToken(String secretKey, String timestamp) {
        String result = secretKey + "_" + timestamp;
        return getMd5Str(result);
    }
    
    /**
     * 根据传入的参数计算token
     * @param appId 应用id
     * @param appKey 应用密钥
     * @param timestamp 时间戳
     * @return  token
     * @author 许小满  
     * @date 2016年3月29日 下午4:23:25
     */
    public static String generateAppToken(String appId, String appKey, String timestamp) {
        String result = appId + "_" + appKey + "_" + timestamp;
        return getMd5Str(result);
    }
    
    /**
     * 判断是否已经超时
     * @param timestamp 时间戳（秒）
     * @return true 超时、false 未超时
     * @author 许小满  
     * @date 2015年6月8日 上午10:02:28
     */
    public static boolean isTimeout(String timestamp){
        long currentTime = System.currentTimeMillis() / 1000;//当前时间(秒)
        if(Math.abs(Long.parseLong(timestamp) - currentTime) > Constants.API_TOKEN_TIMEOUT) {
            return true;
        }
        return false;
    }
    
    /**
     * 参数解密
     * @param paramValue 参数值
     * @param appKey 密钥
     * @return 处理后的值
     * @throws Exception 异常
     * @author 许小满  
     * @date 2016年9月18日 下午4:53:31
     */
    public static String paramDes(String paramValue, String appKey){
        try{
            if(StringUtils.isBlank(paramValue)){
                logger.debug("错误：paramValue 为空！");
                return StringUtils.EMPTY;
            }
            //1. paramValue 进行解密
            String paramValueDec = EncryUtil.decryptByDes(paramValue, appKey);
            //1.1 paramValue 解密成功直接返回
            if(StringUtils.isNotBlank(paramValueDec)){
                return paramValueDec;
            }
            //1.2 paramValue 解密失败，URLDecoder.decode后再进行解密
            logger.debug("提示：paramValue["+paramValue+"] 解密失败，尝试转码后再次进行解密.");
            //1.2.1 参数转码
            String paramValueCode = URLDecoder.decode(paramValue, "UTF-8");
            //1.2.2 参数转码后，再次解密
            paramValueDec = EncryUtil.decryptByDes(paramValueCode, appKey);
            //1.2.2.1 参数转码后，解密成功
            if(StringUtils.isNotBlank(paramValueDec)){
                return paramValueDec;
            }
            //1.2.2.2 参数转码后，解密失败
            logger.debug("提示：paramValueDec["+paramValueDec+"] 解密失败.");
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 测试函数
     * @param args 参数
     * @author 许小满  
     * @date 2015年11月30日 下午3:02:07
     */
    public static void main(String[] args) {
        // md5加密
        //String str = "awifi123";
        //System.out.println(EncryUtil.getMd5Str(str));
        // des 加密
        String merchantId = "187938";
        String merchantOpenId = EncryUtil.encryptByDes(merchantId, "28e57302167d");
        System.out.println("加密：merchantOpenId= " + merchantOpenId);

        // des 解密
        //String merchantOpenId1 = "d4ab492b33804715";
        //String merchantid = EncryUtil.decryptByDes(merchantOpenId1,"28e57302167d");
        //System.out.println("解密：merchantid= " + merchantid);
        
        
        //token 秘钥
        /*String secretKey = UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY).toUpperCase();
        System.out.println(secretKey);*/
        
        //应用 id、key 
        /**/
        //String appid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        //String appkey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);
        //System.out.println("appid= " + appid);
        //System.out.println("appkey= " + appkey);
        
        /*
        String secretKey = "B8221988ECAB410389C70C5B7D6B215A";
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String token = EncryUtil.generateToken(secretKey, timestamp);
        System.out.println("secretKey = " + secretKey);
        System.out.println("timestamp = " + timestamp);
        System.out.println("token = " + token);
        System.out.println("token=" + token + "&timestamp=" + timestamp);
       */
        /* eapi */
        String appId = "af93510d";
        String appKey = "2e76277aae45";
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String token = EncryUtil.generateAppToken(appId, appKey, timestamp);
        System.out.println("token=" + token + "&timestamp=" + timestamp + "&appid=" + appId);
        
    }
    
}