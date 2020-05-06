package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping("/tip")
    public String tip(){
        return "/Admin/tip";
    }

    @RequestMapping("/addActivity")
    public String addActivity(){
        return "/Admin/addActivity";
    }

    @RequestMapping("/addNotice")
    public String addNotice(){
        return "/Admin/addNotice";
    }

    @RequestMapping("/addPresence")
    public String addPresence(){
        return "/Admin/addPresence";
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request){
        String errorMsg = request.getParameter("errorMsg");
        request.setAttribute("errorMsg",request.getAttribute("errorMsg"));
        return "/error?errorMsg="+errorMsg;
    }
}
