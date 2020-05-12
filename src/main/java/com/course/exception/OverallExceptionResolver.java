package com.course.exception;

import com.controller.UserController;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import sun.security.ssl.Debug;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OverallExceptionResolver implements HandlerExceptionResolver {
    public static Logger logger = Logger.getLogger(UserController.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        //handler为当前处理器适配器执行的对象
        String message = e.getMessage();
        try {
             if(isAjax(request)){
                 request.setAttribute("errorMsg", message);
                 response.sendRedirect(request.getContextPath()+"/error.jsp?errorMsg="+message);
              }else {
                 //跳转到相应的处理页面
                 modelAndView.addObject("errorMsg", message);
                 modelAndView.setViewName("error");
              }
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return modelAndView;
    }

    private Boolean isAjax(HttpServletRequest request) {

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        } else {
            return false;
        }
    }
}
