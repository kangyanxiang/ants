package com.awifi.toe.base.constants;

import java.util.regex.Pattern;


/**   
 * @Description:  常量
 * @Title: Constants.java 
 * @Package com.awifi.toe.base.constants 
 * @author kangyanxiang
 * @date 2015年6月23日 下午5:33:06
 * @version V1.0   
 */
public class Constants {

	
	/** ========================= 通用模块 ================================== **/
    
    /** 黑名单 */
    public static final String BLACK = "black";
    /** 白名单 */
    public static final String WHITE = "white";
    
	/** ========================== 校验模块 ================================= **/
    /**ip地址*/
    public static final String IP_PATTERN = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
    /**ip地址*/
    public static final Pattern IP_PATTERN_COMPILE = Pattern.compile(IP_PATTERN);
   
    /** 数字 */
    public static final String NUMBER_PATTERN = "^[0-9A-F]{12}$";
    /** 数字 */
    public static final Pattern NUMBER_PATTERN_COMPILE = Pattern.compile(NUMBER_PATTERN);
    /** MAC地址 */
    public static final String MAC_PATTERN = "^[0-9A-F]{12}$";
    /** 邮箱 */
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"; 
    /** 时 */
    public static final String HOUR_PATTERN = "^([1]?[0-9])|2[0-3]$";
    /** 用户名： 1-50位字符，包括字母、数字、下划线、连接符 */
    public static final String USER_NAME_PATTERN = "^[0-9a-zA-Z\\_\\-]{1,50}$";//第十一迭代修改
    /** 1开头的11位符合手机号码规则的数字(手机号) */
    public static final String CELLPHONE_REGEX = "^(1[0-9]{10})?$";//十四迭代修改
    /** 1-20位字符，包括字母、汉字(姓名/真实姓名) */
    public static final String USER_REAL_NAME_PATTERN = "^[a-zA-Z\u4e00-\u9fa5]{1,20}$";
    /** 1-20位汉字、字母、数字(部门) */
    public static final String USER_DEPT_NAME_PATTERN = "^[0-9a-zA-Z\u4e00-\u9fa5]{1,20}$";
    /** 身份证，18位数字或17位数字最后一位字母X  */
    public static final String USER_IDENTITY_CARD = "^[0-9]{17}([0-9]|X){1}$";
    /** 护照，1-20位字母、数字  */
    public static final String USER_PASSPORT = "^[0-9a-zA-Z]{1,20}$";
    /** 1开头的11位符合手机号码规则的数字(手机号) */
    public static final String USER_PHONE_PATTERN = "^(1[3|4|5|7|8][0-9][0-9]{8})?$";
	/** 请求来源 */
    public static final String REQUEST_TYPE_2GPLAT = "TOE_PLATFORM"; 
    /** 客户名正则 */
    public static final String CUSTOMER_NAME_PATTERN = "^[0-9a-zA-Z\u4e00-\u9fa5_\\(\\),\\.（），。@]{1,50}$";//十四迭代修改 1-50位字符可由中英文、数字及“_”、()、（）、,、，、.、。、@组成
    /** 密码  */                                  
    public static final String USER_PASSWORD = "^[0-9a-zA-Z_@\\$\\-]{1,50}$";
    /**平台名称正则*/
    public static final String PLATFORM_NAME = "^[0-9a-zA-Z\u4e00-\u9fa5\\_]{1,50}$";//十一迭代新增 1-50位字符，包括汉字、字母、数字、下划线，不含特殊字符
    /** 账号正则 */
    public static final String ACCOUNT_PATTERN = "^[0-9a-zA-Z\\-\\_]{1,50}$";//十一迭代修改 1-50位字符，包括字母、数字、下划线、连接符
    /** 联系人正则 */
    public static final String CONTACT_PATTERN = "^[a-zA-Z\u4e00-\u9fa5]{0,20}$";//十四迭代，联系人非必填
    /** 联系方式 */
    public static final String CONTACTWAY_PATTERN = "^(1[3|4|5|7|8][0-9][0-9]{8})?$";
    /** 项目名正则 */
    public static final String PROJECT_PATTERN = "^[0-9a-zA-Z\u4e00-\u9fa5\\_]{1,50}$";
    /** 域正则 */
    public static final String DOMAIN = "^[a-z0-9]{1,}([-\\.]{1}[a-z0-9]{1,}){0,}[\\.]{1}[a-z]{1,4}$";
	/** 调用接入接口返回结果 */
    public static final String OK = "OK";
    /** 调用接入接口返回结果 */
    public static final String FAIL = "FAIL";
	 
	/** portal协议超时时间 */
    public static final int AUTH_PORTAL_TIMEOUT = 15;
	
    /** api接口 超时时间 */
    public static final long API_TOKEN_TIMEOUT = 600;
	
}
