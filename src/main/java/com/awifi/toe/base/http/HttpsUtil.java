package com.awifi.toe.base.http;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;

/**
 * @Description:
 * @Title: HttpsUtil.java
 * @Package com.awifi.toe.base.util
 * @author kangyanxiang
 * @date Nov 14, 2015 11:56:49 PM
 * @version V1.0
 */
public class HttpsUtil {

    /** 编码 */
    private static final String DEFAULT_CHARSET = "UTF-8";
    /** 连接超时 */
    private static final int CONNECT_TIME_OUT = 15000;
    /** 读超时 */
    private static final int READ_TIME_OUT = 15000;

    /**
     * 发送post请求
     * @param path 接口地址
     * @param paramMap 参数
     * @throws Exception 异常
     * @return 接口返回值
     * @author 许小满
     * @date 2015年11月16日 下午3:23:41
     */
    public static String doPost(String path, Map<String, String> paramMap) throws Exception {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
            SSLContext.setDefault(sslContext);
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            // SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(path);
            // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
            conn = (HttpsURLConnection) url.openConnection();
            // conn.setSSLSocketFactory(ssf);
            // 默认情况下是false;
            conn.setDoOutput(true);
            // 设定请求的方法为"GET"，默认是GET
            conn.setRequestMethod("POST");
            // 不使用缓存
            conn.setUseCaches(false);

            String params = formatParam(paramMap);
            byte[] paramBytes = params.getBytes(DEFAULT_CHARSET);
            int paramLength = paramBytes != null ? paramBytes.length : 0;

            conn.setRequestProperty("Content-Length", String.valueOf(paramLength));

            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            conn.setConnectTimeout(CONNECT_TIME_OUT);
            conn.setReadTimeout(READ_TIME_OUT);
            conn.connect();
            // 传递参数
            out = conn.getOutputStream();
            out.write(paramBytes);
            out.flush();

            ByteBuffer byteBuffer = parseInputStream(conn.getInputStream());
            rsp = byteBuffer != null ? new String(byteBuffer.array(), DEFAULT_CHARSET) : null;
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rsp;
    }

    /**
     * 参数格式化
     * @param paramMap 参数
     * @return 格式化后的参数
     * @throws Exception 异常
     * @author 许小满  
     * @date Nov 15, 2015 1:48:28 AM
     */
    public static String formatParam(Map<String, String> paramMap) throws Exception {
        int maxSize = paramMap != null ? paramMap.size() : 0;
        if (maxSize <= 0) {
            return StringUtils.EMPTY;
        }
        StringBuilder param = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            param.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), DEFAULT_CHARSET));
            if (i < (maxSize - 1)) {
                param.append('&');
            }
            i++;
        }
        return param.toString();
    }

    /**
     * 
     * @author 许小满 2015年11月16日 下午3:23:57
     */
    private static class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    /**
     * 将inputStream 读取 成 ByteBuffer。
     * @param inputStream io流
     * @return 结果
     * @throws IOException io异常
     * @author 许小满  
     * @date 2015年12月7日 下午2:15:36
     */
    private static ByteBuffer parseInputStream(InputStream inputStream) throws IOException {
        DataInputStream inStream = new DataInputStream(inputStream);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] byts = new byte[1024];
        int len = 0;
        while ((len = inStream.read(byts)) >= 0) {
            outStream.write(byts, 0, len);
        }
        inStream.close();
        return ByteBuffer.wrap(outStream.toByteArray());
    }
}