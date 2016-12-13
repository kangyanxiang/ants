package com.awifi.toe.base.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
/**   
 * @Description:  
 * @Title: SFTPChannel.java 
 * @Package com.awifi.toe.base.util 
 * @author 郭海勇 
 * @date 2016年3月15日 上午10:16:06
 * @version V1.0   
 */
public class SFTPChannel {
    /**
     * 会话
     */
    private Session session = null;
    /**
     * 通道
     */
    private Channel channel = null;

    /**
     * 日志对象
     */
    private static final Logger logger = Logger.getLogger(SFTPChannel.class.getName());

    /**获取通道对象
     * @param sftpDetails 连接sftp服务器参数集合
     * @param timeout 超时时间
     * @return sftp通道
     * @throws JSchException 异常
     * @author 郭海勇  
     * @date 2016年3月29日 下午4:23:53
     */
    public ChannelSftp getChannel(Map<String, String> sftpDetails, int timeout) throws JSchException {

        String ftpHost = sftpDetails.get(SFTPConstants.SFTP_REQ_HOST);
        String port = sftpDetails.get(SFTPConstants.SFTP_REQ_PORT);
        String ftpUserName = sftpDetails.get(SFTPConstants.SFTP_REQ_USERNAME);
        String ftpPassword = sftpDetails.get(SFTPConstants.SFTP_REQ_PASSWORD);

        int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT;
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }

        JSch jsch = new JSch(); // 创建JSch对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
        logger.debug("Session created.");
        if (ftpPassword != null) {
            session.setPassword(ftpPassword); // 设置密码
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        logger.debug("Session connected.");

        logger.debug("Opening Channel.");
        channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接
        logger.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
                + ", returning: " + channel);
        return (ChannelSftp) channel;
    }

    /**关闭
     * @throws Exception 异常
     * @author 郭海勇  
     * @date 2016年3月29日 下午4:25:01
     */
    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
    
    public class SFTPConstants {
        /**
         * ip地址
         */
        public static final String SFTP_REQ_HOST = "host";
        /**
         * 端口
         */
        public static final String SFTP_REQ_PORT = "port";
        /**
         * 用户名
         */
        public static final String SFTP_REQ_USERNAME = "username";
        /**
         * 密码
         */
        public static final String SFTP_REQ_PASSWORD = "password";
        /**
         * 默认端口
         */
        public static final int SFTP_DEFAULT_PORT = 22;
        /**
         * 本地路径
         */
        public static final String SFTP_REQ_LOC = "location";
    }
    
    /**文件上传方法
     * @param map 连接服务器参数
     * @param srcFile 本地源文件
     * @param dscFile 服务器目标路径
     * @author 郭海勇  
     * @throws SftpException 
     * @throws IOException 
     * @date 2016年3月29日 下午4:26:50
     */
    public static void onload(Map<String, String> map,String srcFile,String dscFile) throws SftpException, IOException {
        SFTPChannel sftpChannel = new SFTPChannel();
        ChannelSftp sftp = null;
        try {
            sftp = sftpChannel.getChannel(map, 5000);
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        createFolder(dscFile,sftp);
        try {
            logger.debug("开始上传文件："+dscFile);
            //sftp.put(srcFile, dscFile, ChannelSftp.OVERWRITE);
            OutputStream out = sftp.put(dscFile, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
            byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
            int read;
            if (out != null) {
                logger.debug("Start to read input stream");
                InputStream is = new FileInputStream(srcFile);
                do {
                    read = is.read(buff, 0, buff.length);
                    if (read > 0) {
                        out.write(buff, 0, read);
                    }
                    out.flush();
                } while (read >= 0);
                is.close();
                logger.debug("input stream read done.");
            }
            logger.debug("已上传到服务器："+dscFile);
        } catch (SftpException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        delLocalFile(srcFile);//删除本地文件
        sftp.quit();
        try {
            sftpChannel.closeChannel();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**ftp上传文件
     * @param map 登陆信息对象
     * @param srcFile 源文件路径
     * @param dscFile 上传目标文件路径
     * @author 郭海勇  
     * @date 2016年5月6日 下午8:03:28
     */
    public static void ftpUpload(Map<String, String> map,String srcFile,String dscFile) { 
        FTPClient ftpClient = new FTPClient(); 
        FileInputStream fis = null; 
        try { 
            logger.debug("开始上传文件："+dscFile);
            ftpClient.connect(map.get(SFTPConstants.SFTP_REQ_HOST)); 
            ftpClient.login(map.get(SFTPConstants.SFTP_REQ_USERNAME), map.get(SFTPConstants.SFTP_REQ_PASSWORD)); 
            File srcfile = new File(srcFile); 
            fis = new FileInputStream(srcfile); 
            
            ftpClient.setBufferSize(1024); 
            ftpClient.setControlEncoding("UTF-8"); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
          //设置上传目录 
            ftpCreateFolder(dscFile,ftpClient);
            ftpClient.storeFile(dscFile, fis);
            logger.debug("已上传到服务器："+dscFile);
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new RuntimeException("FTP客户端出错！", e); 
        } finally { 
            delLocalFile(srcFile);
            IOUtils.closeQuietly(fis); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new RuntimeException("关闭FTP连接发生异常！", e); 
            } 
        } 
    } 
//    public static void main(String[] args) {
//        String src = "C:/Users/Administrator/Desktop/test.txt";
//        String desc = "/01/a/test1.txt";
//        Map<String, String> map = new HashMap<String, String>();
//        map.put(SFTPConstants.SFTP_REQ_HOST, "122.229.31.18");
//        map.put(SFTPConstants.SFTP_REQ_USERNAME, "awifi_toe");
//        map.put(SFTPConstants.SFTP_REQ_PASSWORD, "0mFNc7R5KlXt");
//        ftpUpload(map,src,desc);
//    }
    /**
     * 删除本地文件
     * @param srcFile 本地文件路径
     * @author 亢燕翔 
     * @date 2016年4月7日 下午4:40:07
     */
    private static void delLocalFile(String srcFile) {
        File file = new File(srcFile);
        if (file.exists()) {
            file.delete();  
        } 
    }

    /**
     * 创建文件夹
     * @param dscFile 目标路径
     * @param sftp sftp
     * @throws SftpException 异常
     * @author 亢燕翔 
     * @date 2016年4月7日 下午4:32:56
     */
    private static void createFolder(String dscFile, ChannelSftp sftp) throws SftpException {
        logger.debug(dscFile);
        try {
            sftp.ls(dscFile.substring(0, 3));
        } catch (SftpException e) {
            sftp.mkdir(dscFile.substring(0, 3));
            logger.debug("创建文件夹"+dscFile.substring(0, 3));
        }
        try {
            sftp.ls(dscFile.substring(0, 8));
        } catch (SftpException e) {
            sftp.mkdir(dscFile.substring(0, 8));
            logger.debug("创建文件夹"+dscFile.substring(0, 8));
        }
        try {
            sftp.ls(dscFile.substring(0,11));
        } catch (SftpException e) {
            sftp.mkdir(dscFile.substring(0, 11));
            logger.debug("创建文件夹"+dscFile.substring(0, 11));
        }
        try {
            sftp.ls(dscFile.substring(0, 14));
        } catch (SftpException e) {
            sftp.mkdir(dscFile.substring(0, 14));
            logger.debug("创建文件夹"+dscFile.substring(0, 14));
        }
    }
    
    /**创建文件夹
     * @param dscFile 目标文件夹
     * @param ftp 对象
     * @author 郭海勇  
     * @date 2016年5月6日 下午8:01:26
     */
    private static void ftpCreateFolder(String dscFile, FTPClient ftp){
        logger.debug(dscFile);
        try {
            if(!ftp.changeWorkingDirectory(dscFile.substring(0, 3))){
                ftp.makeDirectory(dscFile.substring(0, 3));
                logger.debug("创建文件夹"+dscFile.substring(0, 3));
            }
            if(!ftp.changeWorkingDirectory(dscFile.substring(0, 8))){
                ftp.makeDirectory(dscFile.substring(0, 8));
                logger.debug("创建文件夹"+dscFile.substring(0, 8));
            }
            if(!ftp.changeWorkingDirectory(dscFile.substring(0, 11))){
                ftp.makeDirectory(dscFile.substring(0, 11));
                logger.debug("创建文件夹"+dscFile.substring(0, 11));
            }
            if(!ftp.changeWorkingDirectory(dscFile.substring(0, 14))){
                ftp.makeDirectory(dscFile.substring(0, 14));
                logger.debug("创建文件夹"+dscFile.substring(0, 14));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
