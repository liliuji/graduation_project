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
    public int updateFeedback(Feedback feedback){
        String replyDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        feedback.setReplydate(replyDate);
        int result = feedbackService.updateFeedback(feedback);
        return result;
    }

    @RequestMapping("/deleteFeeback")
    public int deleteFeeback(int feedbackid){
        int result = feedbackService.deleteFeeback(feedbackid);
        return result;
    }

}
