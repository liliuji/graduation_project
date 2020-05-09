package com.controller;

import com.domain.Activity;
import com.domain.Enlist;
import com.domain.Feedback;
import com.domain.Notice;
import com.domain.Presence;
import com.service.ActivityService;
import com.service.EnlistService;
import com.service.FeedbackService;
import com.service.NoticeService;
import com.service.PresenceService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    NoticeService   noticeService;
    @Autowired
    PresenceService presenceService;
    @Autowired
    ActivityService activityService;
    @Autowired
    private EnlistService enlistService;
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        List<Notice> noticeList = noticeService.getNoticeList();
        modelAndView.addObject("noticeList",noticeList);
        modelAndView.setViewName("/volunteer/index");
        return modelAndView;
    }

    @RequestMapping("/noticeInfo")
    public ModelAndView showNotice(@RequestParam(value = "noticeId", required = false, defaultValue = "0") String noticeId){
        Notice notice = noticeService.getNoticeById(NumberUtils.toInt(noticeId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("notice",notice);
        modelAndView.setViewName("/volunteer/notice");
        return modelAndView;
    }
    @RequestMapping("/volunteerLogin")
    public String volunteerLogin(){
        return "/volunteer/login";
    }

    @RequestMapping("/presenceList")
    public ModelAndView getPresenceList(ModelAndView modelAndView){
        List<Presence> presenceList = presenceService.getPresenceList();
        modelAndView.addObject("presenceList",presenceList);
        modelAndView.setViewName("/volunteer/show");
        return modelAndView;
    }

    @RequestMapping("/presenceInfo")
    public ModelAndView showPresenceInfo(ModelAndView modelAndView,@RequestParam(value = "noticeId", required = false, defaultValue = "0") String presenceId){
        Presence presence = presenceService.getPresenceById(NumberUtils.toInt(presenceId));
        modelAndView.addObject("presence",presence);
        modelAndView.setViewName("/volunteer/showing");
        return modelAndView;
    }

    @RequestMapping("/join")
    public ModelAndView joinList(ModelAndView modelAndView){
        List<Activity> activityList = activityService.getActivityList();
        modelAndView.addObject("activityList",activityList);
        modelAndView.setViewName("/volunteer/join");
        return modelAndView;
    }

    @RequestMapping("/publish")
    public String publish(){
        return "/volunteer/publish";
    }

    @RequestMapping("/savePublish")
    public int savePublish(Feedback feedback){
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int result = feedbackService.saveFeedback(feedback);
        return result;
    }

    @RequestMapping("/joining")
    public ModelAndView joining(ModelAndView modelAndView,@RequestParam(value = "activityId", required = false, defaultValue = "0") String activityId){
        Activity activity = activityService.getActivityById(NumberUtils.toInt(activityId));
        modelAndView.addObject("activity",activity);
        modelAndView.setViewName("/volunteer/joining");
        return modelAndView;
    }

    @RequestMapping("/loginmessage")
    public String loginmessage(){
        return "/volunteer/loginmessage";
    }
}
