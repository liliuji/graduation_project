package com.controller;

import com.course.exception.CustomException;
import com.domain.Enlist;
import com.domain.Notice;
import com.domain.Presence;
import com.domain.User;
import com.domain.Volunteer;
import com.service.EnlistService;
import com.service.NoticeService;
import com.service.PresenceService;
import com.service.UserService;
import com.service.VolunteerService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;
    @Autowired
    private UserService      userService;
    @Autowired
    private NoticeService    noticeService;
    @Autowired
    private EnlistService enlistService;
    @Autowired
    private PresenceService presenceService;

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
    public int deleteVolunteer(HttpServletRequest request) throws CustomException {
        int volunteerId = NumberUtils.toInt(request.getParameter("volunteerId"));
        List<Enlist> enlists = enlistService.getEnlistsByVolunteerId(volunteerId);
        if(!CollectionUtils.isEmpty(enlists)){
            throw new CustomException("该志愿者有报名信息，不能删除!");
        }
        Volunteer volunteer = volunteerService.getVolunteerById(volunteerId);
        List<Presence> presences = presenceService.getPresenceListByVolunteerAccount(volunteer.getAccount());
        if(!CollectionUtils.isEmpty(presences)){
            throw new CustomException("该志愿者有风采信息，不能删除!");
        }
        int result = userService.deleteVolunteerById(volunteerId);
        return result;
    }

    @RequestMapping("/volunteer/loginmessageInfo")
    public ModelAndView volunteerRegister(ModelAndView modelAndView,Volunteer volunteer)
            throws CustomException {
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try {
            Volunteer volunteer1 = volunteerService.getVolunteerByAccount(volunteer.getAccount());
            if(volunteer1!=null){
                throw new CustomException("该账号已存在！");
            }
            volunteer.setCreateDate(nowTime);
            volunteer.setUserType("volunteer");
            volunteerService.volunteerRegister(volunteer);
            modelAndView.setViewName("/volunteer/login");
            modelAndView.addObject("loginmessageSuccess","注册成功，请登录！");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/volunteer/volunteerCheckLogin")
    public ModelAndView volunteerCheckLogin(HttpSession session,ModelAndView modelAndView, String account, String password)
            throws CustomException {
        Volunteer volunteer = volunteerService.getVolunteer(account,password);
        if(volunteer!=null){
            session.setAttribute("volunteer",volunteer);
            session.setAttribute("account",volunteer.getAccount());
            List<Notice> noticeList = noticeService.getNoticeList();
            modelAndView.addObject("noticeList",noticeList);
            modelAndView.setViewName("/volunteer/index");
        }else{
            throw new CustomException("账号或密码不正确!");
        }
        return modelAndView;
    }
}
