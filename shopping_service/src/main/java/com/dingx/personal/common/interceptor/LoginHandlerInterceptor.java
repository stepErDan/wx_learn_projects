package com.dingx.personal.common.interceptor;

import com.baomidou.kisso.web.handler.SSOHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements SSOHandlerInterceptor {
    @Override
    public boolean preTokenIsNullAjax(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return false;
    }

    @Override
    public boolean preTokenIsNull(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return false;
    }
}
