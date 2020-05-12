package com.controller;

import com.course.exception.CustomException;
import com.domain.Activity;
import com.domain.Presence;
import com.domain.User;
import com.domain.Volunteer;
import com.service.ActivityService;
import com.service.PresenceService;
import com.service.UserService;
import com.service.VolunteerService;
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
public class PresenceController {

    @Autowired
    private PresenceService presenceService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private VolunteerService     volunteerService;

    @RequestMapping("/savePresence")
    public int savePresence(Presence presence) throws CustomException {
        int result;
        try {
            String volunteeraccount = presence.getVolunteeraccount();
            String activename = presence.getActivename();
            Activity activity = activityService.getActivityByName(activename);
            Volunteer volunteer = volunteerService.getVolunteerByAccount(volunteeraccount);
            if(volunteer==null){
                throw new CustomException("没有该志愿者账号，请确认！");
            }
            if(activity==null) {
                throw new CustomException("没有该活动，请确认！");
            }
            result = presenceService.savePresence(presence);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        return result;
    }

    @RequestMapping("/showPresence")
    public ModelAndView showPresence(ModelAndView modelAndView){
        List<Presence> presenceList = presenceService.getPresenceList();
        for(Presence presence:presenceList){
            Activity activity = activityService.getActivityByName(presence.getActivename());
            Volunteer volunteer = volunteerService.getVolunteerByAccount(presence.getVolunteeraccount());
            if(activity!=null){
                presence.setActivename(activity.getActivityname());
            }
            if(volunteer!=null){
                presence.setVolunteeraccount(volunteer.getAccount());
            }
        }
        modelAndView.addObject("presenceList",presenceList);
        modelAndView.setViewName("/Admin/showPresence");
        return modelAndView;
    }

    @RequestMapping("/updatePresence")
    public int updatePresence(Presence presence){
        int result  = presenceService.updatePresence(presence);
        return result;
    }

    @RequestMapping("/deletePresence")
    public int deletePresence(int presenceId){
        int result  = presenceService.deletePresence(presenceId);
        return result;
    }

}
