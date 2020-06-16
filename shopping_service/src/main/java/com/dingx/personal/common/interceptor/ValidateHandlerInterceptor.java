package com.dingx.personal.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.dingx.personal.common.annontation.IgnoreLogin;
import com.dingx.personal.common.constant.GeneralAttributes;
import com.dingx.personal.service.base.ITokenService;
import com.dingx.personal.vo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * token验证拦截器
 * @Date : 2020年5月31日 21:51:44
 */
public class ValidateHandlerInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(ValidateHandlerInterceptor.class);

    @Autowired
    private ITokenService iTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        IgnoreLogin ignoreLogin = method.getAnnotation(IgnoreLogin.class);

        //忽略注解
        if(ignoreLogin != null){
            return true;
        }

        Result result = new Result();

        //从header中获取token
        String token = request.getHeader(GeneralAttributes.TOKEN_AUTH_VALIDATE);

        //验证token是否有效
        boolean flag = false;
        try {
            flag = iTokenService.checkToken(token);
        }catch (Exception e){
            response.setStatus(GeneralAttributes.RESULT_ERROR);
            logger.error(e.getMessage());
        }
        if (!flag){
            result.setCode(GeneralAttributes.TOKEN_TIMEOUT_ERROR);
            result.setMsg("token已过期");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(JSON.toJSONString(result));
            response.getWriter().flush();
            return false;
        }
        return true;
    }

}
