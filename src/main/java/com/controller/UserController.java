package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import com.course.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;
import com.domain.User;
import com.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/checkLogin")
    public String checkLogin(User user, HttpSession session, HttpServletResponse response) throws Exception {
        String userName = user.getUserName();
        String passWord = user.getPassword();
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            throw new CustomException("用户名和密码不能为空!");
        }
        user = userService.getUser(userName, passWord);
        if (user != null) {
            if(StringUtils.equals(user.getUserType(),"volunteer")){
                throw new CustomException("后台系统只允许管理员登陆！");
            }
            session.setAttribute("userName", userName);
            session.setAttribute("user", user);
            return "/Admin/main";
        } else {
            throw new CustomException("用户名或密码不正确,请重新登录!");
        }
    }

    @RequestMapping("/alterPsw")
    @ResponseBody
    public Map<String, Object> alterPsw(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        Map<String, Object> map = new HashMap<>();
        map.put("checkLoginFlag",false);

        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(oldPassword)) {
            map.put("msg","用户名或密码不能为空!");
        }else {
            User user = userService.getUser(userName, oldPassword);
            if(user==null){
                map.put("msg","旧密码不正确!");
            }else{
              int resultRow = userService.updatePassword(userName,newPassword);
              if(resultRow>0){
                  map.put("msg","密码修改成功，请重新登录!");
                  map.put("url","/");
                  map.put("checkLoginFlag",true);
              }else{
                  map.put("msg","密码修改失败!");
              }
            }
        }
        return map;
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("userName");
        session.removeAttribute("passWord");
        session.removeAttribute("user");
        return "/Admin/login";
    }
}
