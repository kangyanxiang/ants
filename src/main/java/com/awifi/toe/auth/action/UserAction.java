package com.awifi.toe.auth.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awifi.toe.auth.model.User;
import com.awifi.toe.auth.service.RoleService;
import com.awifi.toe.auth.service.UserService;
import com.awifi.toe.base.action.BaseAction;
import com.awifi.toe.base.constants.ParamName;
import com.awifi.toe.base.http.HttpRequest;
import com.awifi.toe.base.util.ErrorUtil;
import com.awifi.toe.base.util.MessageUtil;
import com.awifi.toe.base.util.SessionUtil;
import com.awifi.toe.base.util.ValidateUtil;
import com.awifi.toe.base.util.rules.Required;
import com.awifi.toe.base.util.rules.Rule;

/**
 * @Description: 用户控制层
 * @Title: UserAction.java
 * @Package com.awifi.toe.auth.action
 * @author kangyanxiang
 * @date 2015年4月30日 上午11:01:30
 * @version V1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
@SuppressWarnings("rawtypes")
public class UserAction extends BaseAction {

    /** 用户业务层 */
    @Resource(name = "userService")
    private UserService userService;

    /** 角色业务层 */
    @Resource(name = "roleService")
    private RoleService roleService;

    
    /**
     * 通过用户名获取用户信息
     * @param userName 用户名
     * @return json
     * @author kangyanxiang
     * @date 2015年6月23日 下午4:49:30
     */
    @RequestMapping(value = "/getuserbyname")
    @ResponseBody
    public Map getUserByName(String userName) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            // 请求参数 校验
            ValidateUtil vu = new ValidateUtil();
            vu.add("userName", userName, ParamName.USER_NAME, new Rule[] { new Required() });
            String validStr = vu.validateString();
            if (StringUtils.isNotBlank(validStr)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", validStr);
                return resultMap;
            }
            User user = userService.findUserByUserName(userName);
            resultMap.put("result", "OK");
            resultMap.put("data", user);
            resultMap.put("message", "");
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "user", userService.getClass().toString(), e);
        }
        return resultMap;
    }

    /**
     * 生成登录页验证码
     * @param response 请求响应
     * @return 验证码
     * @author 牛华凤
     * @date 2015年7月13日 下午5:20:12
     */
    @RequestMapping(value = "/generateauthcode")
    public String generateAuthCode(HttpServletResponse response) {
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 在内存中创建图象
        int width = 126;
        int height = 48;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        // 生成随机类
        Random random = new Random();

        // 设定背景色
        // logger.debug("提示：getRandColor= " + getRandColor(200, 250));
        // g.setColor(getRandColor(200, 250));
        g.setColor(new Color(104, 104, 104));
        g.fillRect(0, 0, width, height);

        // 设定字体
        g.setFont(new Font("宋体", Font.PLAIN, 30));

        // 画边框
        // g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        // g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String allcs = "0123456789";
        // String allcs = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // char[] cs = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
        // 'h', 'i', 'j', 'k', 'A', 'B', 'C' , 'D', 'E', 'F' ,'G' ,'H' ,'I' ,'W'};
        char[] cs = allcs.toCharArray();
        // 取随机产生的认证码(6位数字)
        String sRand = "";
        Color whileColor = new Color(255, 255, 255);
        for (int i = 0; i < 6; i++) {
            char c = cs[random.nextInt(cs.length)];
            String rand = new Character(c).toString();
            sRand += rand;
            // 将认证码显示到图象中
            // g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.setColor(whileColor);
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 22, 35);
        }

        // 将认证码存入session
        session.setAttribute("authCode", sRand); // 这行代码是我们关注的重点
        // 图象生效
        g.dispose();

        // 输出图象到页面
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
        } finally {
            // 解决以调用错误
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                ErrorUtil.printException(e, logger);
            }
        }
        return null;
    }
    
    /**
     * 获取当前登录用户所对应的用户名
     * @param response 请求响应 
     * @return 用户名
     * @author kangyanxiang
     * @date 2015年8月12日 上午11:21:01
     */
    @RequestMapping(value = "/getcurusername")
    @ResponseBody
    public Map getCurUserName(HttpServletResponse response) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            String userName = SessionUtil.getCurUserName();
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("userName", userName);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "user", userService.getClass().toString(), e);
        }
        return resultMap;
    }

    /**
     * 获取当前登录用户所对应的用户信息
     * @param response 请求响应 
     * @return 用户名
     * @author kangyanxiang
     * @date 2015年8月12日 上午11:21:01
     */
    @RequestMapping(value = "/getcuruserinfo")
    @ResponseBody
    public Map getCurUserInfo(HttpServletResponse response) {
        String interfaceCode = "";
        resultMap = new HashMap<String, Object>();
        try {
            // 请求方法类型 校验
            if (request.getMethod().equals(HttpRequest.METHOD_POST)) {
                resultMap.put("result", "FAIL");
                resultMap.put("message", MessageUtil.getMessage("request_illegal"));// 非法请求！
                return resultMap;
            }
            String userName = SessionUtil.getCurUserName();
            User user = userService.getInfoByName(userName);
            resultMap.put("result", "OK");
            resultMap.put("message", "");
            resultMap.put("data", user);
        } catch (Exception e) {
            ErrorUtil.printException(e, logger);
            resultMap.put("result", "FAIL");
            resultMap.put("message", MessageUtil.getMessage("system_exception") + interfaceCode); // 系统异常！异常编号：
            saveExceptionLog(interfaceCode, "user", userService.getClass().toString(), e);
        }
        return resultMap;
    }
    
    /**
     * 跳转到忘记密码页面
     * @return 文件相对路径
     * @author kangyanxiang  
     * @date 2016年7月10日 下午11:39:06
     */
    @RequestMapping(value = "/forgetpwdpage")
    public String forgetPwdPage() {
        return "auth/user/forgetPwd";
    }
    
}