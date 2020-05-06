package com.course.interceptor;

import com.controller.UserController;
import com.domain.User;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    public static Logger logger = Logger.getLogger(UserController.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object o) throws Exception {
        HttpSession session = request.getSession();
        User        user    = (User)session.getAttribute("user");

        String url = request.getRequestURL().toString();
        if(url.contains("checkLogin")){
            return true;
        }else if(user!=null) {
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {

    }
}
