package com.controller;

import com.course.exception.CustomException;
import com.domain.Activity;
import com.domain.User;
import com.service.ActivityService;
import com.service.UserService;
import com.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(HttpServletRequest request) throws CustomException {
        Map<String,Object>          map         = new HashMap<>();
        try {
            MultipartHttpServletRequest mreq        = (MultipartHttpServletRequest)request;
            MultipartFile               file        = mreq.getFile("file");

            String uplodeImgPath = request.getSession().getServletContext().getRealPath("uploadImg/");
            String imgType = file.getContentType();
            long imgSize = file.getSize();
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            int lastIndexOf = StringUtils.lastIndexOf(fileName,".");
            String filePathSuffix  = StringUtils.substring(fileName,lastIndexOf+1).toUpperCase();
            if(!StringUtils.contains(imgType,"image/")) {
                throw new CustomException("请上传图片!");
            }
            if(imgSize>1048576) {
                throw new CustomException("文件大小超过一兆!");
            }
            if(!StringUtils.equals(filePathSuffix,"BMP")
               &&!StringUtils.equals(filePathSuffix,"JPG")
               &&!StringUtils.equals(filePathSuffix,"PNG")
               &&!StringUtils.equals(filePathSuffix,"GIF")){
                throw new CustomException("请上传'BMP'、'JPG'、'PNG'、'GIF'等类型图片!");
            }
            File uplodeFile = new File(uplodeImgPath+fileName);
            if (!uplodeFile.getParentFile().exists()){
                uplodeFile.getParentFile().mkdirs();
            }
            file.transferTo(uplodeFile);
            String projectPath = "C:/Users/liuji.li/Desktop/study/graduation_project/src/main"
                                 + "/webapp/uploadImg/";
            uplodeFile = new File(projectPath+fileName);
            if (uplodeFile.getParentFile().exists()){
                mreq.getFile("file").transferTo(uplodeFile);
            }
            map.put("uploadFlag",true);
            map.put("imgPath","uploadImg/"+fileName);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        return map;
    }

    @RequestMapping("/saveActivity")
    public Map addActivity(HttpServletRequest request) throws ParseException {
        Activity activity = new Activity();
        activity.setActivityname(request.getParameter("activityName"));
        activity.setActivitydate(request.getParameter("activityDate"));
        activity.setActivitylocation(request.getParameter("activityAddress"));
        activity.setDeadline(request.getParameter("Adeadline"));
        String Aintime = request.getParameter("Aintime");
        String Aouttime = request.getParameter("Aouttime");
        activity.setActivitystartdate(request.getParameter("Aintime"));
        activity.setActivityenddate(request.getParameter("Aouttime"));
        activity.setDemand(NumberUtils.toInt(request.getParameter("Apcount")));
        activity.setActivityrequirement(request.getParameter("Arequest"));
        activity.setActivityimgpath(request.getParameter("imgPath"));
        User           user         =  (User)request.getSession().getAttribute("user");
        activity.setByuserid(user.getUserId());
        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        activity.setReleasetime(nowDate);
        int result = activityService.saveActivity(activity);
        Map resultMap = new HashMap();
        if(result>0){
            resultMap.put("result","活动添加成功!");
        }else{
            resultMap.put("result","活动添加失败!");
        }
        return resultMap;
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
    public int deleteActivity(HttpServletRequest request){
        int activityId = NumberUtils.toInt(request.getParameter("activityId"));
        int result = activityService.deleteActivityById(activityId);
        return result;
    }

}
