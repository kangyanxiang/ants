<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title> 忘记密码 </title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<link href="<%=request.getContextPath()%>/css/forgetPwd.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        var browser = navigator.appName;
        var b_version = navigator.appVersion;
        var version = b_version.split(";");
        var trim_Version = version[1].replace(/[ ]/g, "");
        if (browser == "Microsoft Internet Explorer") {
            if (trim_Version == "MSIE6.0" || trim_Version == "MSIE7.0" || trim_Version == "MSIE8.0") {
                window.location.href = '${pageContext.request.contextPath}/html/error/ie.html';
            }
        }
    </script>
</head>
<body>
<div class="top">
    <div class="content">
        <div class="logo">
            <img src="../../../../images/logoDefault.png" alt=""/>
        </div>
        <div class="title">账号管理</div>
        <a class="backLogin" href="<%=request.getContextPath()%>/auth/login">返回登录</a>
    </div>
</div>
<div class="middle">
    <div class="content">
        <div class="process" id="first" style="display: block">
            <div class="title">
                <img src="../../../../images/forgetProcess1.png" alt=""/>
            </div>
            <div class="formContent">
                <input type="text" placeholder="请输入用户名" id="userName" value=""/>
                <input type="text" placeholder="请输入对应的手机号" id="cellphone" value=""/>
                <input type="text" style="float:left;width: 328px;margin-right: 20px" placeholder="请输入验证码" id="authCode" value=""/>
                <button type="button" style="width: 150px;float: left" id="sendSms">获取验证码</button>
                <button type="button" id="next" disabled="disabled">下一步</button>
                <label id="userError"></label>
            </div>
        </div>
        <div class="process" id="second" style="display: none">
            <div class="title">
                <img src="../../../../images/forgetProcess2.png" alt=""/>
            </div>
            <div class="formContent">
                <input type="text" placeholder="请输入新密码" id="newpwd" value=""/>
                <input type="text" placeholder="请再次输入新密码" id="newpwdq" value=""/>
                <button type="button" id="commit">完成</button>
                <label id="pwdError"></label>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/forgetPwd.min.js"></script>
</body>
</html>
