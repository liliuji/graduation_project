package com.controller;

import com.domain.Activity;
import com.domain.Enlist;
import com.domain.User;
import com.service.ActivityService;
import com.service.EnlistService;
import com.service.UserService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ResponseBody
public class EnlistController {

    @Autowired
    private EnlistService enlistService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/showEnlist")
    public ModelAndView showEnlist(ModelAndView modelAndView){
        List<Enlist> enlists = enlistService.getEnlists();
        for(Enlist enlist:enlists){
            User user = userService.getUserByUserId(enlist.getVolunteerid());
            Activity activity = activityService.getActivityById(enlist.getActivityid());
            enlist.setVolunteername(user.getUserName());
            enlist.setActivityname(activity.getActivityname());
        }
        modelAndView.addObject("enlists",enlists);
        modelAndView.setViewName("/Admin/showEnlist");
        return modelAndView;
    }

    @RequestMapping("/updateEnlistStatus")
    public int updateEnlistStatus(String enliststatus,int enlistid){
        int result = enlistService.updateEnlistStatus(enliststatus,enlistid);
        return result;
    }

    @RequestMapping("/deleteEnlist")
    public int deleteEnlist(int enlistid){
        int result = enlistService.deleteEnlistById(enlistid);
        return result;
    }
}
