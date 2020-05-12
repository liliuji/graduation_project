package com.controller;

import com.course.exception.CustomException;
import com.domain.Activity;
import com.domain.Enlist;
import com.domain.Feedback;
import com.domain.Notice;
import com.domain.Presence;
import com.domain.Volunteer;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @RequestMapping("/volunteer/index")
    public ModelAndView index(ModelAndView modelAndView){
        List<Notice> noticeList = noticeService.getNoticeList();
        modelAndView.addObject("noticeList",noticeList);
        modelAndView.setViewName("/volunteer/index");
        return modelAndView;
    }

    @RequestMapping("/volunteer/noticeInfo")
    public ModelAndView showNotice(@RequestParam(value = "noticeId", required = false, defaultValue = "0") String noticeId){
        Notice notice = noticeService.getNoticeById(NumberUtils.toInt(noticeId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("notice",notice);
        modelAndView.setViewName("/volunteer/notice");
        return modelAndView;
    }
    @RequestMapping("/volunteer/login")
    public String volunteerLogin(){
        return "/volunteer/login";
    }

    @RequestMapping("/volunteer/presenceList")
    public ModelAndView getPresenceList(ModelAndView modelAndView){
        List<Presence> presenceList = presenceService.getPresenceList();
        modelAndView.addObject("presenceList",presenceList);
        modelAndView.setViewName("/volunteer/show");
        return modelAndView;
    }

    @RequestMapping("/volunteer/presenceInfo")
    public ModelAndView showPresenceInfo(ModelAndView modelAndView,@RequestParam(value = "presenceId", required = false, defaultValue = "0") String presenceId){
        Presence presence = presenceService.getPresenceById(NumberUtils.toInt(presenceId));
        Activity activity = activityService.getActivityByName(presence.getActivename());
        modelAndView.addObject("activityDate",activity.getActivitydate());
        modelAndView.addObject("presence",presence);
        modelAndView.setViewName("/volunteer/showing");
        return modelAndView;
    }

    @RequestMapping("/volunteer/join")
    public ModelAndView joinList(ModelAndView modelAndView) throws CustomException {
        List<Activity> activitys = new ArrayList<>();
        try {
            List<Activity> activityList = activityService.getActivityList();
            long nowTime =  System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for(Activity activity:activityList){
                String endDate = activity.getActivitydate()+" "+activity.getActivityenddate();
                long endtTime = simpleDateFormat.parse(endDate).getTime();
                if(endtTime>nowTime){
                    activitys.add(activity);
                }
            }
            modelAndView.addObject("activitys",activitys);
            modelAndView.setViewName("/volunteer/join");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/volunteer/publish")
    public String publish(){
        return "/volunteer/publish";
    }

    @RequestMapping("/volunteer/savePublish")
    public ModelAndView savePublish(ModelAndView modelAndView,Feedback feedback) throws CustomException {
        try {
            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            feedback.setFeedbackdate(nowTime);
            int result = feedbackService.saveFeedback(feedback);
            modelAndView.addObject("savePublishFlag",result);
            modelAndView.setViewName("/volunteer/publish");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/volunteer/joining")
    public ModelAndView joining(ModelAndView modelAndView,
                                HttpSession session, @RequestParam(value = "activityId", required = false, defaultValue = "0") String activityId){
        Activity activity = activityService.getActivityById(NumberUtils.toInt(activityId));
        Volunteer volunteer = (Volunteer)session.getAttribute("volunteer");
        if(volunteer!=null){
            String enlistStatus = enlistService.getEnlistStatus(activity.getActivityid(),volunteer.getUserId());
            modelAndView.addObject("enliststatus",enlistStatus);
        }
        modelAndView.addObject("activity",activity);
        modelAndView.setViewName("/volunteer/joining");
        return modelAndView;
    }

    @RequestMapping("/volunteer/loginmessage")
    public String loginmessage(){
        return "/volunteer/loginmessage";
    }
}
