package com.awifi.toe.auth.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

/**
 * @Description:注销
 * @Title: CustomLogoutSuccessHandler.java
 * @Package com.awifi.toe.auth.handler
 * @author kangyanxiang
 * @date 2015年5月7日 下午3:09:32
 * @version V1.0
 */
public class CustomLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

    /** token */
    private JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        if (authentication == null) {
            getRedirectStrategy().sendRedirect(request, response, getDefaultTargetUrl());
            return;
        }
        // 1. 删除数据库保存的token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails == null) {
            getRedirectStrategy().sendRedirect(request, response, getDefaultTargetUrl());
            return;
        }
        String username = userDetails.getUsername();
        if (StringUtils.isNotBlank(username)) {
            getRedirectStrategy().sendRedirect(request, response, getDefaultTargetUrl());
            return;
        }
        jdbcTokenRepositoryImpl.removeUserTokens(username);
        getRedirectStrategy().sendRedirect(request, response, getDefaultTargetUrl());
    }

    public void setJdbcTokenRepositoryImpl(JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl) {
        this.jdbcTokenRepositoryImpl = jdbcTokenRepositoryImpl;
    }
}