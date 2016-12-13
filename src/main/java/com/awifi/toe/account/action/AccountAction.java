package com.awifi.toe.account.action;

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

import com.awifi.toe.account.service.AccountService;
import com.awifi.toe.auth.model.Permission;
import com.awifi.toe.auth.model.Role;
import com.awifi.toe.auth.model.User;
import com.awifi.toe.auth.service.UserService;
import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.base.constants.ParamName;
import com.awifi.toe.base.http.HttpRequest;
import com.awifi.toe.base.model.Page;
import com.awifi.toe.base.util.EncryUtil;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.MessageUtil;
import com.awifi.toe.base.util.SessionUtil;
import com.awifi.toe.base.util.ValidateUtil;
import com.awifi.toe.base.util.rules.Numeric;
import com.awifi.toe.base.util.rules.Required;
import com.awifi.toe.base.util.rules.Rule;
import com.awifi.toe.system.util.SysConfigUtil;

/**   
 * @Description:  
 * @Title: AccountAction.java 
 * @Package com.awifi.toe.account.action 
 * @author kangyanxiang 
 * @date 2015年11月20日 下午2:07:16
 * @version V1.0   
 */
@Controller
@Scope("prototype")
@RequestMapping("/account")
@SuppressWarnings("rawtypes")
public class AccountAction extends BaseAction{

    /**账号业务层*/
    @Resource(name="accountService")
    private AccountService accountService;
    
    /**用户业务层*/
    @Resource(name="userService")
    private UserService userService;
    
    /**
     * 电信侧账号列表（分页）
     * @param page 页面基本数据
     * @param error 错误信息
     * @return resultMap
     * @author kangyanxiang 
     * @date 2015年11月23日 下午5:20:46
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(@Validated()Page page,Errors error){
        resultMap = new HashMap<String, Object>();
        String keywords=request.getParameter("keywords");//账号或客户名称
        String roleId=request.getParameter("roleId");//角色id
        String provinceId=request.getParameter("provinceId");//省id
        String cityId=request.getParameter("cityId");//市id
        String areaId=request.getParameter("areaId");//区县id
        try{
            // 请求方法类型 校验
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            //参数校验
            if(error.hasErrors()){
                resultMap.put("result", "FAIL");
                resultMap.put("message",ErrorUtil.getErrorMessage(error));
                return  resultMap;
            }
            keywords=StringUtils.isNotBlank(keywords) ? keywords : null;
            roleId=StringUtils.isNotBlank(roleId) ? roleId : null;
            provinceId=StringUtils.isNotBlank(provinceId) ? provinceId : null;
            cityId=StringUtils.isNotBlank(cityId) ? cityId : null;
            areaId=StringUtils.isNotBlank(areaId) ? areaId : null;
            accountService.pageQuery(page,keywords,roleId,provinceId,cityId,areaId);
            this.setPageInfo(page);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "accounttelecom", accountService.getClass().toString(), e);
        }
        return resultMap;
    } 
    
    /**
     * 获取当前登陆账号的角色下拉框
     * @return resultMap
     * @author kangyanxiang 
     * @date 2015年11月24日 下午6:09:24
     */
    @RequestMapping(value = "/rolelist")
    @ResponseBody
    public Map getRoleList() {
        resultMap = new HashMap<String, Object>();
        try {
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
            }
            List<Role> roleList = accountService.getRoleList();
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", roleList);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 创建账号
     * @param user 账号
     * @return resultMap
     * @author kangyanxiang 
     * @date 2016年10月22日 下午9:42:04
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Map add(User user){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            String userName = user.getUserName();
            //校验账号唯一性
            if(accountService.isUserNameExit(userName)){
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("account.userName.exit"));//账号名称已存在！
                return resultMap;
            }
            String password=SysConfigUtil.getDefaultPassword();
            user.setPassword(password);//密码
            accountService.insert(user);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("defaultPassword", SysConfigUtil.getParamValue("system.default.password"));
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 重置密码
     * @return resultMap
     * @author kangyanxiang 
     * @date 2015年11月24日 上午10:08:18
     */
    @RequestMapping(value = "/pwdreset")
    @ResponseBody
    public Map pwdReset(){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            String accountId = request.getParameter("id");
            ValidateUtil vu = new ValidateUtil();
            vu.add("accountId", accountId, ParamName.ACCOUNT_ID,new Rule[] {new Required(),new Numeric()});
            String validStr = vu.validateString();
            if(StringUtils.isNotBlank(validStr)){
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            String password = SysConfigUtil.getDefaultPassword();//默认密码
            accountService.updatePwdById(password, Long.parseLong(accountId));
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("defaultPassword", SysConfigUtil.getParamValue("system.default.password"));
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 删除账号
     * @return resultMap
     * @author kangyanxiang 
     * @date 2015年11月24日 上午10:35:28
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map delete() {
        resultMap = new HashMap<String, Object>();
        try {
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message",MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            String accountId = request.getParameter("id");
            ValidateUtil vu = new ValidateUtil();
            vu.add("accountId", accountId, ParamName.ACCOUNT_ID,new Rule[] {new Required(),new Numeric()});
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            accountService.delete(Long.parseLong(accountId));
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception")+ "");
            saveExceptionLog("", "account",accountService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 保存编辑的账号信息
     * @return resultMap
     * @author kangyanxiang 
     * @date 2015年11月24日 下午5:08:31
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Map edit() {
        resultMap = new HashMap<String, Object>();
        try {
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
            }
            String accountId = request.getParameter("id");
            String contactPerson = request.getParameter("contactPerson");
            String contactWay = request.getParameter("contactWay");
            String remark = request.getParameter("remark");
            String projectId = request.getParameter("projectId");
            ValidateUtil vu = new ValidateUtil();
            vu.add("accountId", accountId, ParamName.ACCOUNT_ID, new Rule[] { new Required(), new Numeric() });
            vu.add("projectId", projectId, ParamName.PROJECT_ID, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            accountService.edit(accountId,projectId,contactPerson,contactWay,remark);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
     
     /**
      * 获取账号详情
      * @return resultMap
      * @author kangyanxiang 
      * @date 2015年11月25日 上午10:52:28
      */
    @RequestMapping(value = "/show")
    @ResponseBody
    public Map show(){
        String accountId=request.getParameter("id");//角色
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            ValidateUtil vu = new ValidateUtil();
            vu.add("accountId", accountId, ParamName.ACCOUNT_ID,new Rule[] {new Required(),new Numeric()});
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            User user=accountService.getInfoById(Long.parseLong(accountId));
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data",user);
        } catch(Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception")+ "");
            saveExceptionLog("","account",accountService.getClass().toString(),e);
        }
        return resultMap;
    }
    
    /**
     * 根据id获得权限ids
     * @return resultMap
     * @author kangyanxiang 
     * @date 2015年11月25日 上午10:56:19
     */
    @RequestMapping(value = "/showpermission")
    @ResponseBody
    public Map getPermissionListByAccountId(){
        resultMap = new HashMap<String, Object>();
        String accountId=request.getParameter("id");
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            ValidateUtil vu = new ValidateUtil();
            vu.add("accountId", accountId, ParamName.ACCOUNT_ID,new Rule[] {new Required(),new Numeric()});
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            String permissionids=accountService.getPermissionIdsById(Long.parseLong(accountId));
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", permissionids);
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 根据登陆账号获得权限列表
     * @return resultMap
     * @author kangyanxiang 
     * @date 2015年11月25日 上午11:17:07
     */
    @RequestMapping(value = "/getpermissionlist")
    @ResponseBody
    public Map getPermissionList(){
        resultMap = new HashMap<String, Object>();
        try{
            if(request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            Long accountId=SessionUtil.getCurUserId();
            List<Permission> permissionlist=accountService.getPermissionListById(accountId);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", permissionlist);
        }catch(Exception e){
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + "");
            saveExceptionLog("", "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 分配权限
     * @param id 账号id
     * @param permissionIds 权限ids
     * @return resultMap
     * @author kangyanxiang 
     * @date 2015年12月1日 下午2:12:59
     */
    @ResponseBody
    @RequestMapping(value = "/assign")
    public Map assign(Long id, String permissionIds) {
        String interfaceCode = "A070309";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            //参数校验
            ValidateUtil vu = new ValidateUtil();
            vu.add("accountId", id.toString(), ParamName.ACCOUNT_ID, new Rule[] { new Required(), new Numeric() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            accountService.assign(id,permissionIds);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);
            saveExceptionLog(interfaceCode, "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 账号管理--修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param qpassword 确认新密码
     * @return 密码
     * @author 苏晨斌  
     * @date 2015年11月25日 下午3:45:17
     */
    @RequestMapping(value = "/pwdedit")
    @ResponseBody
    public Map pwdEdit(String oldPassword, String newPassword, String qpassword){
        resultMap = new HashMap<String, Object>();
        String interfaceCode = "A070310";
        String userName = SessionUtil.getCurUserName();
        try{
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message",MessageUtil.getMessage("request_illegal"));
                return resultMap;
            }
            // 请求参数 校验(校验新验密码)
            ValidateUtil vu = new ValidateUtil();
            vu.add("newPassword", newPassword, ParamName.NEWPASSWORD, new Rule[]{new Required()});// 用户新密码
            vu.add("qpassword", qpassword, ParamName.QPASSWORD, new Rule[]{new Required()});// 用户确认密码
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)){
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            //查找数据库该用户的登录密码
            String dbPwd = accountService.findPwdByUserName(userName);
            // 校验 数据库密码与页面输入旧密码是否一致
            if(!dbPwd.equals(EncryUtil.getMd5Str(oldPassword))){
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("user.dbPassword.pagePassword.unequal"));// 原密码与页面输入旧密码不一致
                return resultMap;
            }
            // 校验 新密码与确认密码是否一致
            if(!newPassword.equals(qpassword)){
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("user.password.unequal"));// 两次密码输入不一致
                return resultMap;
            }
            //将新密码写入数据库
            accountService.updatePwdByUserName(EncryUtil.getMd5Str(newPassword), userName);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception")+ interfaceCode);
            saveExceptionLog(interfaceCode, "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 账号管理--修改账号信息
     * @param contactPerson 联系人
     * @param contactWay 联系方式
     * @return 账号信息
     * @author 苏晨斌  
     * @date 2015年11月26日 下午3:38:30
     */
    @RequestMapping(value = "/infomodify")
    @ResponseBody
    public  Map infoModify(String contactPerson, String contactWay) {
        String interfaceCode = "A070311";
        resultMap = new HashMap<String, Object>();
        try {
            if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));
            }
            String userName = SessionUtil.getCurUserName();
            accountService.edit(userName,contactPerson,contactWay);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode);
            saveExceptionLog(interfaceCode, "account", accountService.getClass().toString(), e);
        }
        return resultMap;
    }
}