package com.awifi.toe.auth.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awifi.toe.auth.security.CustomUserDetailsService;
import com.awifi.toe.auth.service.UserService;
import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.base.constants.ParamName;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.MessageUtil;
import com.awifi.toe.base.util.ValidateUtil;
import com.awifi.toe.base.util.rules.Length;
import com.awifi.toe.base.util.rules.Required;
import com.awifi.toe.base.util.rules.Rule;

/**
 * @Description: 认证 -- 控制层
 * @Title: AuthAction.java
 * @Package com.awifi.toe.auth.action
 * @author kangyanxiang
 * @date 2015年4月30日 下午4:13:21
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
@Controller
@Scope("prototype")
@RequestMapping("auth")
public class AuthAction extends BaseAction {

    /** 用户业务类 */
    @Resource(name = "userService")
    private UserService userService;

    /** CustomUserDetailsService */
    @Resource(name = "customUserDetailsService")
    private CustomUserDetailsService customUserDetailsService;

    /**
     * 指向登录页面
     * @param error 返回正确/错误消息
     * @param model model
     * @param code 验证码
     * @return 登录页面地址
     * @author kangyanxiang
     */
    @RequestMapping(value = "/login")
    public String getLoginPage(boolean error, ModelMap model, String code) {
        try {
            logger.debug("Received request to show login page");
            // 验证用户名密码是否匹配
            if (error) {
                model.put("error", MessageUtil.getMessage("user.name.password.invalid"));
            } else {
                model.put("error", StringUtils.EMPTY);
            }
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        }
        return "auth/user/loginpage";
    }

    /**
     * 验证登录页面的验证码是否正确
     * @param code 验证码
     * @return 返回true、false
     * @author 牛华凤
     * @throws Exception 异常
     * @date 2015年7月14日 上午11:32:41
     */
    @RequestMapping(value = "/validationcode")
    @ResponseBody
    public Map validationCode(String code) throws Exception {
        resultMap = new HashMap<String, Object>();
        // 校验前台得到的验证码
        ValidateUtil vu = new ValidateUtil();
        vu.add("code", code, ParamName.AUTH_CODE, new Rule[] { new Required(), new Length(6, 6) });// 验证码不能为空，长度为6位
        String validStr = vu.validateString();

        // 校验失败
        if (StringUtils.isNotBlank(validStr)) {
            resultMap.put("result", "FAIL");
            resultMap.put("message", validStr);
            return resultMap;
        }
        String authCode = (String) session.getAttribute("authCode");// 得到保存在session中的验证码

        // 验证前台输入的验证码与后台生成的验证码是否一致
        if (code.equalsIgnoreCase(authCode)) {// 一致
            resultMap.put("result", "OK");
            resultMap.put("message", "");
        } else {// 不一致
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("code.invalid")); // 验证码输入错误
            return resultMap;
        }
        return resultMap;
    }

    /**
     * 指定无访问额权限页面
     * 
     * @return 无访问额权限页面
     */
    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        try {

            logger.debug("Received request to show denied page");
        } catch (Exception e) {
            this.saveExceptionLog("a", "main", AuthAction.class.toString(), e);
            ErrorUtil.printException(e, logger);
        }
        return "auth/user/deniedpage";
    }

    /**
     * 注销
     * 
     * @return 登录页
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        return "auth/user/loginpage";
    }

    /**
     * 超过规定时间未操作本系统则退出
     * 
     * @return 登录页
     * @author 牛华凤
     * @date 2015年11月18日 上午10:41:09
     */
    @RequestMapping(value = "/sessionTimeout")
    public String sessionTimeout() {
        return "auth/user/sessionTimeout";
    }

}