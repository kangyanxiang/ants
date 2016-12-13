package com.awifi.toe.base.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Title: IOUtil.java
 * @Package com.awifi.toe.base.util
 * @author 亢燕翔
 * @date 2015年8月4日 上午11:35:28
 * @version V1.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IOUtil {

    /** 日志 */
    private static final Log logger = LogFactory.getLog(IOUtil.class);

    /**
     * 下载工具类
     * @param fileName 文件名
     * @param filePath 文件路径
     * @param response response对象
     * @author 亢燕翔
     * @date 2015年8月4日 下午1:32:11
     */
    public static void download(String fileName, String filePath, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            InputStream inputStream = new FileInputStream(new File(filePath + "/" + fileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();// 关闭资源
            inputStream.close();
        } catch (IOException e) {
            ErrorUtil.printException(e, logger);
        } finally {  
            if(filePath.substring(filePath.length()-4, filePath.length()).equals("temp")){
                delLocalFile(filePath + File.separator + fileName);
            }
        } 
    }

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
     * 返回错误的json格式的信息
     * @param response response对象
     * @param errorMessage 错误信息
     * @author 许小满
     * @date 2015年11月30日 下午1:42:10
     */
    public static void responseError(HttpServletResponse response, String errorMessage) {
        Map<String, Object> resultMap = new HashMap<String, Object>(2);
        resultMap.put("result", "FAIL");
        resultMap.put("message", errorMessage);
        String json = JsonUtil.toJson(resultMap);
        PrintWriter out = null;
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            out = response.getWriter();
            out.write(json);
            out.flush();
        } catch (IOException e) {
            ErrorUtil.printException(e, logger);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 拷贝文件
     * @param is 文件流
     * @param to 目标文件路径
     * @return map
     * @author 许小满
     * @date 2011-8-24 上午09:49:04
     */
    public static Map copyFile(InputStream is, String to) {
        Map params = new HashMap();
        if (is == null) {
            params.put("fileSize", 0);
            return params;
        }
        int fileSize = 0;
        OutputStream os = null;
        try {
            os = new FileOutputStream(to);
            fileSize = IOUtils.copy(is, os);
            logger.debug("提示：文件大小= " + fileSize);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e1) {
                    ErrorUtil.printException(e1, logger);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e1) {
                    ErrorUtil.printException(e1, logger);
                }
            }
        }
        params.put("fileSize", fileSize);
        return params;
    }

    /**
     * 删除文件或目录
     * @param path 文件路径
     * @author xuxiaoman
     * @date 2010-11-17
     */
    public static void remove(String path) {
        if (StringUtils.isBlank(path)) {
            logger.debug("提示：需要删除文件的【path】为空！");
            return;
        }
        File file = null;
        try {
            file = new File(path);
            if (file.isDirectory()) {// 目录
                logger.debug("提示：开始删除目录{ " + path + " }");
                FileUtils.deleteDirectory(file);
            } else if (file.isFile()) {// 文件
                logger.debug("提示：开始删除文件{ " + path + " }");
                file.delete();
            }
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
    }

    /**
     * 删除文件或目录
     * @param file 文件
     * @author xuxiaoman
     * @date 2010-11-17
     */
    public static void remove(File file) {
        try {
            if (file.isDirectory()) {// 目录
                FileUtils.deleteDirectory(file);
            } else if (file.isFile()) {// 文件
                file.delete();
            }
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
    }

    /**
     * 替换指定文件中的指定文本操作
     * @param optFile 所需要操作的指定文件
     * @param originStr 需要替换的原始字串
     * @param replaceStr 需要替换后的字串 -1：文件替换出错，目录不存在/IOException等 0: 没有可以替换的文本
     */
    public static void replaceText(File optFile, String originStr, String replaceStr) {
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(optFile)));
            String filecontent = null;
            List<String> fileContentList = new ArrayList<String>();
            while ((filecontent = br.readLine()) != null) {
                fileContentList.add(filecontent);
            }
            br.close();
            pw = new PrintWriter(new FileOutputStream(optFile));
            // 输出替换完成的文件
            int maxSize = fileContentList.size();
            String lineSeparator = null;// 换行符
            if (maxSize > 1) {
                lineSeparator = System.getProperty("line.separator");
            }
            for (int i = 0; i < maxSize; i++) {
                pw.write(fileContentList.get(i).replaceAll(originStr, replaceStr));
                if (i < (maxSize - 1)) {
                    pw.write(lineSeparator);
                }
            }
            pw.flush();
        } catch (IOException e) {
            ErrorUtil.printException(e, logger);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e1) {
            }
        }
    }

    /**
     * 获取文件的后缀
     * @param fileName 文件名称
     * @return 文件的后缀
     * @author 许小满
     * @date 2012-4-17 下午5:31:55
     */
    public static String getFileSuffix(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return StringUtils.EMPTY;
        }
        String fileSuffix = null;
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex != -1) {
            fileSuffix = fileName.substring(lastIndex + 1, fileName.length());
        }
        return fileSuffix != null ? fileSuffix : StringUtils.EMPTY;
    }

    /**
     * 1. 判断目录是否存在 2. 当不存在时，自动创建该目录
     * @param path 文件夹路径
     * @author 许小满
     * @date Jan 13, 2012 9:49:17 AM
     */
    public static void mkDirs(String path) {
        if (StringUtils.isBlank(path)) {
            logger.debug("错误：path 为空！");
            return;
        }
        File file = null;
        try {
            file = new File(path);
            boolean isDirectory = file.exists() && file.isDirectory();
            if (isDirectory) {// 当存在时，自动跳过
                logger.debug("提示：路径(" + path + ") 已存在！");
            } else {// 当不存在时，自动创建该目录
                boolean executeResult = file.mkdirs();
                if (executeResult) {
                    logger.debug("提示：文件夹(" + path + ") 创建成功！");
                } else {
                    logger.debug("提示：文件夹(" + path + ") 创建失败！");
                }
            }
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
    }
    
    /**
     * 通过文件路径，创建文件夹
     * @param filePath 文件路径
     * @author 许小满  
     * @date 2016年1月6日 下午7:14:49
     */
    public static void mkDirsByFilePath(String filePath){
        if(StringUtils.isBlank(filePath)){
            return;
        }
        int lastIndex = filePath.lastIndexOf("/");
        if(lastIndex == -1){
            lastIndex = filePath.lastIndexOf("\\");
        }
        //logger.debug("提示：lastIndex= " + lastIndex);
        if(lastIndex == -1){
            return;
        }
        String folderPath = filePath.substring(0, lastIndex);
        logger.debug("提示：文件所对应的文件夹路径[folderPath]= " + folderPath);
        mkDirs(folderPath);
    }
    
    /**
     * 保存图片
     * @param request 请求
     * @param multipartFile 文件
     * @return 图片路径
     * @author 许小满
     * @date 2015年12月18日 下午8:42:02
     */
    public static String savePicture(HttpServletRequest request, MultipartFile multipartFile) {
        return savePicture(request, multipartFile, null);
    }
    
    /**
     * 保存图片
     * @param request 请求
     * @param multipartFile 文件
     * @param picPath 图片路径，当存在是，覆盖原有的旧图片
     * @return 图片路径
     * @author 许小满
     * @date 2015年12月18日 下午8:42:02
     */
    public static String savePicture(HttpServletRequest request, MultipartFile multipartFile, String picPath) {
        try {
            ServletContext servletContext = request.getServletContext();
            if(StringUtils.isBlank(picPath)){
                picPath = getPicPath(servletContext, multipartFile);
            }else{//创建对应的文件夹，防止文件不存在时报错
                mkDirsByFilePath(servletContext.getRealPath(picPath));
            }
            logger.debug("提示：图片路径= " + picPath);
            multipartFile.transferTo(new File(servletContext.getRealPath(picPath)));
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return picPath;
    }
    
    /**
     * 获取图片路径
     * @param servletContext 应用上下文
     * @param multipartFile 文件
     * @return 文件路径
     * @author 许小满  
     * @date 2016年1月19日 下午2:49:28
     */
    private static String getPicPath(ServletContext servletContext, MultipartFile multipartFile){
        String date = DateUtil.getTodayDate();
        String[] dataArray = date.split("-");
        String year = dataArray[0];// 年
        String month = dataArray[1];// 月
        String day = dataArray[2];// 日
        StringBuffer folderPath = new StringBuffer(25);// 文件夹路径
        folderPath.append("/").append("media").append("/").append("picture").append("/").append(year).append("/")
                .append(month).append("/").append(day);
        // logger.debug("提示：路径长度= " + folderPath.length());
        String folderPathStr = folderPath.toString();
        logger.debug("提示：folderPath= " + folderPathStr);
        mkDirs(servletContext.getRealPath(folderPathStr));// 创建文件夹
        String fileName = multipartFile.getOriginalFilename();
        logger.debug("提示：上传的图片名[fileName]= " + fileName);
        String fileSuffix = getFileSuffix(fileName);// 文件后缀
        logger.debug("提示：上传的图片后缀[fileSuffix]= " + fileSuffix);
        StringBuffer picPath = new StringBuffer(62);
        picPath.append(folderPathStr).append("/").append(KeyUtil.generateKey()).append(".").append(fileSuffix);
        // logger.debug("提示：路径长度= " + picPath.length());
        return picPath.toString();
    }

    /**
     * 年月日的路径合并
     * @param code 文件路径
     * @return String
     * @author 郭海勇
     * @date 2015年12月23日 上午11:35:39
     */
    public static String mergePath(String code) {
        StringBuffer componentPath = new StringBuffer();
        componentPath.append("/media/template/");
        String date = DateUtil.getTodayDate();
        String[] dataArray = date.split("-");
        String year = dataArray[0];//年
        String month = dataArray[1];//月
        String day = dataArray[2];//日
        componentPath.append(year).append("/").append(month).append("/").append(day).append("/").append(code);
        return componentPath.toString();
    }

    /**
     * 文件拷贝
     * @param src 文件源路径
     * @param target 文件输出路径
     * @author 郭海勇
     * @date 2015年12月21日 下午3:48:44
     */
    public static void fileChannelCopy(String src, String target) {
        File s = new File(src);
        File t = new File(target);
        if (!t.exists()) {
            createFile(target);
        }
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            ErrorUtil.printException(e, logger);
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制文件夹
     * 
     * @param sourceDir 源文件夹
     * @param targetDir 模板文件夹
     * @throws IOException 异常
     * @author 郭海勇
     * @date 2015年12月23日 下午3:38:05
     */
    public static void copyDirectiory(String sourceDir, String targetDir) {
        try {
            if (!new File(sourceDir).isDirectory()) {
                return;
            }
            // 新建目标目录
            (new File(targetDir)).mkdirs();
            // 获取源文件夹当前下的文件或目录
            File[] file = (new File(sourceDir)).listFiles();
            for (int i = 0; i < file.length; i++) {
                if (file[i].isFile()) {
                    // 源文件
                    File sourceFile = file[i];
                    // 目标文件
                    File targetFile = new File(new File(targetDir).getAbsolutePath() + "/" + file[i].getName());
                    fileChannelCopy(sourceFile.getAbsolutePath(), targetFile.getAbsolutePath());
                }
                if (file[i].isDirectory()) {
                    // 准备复制的源文件夹
                    String dir1 = sourceDir + "/" + file[i].getName();
                    // 准备复制的目标文件夹
                    String dir2 = targetDir + "/" + file[i].getName();
                    copyDirectiory(dir1, dir2);
                }
            }
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }

    }

    /**
     * 文件合并
     * @param outFile 输出文件的路径
     * @param files 多个来源文件路径
     * @author 郭海勇
     * @date 2015年12月21日 下午4:12:26
     */
    public static void mergeFiles(String outFile, String[] files) {
        BufferedReader bufReader = null;
        BufferedWriter bufWriter = null;
        try{
            //缓冲System.in输入流  
            //System.in是位流，可以通过InputStreamReader将其转换为字符流  
            //缓冲FileWriter  
            bufWriter = new BufferedWriter(new FileWriter(outFile, true));  
            String input = null;  
            for(int i=0; i<files.length; i++){
                if(files[i].equals(outFile)){
                    continue;
                }
                bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i])));
              //每读一行进行一次写入动作  
                input = bufReader.readLine();
                while(input != null){
                    bufWriter.write(input);  
                    //newLine()方法写入与操作系统相依的换行字符，依执行环境当时的OS来决定该输出那种换行字符  
                    //bufWriter.newLine();
                    input = bufReader.readLine();
                }
                bufReader.close();
            }
            bufWriter.flush();
        }catch(Exception e){  
            ErrorUtil.printException(e, logger);
        }finally{
            if(bufWriter != null){
                try{ 
                    bufWriter.close();
                }catch(Exception e){}
            }
        }
    }

    /**
     * 文件内容替换
     * @param filePath 文件路径
     * @param src 原文本内容
     * @param target 需要替换上去的内容
     * @author 郭海勇
     * @date 2015年12月22日 下午8:44:13
     */
    public static void replaceContentToFile(String filePath, String src, String target) {
        FileReader read = null;
        BufferedReader br = null;
        StringBuilder content = null;
        FileOutputStream fs = null;
        try {
            read = new FileReader(filePath);
            br = new BufferedReader(read);
            content = new StringBuilder();
            int dex=0;
            while (br.ready()) {
                content.append(br.readLine());
                while(dex != -1){
                    dex = content.indexOf(src,dex+1);
                    if (dex != -1) {
                        content.delete(dex, src.length() + dex);
                        content.insert(dex, target);
                    }
                }
                //content.append("\n");
            }
            
            br.close();
            read.close();
            fs = new FileOutputStream(filePath);
            fs.write(content.toString().getBytes());
            fs.close();

        } catch (FileNotFoundException e) {
            ErrorUtil.printException(e, logger);

        } catch (IOException e) {
            ErrorUtil.printException(e, logger);

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (read != null) {
                    read.close();
                }
                if (fs != null) {
                    fs.close();
                }
            } catch (IOException e) {

            }
        }
    }

    
    /**
     * 文件追加内容
     * @param fileName 文件路径
     * @param content 内容
     * @author 郭海勇
     * @date 2015年12月24日 上午11:17:33
     */
    public static void fileAppendContent(String fileName, String content) {
        FileWriter writer = null;
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(fileName, true);
            writer.write(content);
        } catch (IOException e) {
            ErrorUtil.printException(e, logger);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建文件路径，可以创建多层文件夹
     * @param destFileName 路径
     * @return 是否创建成功
     * @author 郭海勇
     * @date 2015年12月31日 上午10:46:41
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            return false;
        }
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                logger.debug("创建目录文件所在的目录失败！");
                return false;
            }
        }
        // 创建目标文件
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("创建单个文件" + destFileName + "失败！");
            return false;
        }
    }

    /**清空文件内容
     * @param filePath 文件路径
     * @author 郭海勇  
     * @date 2016年1月6日 上午11:46:06
     */
    public static void clearFile(String filePath) {
        try {
            File f5 = new File(filePath);
            FileWriter fw5 = new FileWriter(f5);
            BufferedWriter bw1 = new BufferedWriter(fw5);
            bw1.write("");
            bw1.close();
        } catch (Exception e) {
            e.printStackTrace();
            ErrorUtil.printException(e, logger);
        }
    }

}