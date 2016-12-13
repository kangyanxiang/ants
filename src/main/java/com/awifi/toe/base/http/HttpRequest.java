package com.awifi.toe.base.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.awifi.toe.base.util.ErrorUtil;

public class HttpRequest {

    /** 日志 */
    private static Log logger = LogFactory.getLog(HttpRequest.class);
    
    /** 连接延时 */
    private static final int CONN_TIMEOUT = 300000;//5分钟
    
    /** utf-8编码 */
    private static final String ENCODE_UTF8 = "UTF-8";
    
    /** 请求方式 GET */
    public static final String METHOD_GET = "GET";
    
    /** 请求方式 POST*/
    public static final String METHOD_POST = "POST";

    

    /**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @return 接口返回值
     */
    public static ByteBuffer sendGetRequest(String path, Map<String, String> params){
        return sendGetRequest(path, params, null);
    }
    /**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @param timeout 超时时间
     * @return 接口返回值
     */
    public static ByteBuffer sendGetRequest(String path, Map<String, String> params, Integer timeout){
        long beginTime = System.currentTimeMillis();
        String url = null;
        try {
            url = getURL(path, params);
            return sendGetRequest(url, timeout);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }finally{
            if(url != null){
                logger.debug("提示：接口  "+ url +" ,共花费了 "+ (System.currentTimeMillis() - beginTime) +" ms.");
            }
        }
        return null;
    }
    
    /**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 接口参数
     * @return 接口返回值
     * @throws Exception 异常
     */
    public static ByteBuffer sendGetRequest(String path, String params){
        return sendGetRequest(path, params, null);
    }
    
    /**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 接口参数
     * @param timeout 超时时间
     * @return 接口返回值
     * @throws Exception 异常
     */
    public static ByteBuffer sendGetRequest(String path, String params, Integer timeout){
        long beginTime = System.currentTimeMillis();
        String url = null;
        try {
            url = getURL(path, params);
            return sendGetRequest(url, timeout);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }finally{
            if(url != null){
                logger.debug("提示：接口  "+ url +" ,共花费了 "+ (System.currentTimeMillis() - beginTime) +" ms.");
            }
        }
        return null;
    }

    /**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param timeout 超时时间
     * @return 接口返回值
     * @throws Exception 异常
     */
    public static ByteBuffer sendGetRequest(String path, Integer timeout) throws Exception{
        int connTimeout = timeout != null ? timeout : CONN_TIMEOUT;//接口超时时间
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 默认情况下是false;
        conn.setDoOutput(false);
        // 设置是否从httpUrlConnection读入，默认情况下是true
        conn.setDoInput(true);
        // Get 请求不能使用缓存
        conn.setUseCaches(false);
        // 设定请求的方法为"GET"，默认是GET
        conn.setRequestMethod(METHOD_GET);
        // 设置 连接主机超时（单位：毫秒）
        conn.setConnectTimeout(connTimeout);
        // 设置从主机读取数据超时（单位：毫秒）
        conn.setReadTimeout(connTimeout);
        return parseInputStream(conn.getInputStream());
    }
    
    /**
     * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @return 接口返回值
     * @throws Exception 异常
     */
    public static ByteBuffer sendPostRequestReturnByte(String path, Map<String, String> params){
        return sendPostRequestReturnByte(path, params, null);
    }
    
    /**
     * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @return 接口返回值
     * @throws Exception 异常
     */
    public static ByteBuffer sendPostRequestReturnByte(String path, String params){
        return sendPostRequestReturnByte(path, params, null);
    }
    
    /**
     * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @param timeout 超时时间
     * @return 接口返回值
     * @throws Exception 异常
     */
    public static ByteBuffer sendPostRequestReturnByte(String path, Map<String, String> params, Integer timeout){
        try {
            String requestParam = getParams(params);//请求参数
            return sendPostRequestReturnByte(path, requestParam, timeout);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return null;
    }
    
    /**
     * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @param timeout 超时时间
     * @return 接口返回值
     * @throws Exception 异常
     */
    public static ByteBuffer sendPostRequestReturnByte(String path, String params, Integer timeout){
        InputStream is = null;
        ByteBuffer byteBuffer = null;
        try{
            is = sendPostRequest(path, params, timeout);
            byteBuffer = parseInputStream(is);
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
        }finally{
            if(is != null){
                try{
                    is.close();//关闭io流
                }catch(Exception e1){};
            }
        }
        return byteBuffer;
    }

    /**
     * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @return 接口返回值
     */
    public static InputStream sendPostRequest(String path, Map<String, String> params){
        return sendPostRequest(path, params, null);
    }
    
    /**
     * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @param timeout 超时时间
     * @return 接口返回值
     */
    public static InputStream sendPostRequest(String path, Map<String, String> params, Integer timeout){
        try {
            String requestParam = getParams(params);//请求参数
            return sendPostRequest(path, requestParam, timeout);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return null;
    }
    
    /**
     * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @return 接口返回值
     */
    public static InputStream sendPostRequest(String path, String params){
        return sendPostRequest(path, params, null);
    }
    
    /**
     * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
     * @param path url
     * @param params 参数
     * @param timeout 超时时间
     * @return 接口返回值
     */
    public static InputStream sendPostRequest(String path, String params, Integer timeout){
        long beginTime = System.currentTimeMillis();
        
        try{
            int connTimeout = timeout != null ? timeout : CONN_TIMEOUT;
            int index = path.indexOf("?");
            if (index > -1) {
                path = path.substring(0, index);
            }
            byte[] paramData = params.getBytes();
            
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设定请求的方法为"POST"，默认是GET
            conn.setRequestMethod(METHOD_POST);
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   
            // http正文内，因此需要设为true, 默认情况下是false;
            conn.setDoOutput(true);
            // 设定传送的内容类型
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(paramData.length));
            // Post 请求不能使用缓存   
            conn.setUseCaches(false);
            // 设置 连接主机超时（单位：毫秒）
            conn.setConnectTimeout(connTimeout);
            // 设置从主机读取数据超时（单位：毫秒）
            conn.setReadTimeout(connTimeout);

            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(paramData);
            outputStream.flush();
            outputStream.close();

            //logger.debug("http response code ("+ conn.getResponseCode() +")...");
            if (conn.getResponseCode() == 200) {
                return conn.getInputStream();
            } else {
                ByteBuffer buff = parseInputStream(conn.getErrorStream());
                logger.error("http request error. " + new String(buff.array(), ENCODE_UTF8));
            }
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
        }finally{
            logger.debug("提示：接口地址（"+path+"），接口参数（"+ params +"）， 共花费了 "+ (System.currentTimeMillis() - beginTime) +" ms.");
        }
        return null;
    }
    
    /**
     * 将inputStream 读取 成 ByteBuffer。
     * @param is inputStream
     * @return 接口返回值
     * @throws Exception 异常
     */
    public static ByteBuffer parseInputStream(InputStream is) throws Exception{
        if(is == null){
            return null;
        }
        ByteArrayOutputStream bos = null;
        ByteBuffer byteBuffer = null;
        try{
            bos = new ByteArrayOutputStream();
            byte[] byts = new byte[1024];
            int len = 0;
            while ((len = is.read(byts)) >= 0){
                bos.write(byts, 0, len);
            }
            byteBuffer = ByteBuffer.wrap(bos.toByteArray());
        }catch(Exception e){
            throw e;
        }finally{
            try{
                is.close();
                bos.close();
            }catch(Exception e1){}
        }
        return byteBuffer;
    }
    
    
    /**
     * 获取参数
     * @param paramMap 参数map
     * @return 参数
     * @throws Exception 异常
     * @author 许小满  
     * @date 2016年3月23日 上午8:49:14
     */
    public static String getParams(Map<String, String> paramMap)throws Exception{
        if(paramMap == null || paramMap.size()<= 0){
            return StringUtils.EMPTY;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            String value = StringUtils.defaultString(entry.getValue());
            String key = entry.getKey();
            params.append(key).append('=').append(URLEncoder.encode(value, ENCODE_UTF8)).append('&');
        }
        params.deleteCharAt(params.length() - 1);
        return params.toString();
    }
    
    /**
     * 获取url
     * @param path 接口地址
     * @param paramMap 接口参数
     * @return 完整的接口地址
     * @throws Exception 异常
     * @author 许小满  
     * @date 2016年3月22日 下午11:42:47
     */
    public static String getURL(String path, Map<String, String> paramMap) throws Exception{
        String param = getParams(paramMap);
        String interfaceURL = (path.indexOf("?") > -1) ? path + "&" + param : path + "?" + param;
        return interfaceURL;
    }
    
    /**
     * 获取url
     * @param path 接口地址
     * @param params 接口参数
     * @return 完整的接口地址
     * @throws Exception 异常
     * @author 许小满  
     * @date 2016年3月22日 下午11:42:47
     */
    public static String getURL(String path, String params) throws Exception{
        String interfaceURL = (path.indexOf("?") > -1) ? path + "&" + params : path + "?" + params;
        return interfaceURL;
    }

}
