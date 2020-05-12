package com.controller;

import com.course.exception.CustomException;
import com.domain.Activity;
import com.domain.Enlist;
import com.domain.Presence;
import com.domain.User;
import com.service.ActivityService;
import com.service.EnlistService;
import com.service.PresenceService;
import com.service.UserService;
import com.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private EnlistService enlistService;
    @Autowired
    private PresenceService presenceService;

    @RequestMapping("/saveActivity")
    public int addActivity(Activity activity) throws ParseException, CustomException {
        int result;
        try {
            Activity activity1 = activityService.getActivityByName(activity.getActivityname());
            if(activity1!=null){
                throw new CustomException("该活动已存在,请确认!");
            }
            String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            activity.setReleasetime(nowDate);
            result = activityService.saveActivity(activity);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        return result;
    }

    @RequestMapping("/myActivity")
    public ModelAndView getActivity(HttpServletRequest request, ModelAndView modelAndView)
            throws CustomException {
        User           user         =  (User)request.getSession().getAttribute("user");
        List<Activity> activityList = activityService.getActivityByUserId(user.getUserId());
        List<Activity> noStartList = new ArrayList<>();
        List<Activity> finishList = new ArrayList<>();
        List<Activity> underwayList = new ArrayList<>();
        try {
            long nowTime =  System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for(Activity activity:activityList){
                activity.setByUserName(user.getUserName());
                String startDate = activity.getActivitydate()+" "+activity.getActivitystartdate();
                long startTime = simpleDateFormat.parse(startDate).getTime();
                String endDate = activity.getActivitydate()+" "+activity.getActivityenddate();
                long endtTime = simpleDateFormat.parse(endDate).getTime();
                if(nowTime<startTime){
                    noStartList.add(activity);
                }else if(nowTime>endtTime){
                    finishList.add(activity);
                }else{
                    underwayList.add(activity);
                }
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        modelAndView.addObject("noStartList", noStartList);
        modelAndView.addObject("finishList", finishList);
        modelAndView.addObject("underwayList", underwayList);

        modelAndView.setViewName("/Admin/myActivity");
        return modelAndView;
    }

    @RequestMapping("/showActivity")
    public Map showActivity(HttpServletRequest request){
        int activityId = NumberUtils.toInt(request.getParameter("activityId"));
        Activity activity = activityService.getActivityById(activityId);
        activity.setByUserName(userService.getUserByUserId(activity.getByuserid()).getUserName());
        Map resultMap = new HashMap();
        resultMap.put("activity", activity);
        return resultMap;
    }

    @RequestMapping("/deleteActivity")
    public int deleteActivity(int activityId) throws CustomException {
      //  int activityId = NumberUtils.toInt(request.getParameter("activityId"));
        List<Enlist> enlists = enlistService.getEnlistsByActivityId(activityId);
        if(!CollectionUtils.isEmpty(enlists)){
            throw new CustomException("该活动有报名信息，不允许删除！");
        }
        Activity activity = activityService.getActivityById(activityId);
        List<Presence> presences = presenceService.getPresenceListByActivity(activity.getActivityname());
        if(!CollectionUtils.isEmpty(presences)){
            throw new CustomException("该活动有风采信息，不允许删除！");
        }
        int result = activityService.deleteActivityById(activityId);
        return result;
    }

}
