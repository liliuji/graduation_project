package com.controller;

import com.domain.Presence;
import com.service.PresenceService;
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
    PresenceService presenceService;

    @RequestMapping("/savePresence")
    public ModelAndView savePresence(HttpServletRequest request,ModelAndView modelAndView){
        Presence presence = new Presence();
        presenceService.savePresence(presence);
        List<Presence> presenceList = presenceService.getPresenceList();
        modelAndView.addObject("presenceList",presenceList);
        modelAndView.setViewName("");
        return modelAndView;
    }

    @RequestMapping("/showPresence")
    public ModelAndView showPresence(ModelAndView modelAndView){
        List<Presence> presenceList = presenceService.getPresenceList();
        modelAndView.addObject("presenceList",presenceList);
        modelAndView.setViewName("");
        return modelAndView;
    }

    @RequestMapping("/updatePresence")
    public int updatePresence(HttpServletRequest request){
        Presence presence = new Presence();
        int result  = presenceService.updatePresence(presence);
        return result;
    }

    @RequestMapping("/deletePresence")
    public int deletePresence(HttpServletRequest request){
        int presenceId = NumberUtils.toInt(request.getParameter("presenceId"));
        int result  = presenceService.deletePresence(presenceId);
        return result;
    }

}
