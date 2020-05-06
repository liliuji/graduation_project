package com.controller;

import com.domain.Enlist;
import com.service.EnlistService;
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

    @RequestMapping("/showEnlist")
    public ModelAndView showEnlist(ModelAndView modelAndView){
        List<Enlist> enlists = enlistService.getEnlists();
        modelAndView.addObject("enlists",enlists);
        modelAndView.setViewName("/Admin/showEnlist");
        return modelAndView;
    }

    @RequestMapping("/updateEnlistStatus")
    public int updateEnlistStatus(HttpServletRequest request){
        String enlistStatus = request.getParameter("enlistStatus");
        int enlistId = NumberUtils.toInt(request.getParameter("enlistId"));
        int result = enlistService.updateEnlistStatus(enlistStatus,enlistId);
        return result;
    }

    @RequestMapping("/deleteEnlist")
    public int deleteEnlist(HttpServletRequest request){
        int enlistId = NumberUtils.toInt(request.getParameter("enlistId"));
        int result = enlistService.deleteEnlistById(enlistId);
        return result;
    }
}
