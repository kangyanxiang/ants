package com.awifi.toe.system.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.awifi.toe.base.model.Page;
import com.awifi.toe.base.service.BaseService;
import com.awifi.toe.base.util.DateUtil;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.IOUtil;
import com.awifi.toe.base.util.KeyUtil;
import com.awifi.toe.base.util.ZipUtil;
import com.awifi.toe.system.mapper.ThemeMapper;
import com.awifi.toe.system.model.Theme;
import com.awifi.toe.system.util.SysConfigUtil;

/**   
 * @Description:  皮肤业务层
 * @Title: ThemeService.java 
 * @Package com.awifi.toe.system.service 
 * @author 许小满 
 * @date 2016年4月22日 上午9:40:58
 * @version V1.0   
 */
@Service("themeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ThemeService extends BaseService{
    
    /** 皮肤mapper */
    @Resource(name="themeMapper")
    private ThemeMapper themeMapper;

    /**
     * 皮肤分页
     * @param page 页面基本数据
     * @param keywords 关键字
     * @throws Exception 异常
     * @author 许小满  
     * @date 2016年4月22日 下午7:56:16
     */
    public void pageQuery(Page page, String keywords) throws Exception {
        if (page.getPageSize() == null) {
            page.setPageSize(Integer.parseInt(SysConfigUtil.getParamValue("page.pageSize")));
        }
        int pageCount = themeMapper.pageCount(keywords);
        page.setTotalRecord(pageCount);
        if (pageCount <= 0) {
            return;
        }
        List<Theme> themeList = themeMapper.queryList(keywords, page.getBegin(), page.getPageSize());
        page.setRecords(themeList);
    }
    
    /**
     * 新增皮肤
     * @param request 请求对象
     * @param theme 皮肤对象
     * @throws Exception 异常
     * @author 许小满  
     * @date 2015年12月16日 下午4:07:05
     */
    public void add(HttpServletRequest request, Theme theme) throws Exception{
        CommonsMultipartResolver multipartResolver  = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(!multipartResolver.isMultipart(request)){
            logger.debug("错误：form 未包含 enctype=\"multipart/form-data\"");
            return;
        }
        String code = getCode();//获取编号（组件唯一码）
        logger.debug("提示：获取编号（组件唯一码） = " + code);
        theme.setCode(code);//编号（组件唯一码）
        MultipartHttpServletRequest  multiRequest = (MultipartHttpServletRequest)request;
        Iterator<String>  iter = multiRequest.getFileNames();
        MultipartFile multipartFile = null;
        String themePath = null;//皮肤压缩包--文件保存相对路径
        String thumb = null;//皮肤缩略图--文件保存相对路径
        while(iter.hasNext()){
            multipartFile = multiRequest.getFile((String)iter.next());
            if(multipartFile == null){
                continue;
            }
            String fileName = multipartFile.getName();
            logger.debug("提示：fileName= " + fileName);
            if(StringUtils.isBlank(fileName)){
                continue;
            }
            //处理组件压缩包
            if(fileName.equals("themeZip")){
                themePath = this.dealThemeZip(request, multipartFile, theme);
            }
            //处理组件缩略图
            else if(fileName.equals("thumbPic")){
                thumb = IOUtil.savePicture(request, multipartFile);
            }
        }
        theme.setThemePath(themePath);//皮肤压缩包--文件保存相对路径
        theme.setThumb(thumb);//皮肤缩略图--文件保存相对路径
        themeMapper.insert(theme);
    }
    
    /**
     * 皮肤配置编辑
     * @param request 请求对象
     * @param theme 皮肤对象
     * @throws Exception 异常
     * @author 许小满  
     * @date 2015年12月16日 下午7:37:45
     */
    public void edit(HttpServletRequest request, Theme theme) throws Exception{
        CommonsMultipartResolver multipartResolver  = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(!multipartResolver.isMultipart(request)){
            logger.debug("错误：form 未包含 enctype=\"multipart/form-data\"");
            return;
        }
        MultipartHttpServletRequest  multiRequest = (MultipartHttpServletRequest)request;
        Iterator<String>  iter = multiRequest.getFileNames();
        MultipartFile multipartFile = null;
        String thumb = theme.getThumb();//皮肤缩略图--文件保存相对路径
        ServletContext servletContext = request.getServletContext();
        while(iter.hasNext()){
            multipartFile = multiRequest.getFile((String)iter.next());
            if(multipartFile == null){
                continue;
            }
            String fileName = multipartFile.getName();
            logger.debug("提示：fileName= " + fileName);
            if(StringUtils.isBlank(fileName)){
                continue;
            }
            //处理组件压缩包
            if(fileName.equals("themeZip")){
                this.dealThemeZip(request, multipartFile, theme);
            }
            //处理组件缩略图
            else if(fileName.equals("thumbPic")){
                String thumbFullPath = servletContext.getRealPath(thumb);
                IOUtil.mkDirsByFilePath(thumbFullPath);//如果目录不存在，自动创建
                multipartFile.transferTo(new File(thumbFullPath));
            }
        }
        themeMapper.update(theme);
    }
    
    /**
     * 获取编号（皮肤唯一码）
     * @return 编号
     * @author 许小满  
     * @date 2016年4月24日 下午11:52:08
     */
    private String getCode(){
        String code = null;
        int count;
        do{
            code = KeyUtil.generateKey();
            count = themeMapper.findNumByCode(code);
        }while(count > 0);
        return code;
    }
    
    /**
     * 处理皮肤压缩包
     * @param request request
     * @param multipartFile 压缩包文件
     * @param theme 皮肤对象
     * @throws Exception 异常
     * @return 皮肤路径
     * @author 许小满  
     * @date 2016年4月25日 上午12:22:52
     */
    private String dealThemeZip(HttpServletRequest request, MultipartFile multipartFile,Theme theme) throws Exception{
        File zipTempFile = null;
        String themePath = theme.getThemePath();
        String code = theme.getCode();
        try{
            ServletContext servletContext = request.getServletContext();
            if(StringUtils.isBlank(themePath)){
                themePath = this.getThemePath(request, code);//组件保存路径
            }
            logger.debug("提示：themePath = " + themePath);
            String themeFullPath = servletContext.getRealPath(themePath);
            String tempPath = this.getTempPath(request);//组件zip临时保存路径
            String tempFullpath = servletContext.getRealPath(tempPath);
            logger.debug("提示：tempFullpath = " + tempFullpath);
            //1.拷贝到临时路径
            IOUtil.copyFile(multipartFile.getInputStream(), tempFullpath);
            //2.解压缩
            //2.1 删除旧文件
            IOUtil.remove(themeFullPath);
            zipTempFile = new File(tempFullpath);
            ZipUtil.unzip(zipTempFile, themeFullPath);
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            throw e;
        }finally{
            try{
                //释放资源
                if(zipTempFile != null){
                    IOUtil.remove(zipTempFile);
                }
            }catch(Exception e1){}
        }
        return themePath;
    }
    
    /**
     * 获取皮肤保存路径
     * @param request 请求对象
     * @param code 编号
     * @return 皮肤保存路径
     * @author 许小满  
     * @date 2016年4月25日 上午12:17:33
     */
    private String getThemePath(HttpServletRequest request, String code){
        StringBuffer componentPath = new StringBuffer();
        componentPath.append("/media/theme/");
        String date = DateUtil.getTodayDate();
        String[] dataArray = date.split("-");
        String year = dataArray[0];//年
        String month = dataArray[1];//月
        String day = dataArray[2];//日
        componentPath.append(year).append("/").append(month).append("/").append(day).append("/").append(code);
        return componentPath.toString();
    }
    
    /**
     * 获取皮肤zip临时保存路径
     * @param request 请求对象
     * @return 皮肤zip临时保存路径
     * @author 许小满  
     * @date 2016年4月25日 上午12:18:57
     */
    private String getTempPath(HttpServletRequest request){
        StringBuffer path = new StringBuffer();
        path.append(File.separator).append("file").append(File.separator).append("temp");
        //创建temp目录
        IOUtil.mkDirs(request.getServletContext().getRealPath(path.toString()));
        path.append(File.separator).append(KeyUtil.generateKey()).append(".zip");
        return path.toString();
    }

    /**
     * 系统配置--换肤配置--删除
     * @param id 主键id
     * @author 苏晨斌  
     * @date 2016年4月25日 上午9:06:25
     */
    public void delete(Long id) {
        themeMapper.delete(id);        
    }
    
    /**
     * 通过皮肤名称获取皮肤集合
     * @param name 皮肤名称
     * @return 皮肤集合
     * @author 许小满  
     * @date 2016年4月25日 上午8:42:34
     */
    public List<Theme> findByName(String name){
        return themeMapper.findByName(name);
    }
    
    /**
     * 通过id获取皮肤信息
     * @param id 组件表主键Id
     * @return 皮肤
     * @author 许小满  
     * @date 2016年4月25日 下午6:22:46
     */
    public Theme findById(Long id){
        Theme theme = themeMapper.findById(id);
        theme.setId(id);
        return theme;
    }
    
    /**
     * 校验皮肤名称是否已经存在
     * @param id 皮肤表主键id
     * @param name 皮肤名称
     * @return true 已存在、false 不存在
     * @author 许小满  
     * @date 2016年4月28日 下午6:02:43
     */
    public boolean isNameExist(Long id, String name){
        int num = themeMapper.isNameExist(id, name);
        return num > 0 ? true : false;
    }
    
    /**
     * 根据域名、编码获取皮肤路径
     * @param domain 域名
     * @param themeCode 皮肤编号
     * @return 皮肤路径
     * @author 许小满  
     * @date 2016年5月4日 下午4:43:49
     */
    public String findPathByDomainAndCode(String domain, String themeCode){
        return themeMapper.findPathByDomainAndCode(domain, themeCode);
    }
    
    /**
     * 根据客户信息获取皮肤路径
     * @param customerIds 客户ids
     * @param cascadeLevel 客户层级
     * @return 皮肤路径
     * @author 许小满  
     * @date 2016年5月9日 下午6:32:43
     */
    public String findPathByCustomerInfo(String customerIds, Integer cascadeLevel){
        return themeMapper.findPathByCustomerInfo(customerIds, cascadeLevel);
    }
    
}