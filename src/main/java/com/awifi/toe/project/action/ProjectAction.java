package com.awifi.toe.project.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.base.constants.ParamName;
import com.awifi.toe.base.http.HttpRequest;
import com.awifi.toe.base.model.Page;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.MessageUtil;
import com.awifi.toe.base.util.ValidateUtil;
import com.awifi.toe.base.util.rules.Required;
import com.awifi.toe.base.util.rules.Rule;
import com.awifi.toe.project.model.Project;
import com.awifi.toe.project.service.ProjectService;

/**   
 * @Description:  项目维护
 * @Title: Project.java 
 * @Package com.awifi.toe.customer.action 
 * @author 亢燕翔 
 * @date 2015年11月20日 上午11:19:17
 * @version V1.0   
 */
@SuppressWarnings("rawtypes")
@Controller
@Scope("prototype")
@RequestMapping("project")
public class ProjectAction extends BaseAction{
    
    /** 项目维护业务层  */
    @Resource(name = "projectService")
    private ProjectService projectService;
    
    /**
     * 项目列表(分页)
     * @param page 分页实体
     * @param error 错误提示
     * @param keywords 关键字
     * @param areaId 区
     * @param cityId 市
     * @param provinceId 省
     * @return resultMap
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:22:38
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(@Validated()Page page, Errors error,String keywords,String provinceId,String cityId,String areaId){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            if(error.hasErrors()){
                resultMap.put("result", "FAIL");
                resultMap.put("message", ErrorUtil.getErrorMessage(error));
                return resultMap;
            }
            projectService.pageQuery(page,keywords,provinceId,cityId,areaId);
            this.setPageInfo(page);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", e.getMessage());
            saveExceptionLog("", "project", projectService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 项目下拉列表
     * @return list
     * @author kangyanxiang 
     * @date Oct 31, 2016 10:03:54 AM
     */
    @RequestMapping(value = "/projectlist")
    @ResponseBody
    public Map projectlist(){
        resultMap = new HashMap<String, Object>();
        try {
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
            }
            List<Project> projectList = projectService.getProjectList();
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", projectList);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "project", projectService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 创建项目
     * @param projectName 项目名称
     * @param contact 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @param areaId 区
     * @param cityId 市
     * @param provinceId 省
     * @return resultMap
     * @author 亢燕翔 
     * @date 2015年12月10日 下午2:12:50
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Map add(String projectName,String contact,String contactWay,String remark,String provinceId,String cityId,String areaId){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            ValidateUtil vu = new ValidateUtil();
            vu.add("projectName", projectName, ParamName.PROJECT_NAME, new Rule[] { new Required() });
            vu.add("contact", contact, ParamName.CONTACT, new Rule[] { new Required() });
            vu.add("contactWay", contactWay, ParamName.CONTACTWAY, new Rule[] { new Required() });
            vu.add("provinceId", provinceId, ParamName.PROVINCE_ID, new Rule[] { new Required() });
            vu.add("cityId", cityId, ParamName.CITY_ID, new Rule[] { new Required() });
            vu.add("areaId", areaId, ParamName.AREA_ID, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            int projectNameCount = projectService.eqAddRepeat(projectName);
            if(projectNameCount>=1){
                resultMap.put("result", "FAIL");
                resultMap.put("message", "项目名称已存在，请检查重新填写");
                return resultMap;
            }
            projectService.add(projectName,contact,contactWay,remark,provinceId,cityId,areaId);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "project", projectService.getClass().toString(), e);
        }
        return resultMap;
    }    
    
    /**
     * 编辑项目
     * @param id 项目ID
     * @param projectName 项目名称
     * @param contact 联系人
     * @param contactWay 联系方式
     * @param remark 备注
     * @param areaId 区
     * @param cityId 市
     * @param provinceId 省
     * @return resultMap
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:51:17
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Map edit(String id,String projectName,String contact,String contactWay,String remark,String provinceId,String cityId,String areaId){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            ValidateUtil vu = new ValidateUtil();
            vu.add("id", id, ParamName.PROJECT_ID, new Rule[] { new Required() });
            vu.add("projectName", projectName, ParamName.PROJECT_NAME, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            int projectNameCount = projectService.eqEditRepeat(projectName,id);
            if(projectNameCount>=1){
                resultMap.put("result", "FAIL");
                resultMap.put("message", "项目名称已存在，请检查重新填写");
                return resultMap;
            }
            projectService.edit(id,projectName,contact,contactWay,remark,provinceId,cityId,areaId);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "project", projectService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 删除项目
     * @param id 项目ID
     * @return resultMap
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:36:39
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map delete(String id){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            ValidateUtil vu = new ValidateUtil();
            vu.add("id", id, ParamName.PROJECT_ID, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            String message = projectService.delete(id);
            resultMap.put("result", "OK");
            resultMap.put("message", message);
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "project", projectService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 通过项目ID获取项目详细信息
     * @param id 项目ID
     * @return resultMap
     * @author 亢燕翔 
     * @date 2015年12月8日 下午2:43:29
     */
    @RequestMapping(value = "/projectShow")
    @ResponseBody
    public Map projectShow(String id){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            ValidateUtil vu = new ValidateUtil();
            vu.add("id", id, ParamName.PROJECT_ID, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            Project project = projectService.projectShow(id);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", project);
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "project", projectService.getClass().toString(), e);
        }
        return resultMap;
    }
    
}