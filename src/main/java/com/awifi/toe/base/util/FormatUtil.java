package com.awifi.toe.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.awifi.toe.system.util.SysConfigUtil;

/**
 * @Description: 格式化
 * @Title: FormatUtil.java
 * @Package com.awifi.toe.base.util
 * @author 许小满
 * @date 2015年6月29日 下午4:01:59
 * @version V1.0
 */
public class FormatUtil {

    /**
     * 将 用户类型 转换成 角色名称
     * 
     * @param userType
     *            用户类型: 1超级管理员、2 省级管理员(电信侧)、3 市级管理员(电信侧)、4 区县管理员(电信侧)、5 客户管理员(客户型)、6 集团管理员(电信侧)
     * @return 角色名称
     * @author 许小满
     * @date 2015年6月29日 下午4:03:08
     */
    public static String userTypeToRoleNameToE(Integer userType) {
        String roleName = null;
        switch (userType) {
            case 1:
                roleName = "ROLE_SUPER_ADMIN";
                break;
            case 2:
                roleName = "ROLE_D_PROVINCE";
                break;
            case 3:
                roleName = "ROLE_D_CITY";
                break;
            case 4:
                roleName = "ROLE_D_AREA";
                break;
            case 5:
                roleName = "ROLE_CUSTOMER_ADMIN ";
                break;
            case 6:
                roleName = "ROLE_GROUP_ADMIN ";
                break;    
            default:
                roleName = StringUtils.EMPTY;
        }
        return roleName;
    }

    /**
     * 将 用户类型 转换成 角色名称(显示)
     * 
     * @param userType
     *            用户类型: 1超级管理员、2 省级管理员(电信侧)、3 市级管理员(电信侧)、4 区县管理员(电信侧)、5 客户管理员(客户型)
     * @return 角色名称(显示)
     * @author 许小满
     * @date 2015年7月2日 上午11:31:11
     */
    public static String userTypeToRoleDspName(Integer userType) {
        String roleName = null;
        switch (userType) {
            case 1:
                roleName = "超级管理员";
                break;
            case 2:
                roleName = "省级管理员";
                break;
            case 3:
                roleName = "市级管理员";
                break;
            case 4:
                roleName = "区县管理员";
                break;
            case 5:
                roleName = "客户管理员";
                break;
            case 6:
                roleName = "集团管理员";
                break;
            default:
                roleName = "访客";
        }
        return roleName;
    }

    
    /**
     * 将静态用户类型转化成角色名称
     * @param userType 用户类型
     * @return 角色名称  1普通员工、2 VIP客户、3 终端体验区
     * @author ZhouYing 
     * @date 2015年12月10日 上午9:25:37
     */
    public static String staticUserTypeToRoleDspName(Integer userType) {
        String roleName = null;
        switch (userType) {
            case 1:
                roleName = "普通员工";
                break;
            case 2:
                roleName = "VIP客户";
                break;
            case 3:
                roleName = "终端体验区";
                break;
            default:
                roleName = StringUtils.EMPTY;
        }
        return roleName;
    }
    
    /**
     * 将 用户类型 转换成 角色名称
     * 
     * @param userType
     *            用户类型: 0 中心管理员、1 客户方管理员、2 正式员工、3 临时员工
     * @return 角色名称
     * @author 许小满
     * @date 2015年6月29日 下午4:03:08
     */
    public static String userTypeToRoleName(Integer userType) {
        String roleName = null;
        switch (userType) {
            case 0:
                roleName = "ROLE_SUPER_ADMIN";
                break;
            case 1:
                roleName = "ROLE_PROVINCE";
                break;
            case 2:
                roleName = "ROLE_CITY";
                break;
            case 3:
                roleName = "ROLE_AREA";
                break;
            case 4:
                roleName = "ROLE_SALE_DEPT";
                break;
            case 5:
                roleName = "ROLE_SALE_SITE";
                break;
            default:
                roleName = StringUtils.EMPTY;
        }
        return roleName;
    }

    /**
     * 将数据中心得到的设备类型的数字转换成汉字
     * @param deviceType 设备类型
     * @return 角色名称(显示)
     * @author 牛华凤
     * @date 2015年9月28日 下午4:05:14
     */
    public static String deviceTypeToExplain(Integer deviceType) {
        String deviceTypeName = null;
        switch (deviceType) {
            case 11:
                deviceTypeName = "AWiFi-BRAS";
                break;
            case 12:
                deviceTypeName = "AWiFi-AC";
                break;
            case 13:
                deviceTypeName = "AWiFi-PFitAP 项目型瘦AP";
                break;
            case 14:
                deviceTypeName = "AWiFi-FatAP";
                break;
            case 15:
                deviceTypeName = "AWiFi-GPON 有线光猫";
                break;
            case 16:
                deviceTypeName = "AWiFi-WGPON 无线光猫";
                break;
            case 18:
                deviceTypeName = "AWiFi-HFitAP 热点下瘦AP";
                break;
            case 22:
                deviceTypeName = "chinaNet-AC";
                break;
            case 24:
                deviceTypeName = "chinaNet-AP";
                break;
            default:
                deviceTypeName = StringUtils.EMPTY;
        }
        return deviceTypeName;
    }

    /**
     * 将 从数据中心得到的设备状态数字转换成汉字
     * @param deviceStatus 设备状态
     * @return 角色名称
     * @author 许小满
     * @date 2015年9月28日 下午4:03:08
     */
    public static String deviceStatusToExplain(Integer deviceStatus) {
        String deviceStatusName = null;
        switch (deviceStatus) {
            case 1:
                deviceStatusName = "正常";
                break;
            case 2:
                deviceStatusName = "锁定/冻结";
                break;
            case 9:
                deviceStatusName = "作废/注销";
                break;
            default:
                deviceStatusName = StringUtils.EMPTY;
        }
        return deviceStatusName;
    }

    /**
     * 格式化 请求参数
     * @param request 请求
     * @return json
     * @author 许小满
     * @date 2015年7月21日 下午6:53:52
     */
    public static String formatRequestParam(HttpServletRequest request) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parameter", request.getParameterMap());// 参数
        param.put("method", request.getMethod());// 请求的方法
        param.put("protocol", request.getProtocol());// 协议
        param.put("cookies", request.getCookies());// 跟踪
        param.put("url", request.getHeader("referer"));// 请求的地址
        return JsonUtil.toJson(param);
    }

    /**
     * 手机号加密
     * 
     * @param cellphone 手机号
     * @return 加密后的手机号
     * @author 亢燕翔
     * @date 2015年9月2日 上午9:54:34
     */
    public static String formatCellphone(String cellphone) {
        String cellphoneEncry = SysConfigUtil.getParamValue("cellphone.encryption");
        if (cellphoneEncry.equals("yes")) {
            return cellphone.substring(0, 3) + "****" + cellphone.substring(7);
        }
        if (cellphoneEncry.equals("no")) {
            return cellphone;
        }
        return cellphone.substring(0, 3) + "****" + cellphone.substring(7);
    }

    /**
     * APMAC格式化（00-08-D2-ED-D0-F0）
     * @param apMacs apmac
     * @return 格式化后的mac
     * @author 亢燕翔
     * @date 2015年10月26日 下午4:12:47
     */
    public static String formatApMac(String apMacs) {
        String apMac = apMacs.toUpperCase();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < apMac.length(); i += 2) {
            if (i < apMac.length() - 2) {
                sb.append(apMac.substring(i, i + 2)).append("-");
            } else {
                sb.append(apMac.substring(i, i + 2));
            }
        }
        return sb.toString();
    }

    /**
     * 给多个逗号分割的mac加 "-"
     * 
     * @param macs macs
     * @return 格式化后的macs
     * @author 郭海勇
     * @date 2015年10月30日 上午9:29:00
     */
    public static String macControl(String macs) {
        macs = macs.toUpperCase();
        String[] apmacs = macs.split(",");
        StringBuffer sb = new StringBuffer();
        String mac = StringUtils.EMPTY;
        for (int i = 0; i < apmacs.length; i++) {
            mac = apmacs[i];
            for (int j = 0; j < mac.length(); j += 2) {
                sb.append(mac.substring(j, j + 2));
                if (j < mac.length() - 2) {
                    sb.append("-");
                }
            }
            if (i < apmacs.length - 1) {
                sb.append(",");
            }

        }
        return sb.toString();
    }
    
    /**
     * @param map 去空值
     * @return 接口
     * @author 郭海勇  
     * @date 2015年12月1日 下午4:25:47
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map removeNullMap(Map map){
        Iterator iter = map.keySet().iterator(); 
        List array = new ArrayList();
        Object key = null;
        Object val = null;
        while (iter.hasNext()) { 
            key = iter.next(); 
            val = map.get(key); 
            if(StringUtils.EMPTY.equals(val) || null == val){
                array.add(key);
            }
        } 
        for (int i = 0; i < array.size(); i++) {
            map.remove(array.get(i));
        }
        return map;
    }
    
    /**将map中的double类型转为String类型
     * @param map 需要转化的map
     * @return result
     * @author 郭海勇  
     * @date 2016年5月16日 下午12:18:49
     */
    public static Map<String,String> transfMap(Map<String,Object> map){
        Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
        Map<String,String> result = new HashMap<String,String>();
        while (iter.hasNext()) {
            Entry<String, Object> entry = (Entry<String, Object>) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            if(val instanceof Double){
                result.put(key.toString(), ((Double)val).longValue()+StringUtils.EMPTY);
            }else{
                result.put(key.toString(), val.toString());
            }
        }
        return result;
    }
    
    /**判读是不是mac
     * @param mac 物理mac地址
     * @return boolean
     * @author 郭海勇  
     * @date 2015年12月8日 下午12:38:49
     */
    public static boolean isMac(String mac) {
        if(StringUtils.isEmpty(mac)){
            return false;
        }
        mac = mac.replace("-", StringUtils.EMPTY);
        String patternMac="^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})$";
        Pattern pa= Pattern.compile(patternMac);
        return pa.matcher(mac).find();
    }

    /**
     * 将皮肤策略类型转换成中文
     * @param themeType 皮肤策略类型
     * @return 类型名称  1、域名   2、编号
     * @author 牛华凤  
     * @date 2016年4月21日 下午4:26:45
     */
    public static String formatThemeType(Integer themeType) {
        String roleName = null;
        switch (themeType) {
            case 1:
                roleName = "域名";
                break;
            case 2:
                roleName = "编号";
                break;
            default:
                roleName = StringUtils.EMPTY;
        }
        return roleName;
    }

    /**
     * 将热点地图设备类型转换成中文
     * @param type 皮肤策略类型
     * @return 类型名称  1、域名   2、编号
     * @author 牛华凤  
     * @date 2016年4月21日 下午4:26:45
     */
    public static String formatHeatmapDeviceName(Integer type) {
        String roleName = null;
        switch (type) {
            case 1:
                roleName = "瘦AP";
                break;
            case 2:
                roleName = "胖AP";
                break;
            case 3:
                roleName = "光猫";
                break;
            default:
                roleName = StringUtils.EMPTY;
        }
        return roleName;
    }

    /**
     * 无感知状态转化
     * @param status 状态
     * @return 1 开启 2关闭
     * @author ZhouYing 
     * @date 2016年4月29日 下午2:41:50
     */
    public static String statusToDsp(String status) {
        String roleName = null;
        switch (status) {
            case "1":
                roleName = "开启";
                break;
            case "2":
                roleName = "关闭";
                break;
            case "9":
                roleName = "失效";
                break;
            default:
                roleName = StringUtils.EMPTY;
        }
        return roleName;
    }

    /**
     * 格式化设备名称
     * @param entityType 实体名称
     * @param apMac APMAC
     * @param ssid SSID
     * @param entityName 实体名称
     * @return 设备名称
     * @author kangyanxiang 
     * @param hotareaName 
     * @date 2016年5月16日 下午4:55:54
     */
    public static String entityTypeToDeviceName(String entityType, String apMac, String ssid, String entityName, String hotareaName) {
        if ("胖AP".equals(entityType) && StringUtils.isNotBlank(apMac)) {
            return apMac;
        } else if ("瘦AP".equals(entityType)) {
            if (StringUtils.isBlank(apMac)) {
                return ssid;
            } else if (StringUtils.isBlank(ssid)) {
                return apMac;
            } else {
                return apMac + "_" + ssid;
            }
        } else if ("AC".equals(entityType) && StringUtils.isNotBlank(entityName)) {
            return entityName;
        } else if ("BAS".equals(entityType) && StringUtils.isNotBlank(entityName)) {
            return entityName;
        } else if ("GPON".equals(entityType) && StringUtils.isNotBlank(entityName)) {
            return entityName;
        } else if ("热点".equals(entityType)) {
            return hotareaName;
        } else if ("IPTV盒子".equals(entityType) && StringUtils.isNotBlank(entityName)) {
            return entityName;
        } else if ("EPON".equals(entityType) && StringUtils.isNotBlank(entityName)) {
            return entityName;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 将用户认证类型转化成汉字
     * @param authType 用户认证类型
     * @return 用户认证类型  OLD_USER:免认证用户
     *                   GREEN_USER:无感知认证
     *                   MOBILE_SMS:手机号验证码
     *                   ACCOUNT_MOBILE:账号认证
     *                   ACCOUNT_NAME:账号认证
     *                   MOBILE_VOICE:语音认证
     * @author 牛华凤  
     * @date 2016年5月16日 下午4:26:37
     */
    public static String staticAuthTypeToRoleDspName(String authType) {
        String authTypeDsp = null;
        if("OLD_USER".equals(authType)){
            authTypeDsp = "免认证用户";
        } else if("GREEN_USER".equals(authType)){
            authTypeDsp = "无感知认证";
        } else if("MOBILE_SMS".equals(authType)){
            authTypeDsp = "手机号验证码认证";
        } else if("ACCOUNT_MOBILE".equals(authType)){//账号认证
            authTypeDsp = "手机号密码认证";
        } else if("ACCOUNT_NAME".equals(authType)){//账号认证
            authTypeDsp = "用户名密码认证";
        } else if("MOBILE_VOICE".equals(authType)){
            authTypeDsp = "语音认证";
        }
        return authTypeDsp;
    }

    /**
     * 将认证类型转换成中文
     * @param authType 认证类型  OLD_USER:免认证用户
     *                   GREEN_USER:无感知认证
     *                   MOBILE_SMS:手机号验证码
     *                   ACCOUNT_MOBILE:账号认证
     *                   ACCOUNT_NAME:账号认证
     *                   MOBILE_VOICE:语音认证
     * @return 认证类型
     * @author 牛华凤  
     * @date 2016年5月17日 上午10:02:54
     */
    public static String authTypeToRoleDspName(String authType){
        String authTypeDsp = null;
        if("OLD_USER".equals(authType)){
            authTypeDsp = "免认证用户";
        } else if("GREEN_USER".equals(authType)){
            authTypeDsp = "无感知认证";
        } else if("MOBILE_SMS".equals(authType)){
            authTypeDsp = "手机号验证码认证";
        } else if("ACCOUNT_MOBILE".equals(authType)){
            authTypeDsp = "手机号密码认证";
        } else if("ACCOUNT_NAME".equals(authType)){
            authTypeDsp = "用户名密码认证";
        } else if("MOBILE_VOICE".equals(authType)){
            authTypeDsp = "语音认证";
        }
        return authTypeDsp;
    }

    /**
     * 销售点二级分类转换
     * @param storeType 类型
     * @return 中文
     * @author ZhouYing 
     * @date 2016年5月27日 下午5:08:02
     */
    public static String storeTypeDsp(String storeType) {
        if(StringUtils.isEmpty(storeType)){
            return StringUtils.EMPTY;
        }
        String storeTypeDsp = null;
        switch (storeType) {
            case "1":
                storeTypeDsp = "专营店";
                break;
            case "2":
                storeTypeDsp = "自有厅";
                break;
            case "3":
                storeTypeDsp = "其他";
                break;
            default:
                storeTypeDsp = StringUtils.EMPTY;
        }
        return storeTypeDsp;
    }

    /**
     * 自有厅级别
     * @param storeLevel 自有厅级别
     * @return 显示
     * @author ZhouYing 
     * @date 2016年5月27日 下午5:16:55
     */
    public static String storeLevelDsp(String storeLevel) {
        if(StringUtils.isEmpty(storeLevel)){
            return StringUtils.EMPTY;
        }
        String storeLevelDsp = null;
        switch (storeLevel) {
            case "1":
                storeLevelDsp = "1级";
                break;
            case "2":
                storeLevelDsp = "2级";
                break;
            case "3":
                storeLevelDsp = "3级";
                break;
            case "4":
                storeLevelDsp = "4级";
                break;
            case "5":
                storeLevelDsp = "5级";
                break;
            default:
                storeLevelDsp = StringUtils.EMPTY;
        }
        return storeLevelDsp;
    }

    /**
     * 接入方式转换
     * @param connectType 接入方式
     * @return 显示
     * @author ZhouYing 
     * @date 2016年5月27日 下午5:31:09
     */
    public static String connectTypeDsp(String connectType) {
        if(StringUtils.isEmpty(connectType)){
            return StringUtils.EMPTY;
        }
        String connectTypeDsp = null;
        if("chinanet".equals(connectType)){
            connectTypeDsp = "Chinanet接入";
        } else if("fatAp".equals(connectType)){
            connectTypeDsp = "胖AP接入";
        } else if(connectType.length()>=7 && "optical".equals(connectType.substring(0, 7))){
            connectTypeDsp = "定制光猫接入";
        } else if("others".equals(connectType)){
            connectTypeDsp = "其他";
        }
        return connectTypeDsp;
    }

    /**
     * 专营店星级
     * @param storeStar 专营店级别
     * @return 显示
     * @author ZhouYing 
     * @date 2016年5月27日 下午5:39:35
     */
    public static String storeStarDsp(String storeStar) {
        String storeStarDsp = null;
        switch (storeStar) {
            case "1":
                storeStarDsp = "1星";
                break;
            case "2":
                storeStarDsp = "2星";
                break;
            case "3":
                storeStarDsp = "3星";
                break;
            case "4":
                storeStarDsp = "4星";
                break;
            case "5":
                storeStarDsp = "5星";
                break;
            default:
                storeStarDsp = "其他";
        }
        return storeStarDsp;
    }
    
    /**
     * 专营店类别
     * @param beCommStore 类别
     * @return name
     * @author kangyanxiang 
     * @date 2016年5月30日 下午4:09:01
     */
    public static String  beCommStoreDsp(String beCommStore) {
        if(StringUtils.isEmpty(beCommStore)){
            return StringUtils.EMPTY;
        }
        String beCommStoreDsp = null;
        switch (beCommStore) {
            case "1":
                beCommStoreDsp = "社区店";
                break;
            case "2":
                beCommStoreDsp = "商圈店";
                break;
            case "3":
                beCommStoreDsp = "旗舰店";
                break;
            case "4":
                beCommStoreDsp = "其他";
                break;
            default:
                beCommStoreDsp = "其他";
        }
        return beCommStoreDsp;
    }

    /**
     * 格式化设备实体类型
     * @param entityTypeStr 设备类型名称
     * @return 设备编号
     * @author kangyanxiang 
     * @date 2016年8月11日 下午5:21:53
     */
    public static String formatEntityType(String entityTypeStr) {
        if("HOT_AP".equals(entityTypeStr)){
            return "H";
        }else if("BAS".equals(entityTypeStr)){
            return "11";
        }else if("AC".equals(entityTypeStr)){
            return "21";
        }else if("FAT_AP".equals(entityTypeStr)){
            return "31";
        }else if("GPON".equals(entityTypeStr)){
            return "32,33";
        }else if("EPON".equals(entityTypeStr)){
            return "34,35";
        }else if("FIT_AP".equals(entityTypeStr)){
            return "41,42,43";
        }else if("other".equals(entityTypeStr)){
            return "36,37";
        }else {
            return null;
        }
    }
    
}