package com.controller;

import com.domain.Feedback;
import com.service.FeedbackService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping("/showFeedback")
    public ModelAndView showFeedback(ModelAndView modelAndView){
        List<Feedback> feedbackList = feedbackService.getFeedbackList();
        modelAndView.addObject("feedbackList",feedbackList);
        modelAndView.setViewName("/Admin/showFeedback");
        return modelAndView;
    }

    @RequestMapping("/updateFeedback")
    public int updateFeedback(HttpServletRequest request){
        String feedbackReply = request.getParameter("feedbackReply");
        String replyDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int feedbackId = NumberUtils.toInt(request.getParameter("feedbackId"));
        int result = feedbackService.updateFeedback(feedbackReply,replyDate,feedbackId);
        return result;
    }

    @RequestMapping("/deleteFeeback")
    public int deleteFeeback(HttpServletRequest request){
        int feedbackId = NumberUtils.toInt(request.getParameter("feedbackId"));
        int result = feedbackService.deleteFeeback(feedbackId);
        return result;
    }

}
