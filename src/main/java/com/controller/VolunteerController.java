package com.controller;

import com.domain.User;
import com.domain.Volunteer;
import com.service.UserService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class VolunteerController {

    @Autowired
    private UserService userService;

    @RequestMapping("/volunteerInfo")
    public ModelAndView getVolunteerInfo(ModelAndView modelAndView){
        String          userType      = "volunteer";
        List<Volunteer> volunteerInfo = userService.getUserByType(userType);
        modelAndView.addObject("volunteerInfo",volunteerInfo);
        modelAndView.setViewName("/Admin/volunteerInfo");
        return modelAndView;
    }

    @RequestMapping("/showVolunteer")
    public Map showVolunteer(HttpServletRequest request){
        int volunteerId = NumberUtils.toInt(request.getParameter("volunteerId"));
        Map resultMap = new HashMap();
        resultMap.put("volunteer",userService.getUserByUserId(volunteerId));
        return resultMap;
    }

    @RequestMapping("/deleteVolunteer")
    public int deleteVolunteer(HttpServletRequest request){
        int volunteerId = NumberUtils.toInt(request.getParameter("volunteerId"));
        int result = userService.deleteVolunteerById(volunteerId);
        return result;
    }
}
