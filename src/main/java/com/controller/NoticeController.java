package com.controller;

import com.domain.Notice;
import com.service.NoticeService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/saveNotice")
    public ModelAndView saveNotice(HttpServletRequest request,ModelAndView modelAndView)
            throws UnsupportedEncodingException {
        String noticeTitle = new String(request.getParameter("noticeTitle").getBytes("iso-8859-1"), "utf-8");
        String noticeContent = new String(request.getParameter("noticeContent").getBytes("iso-8859-1"), "utf-8");
        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Notice notice = new Notice();
        notice.setNoticetitle(noticeTitle);
        notice.setNoticecontent(noticeContent);
        notice.setNoticecreatedate(nowDate);
        int result = noticeService.saveNotice(notice);
        List<Notice>  noticeList = noticeService.getNoticeList();
        modelAndView.addObject("noticeList",noticeList);
        modelAndView.setViewName("/Admin/myNotice");
        return modelAndView;
    }

    @RequestMapping("/showNotice")
    public ModelAndView showNotice(ModelAndView modelAndView){
        List<Notice>  noticeList = noticeService.getNoticeList();
        modelAndView.addObject("noticeList",noticeList);
        modelAndView.setViewName("/Admin/myNotice");
        return modelAndView;
    }

    @RequestMapping("updateNotice")
    public int updateNotice(HttpServletRequest request){
        String noticeTitle = request.getParameter("noticeTitle");
        String noticeContent = request.getParameter("noticeContent");
        int noticeId = NumberUtils.toInt(request.getParameter("noticeId"));
        int result = noticeService.updateNotice(noticeTitle,noticeContent,noticeId);
        return result;
    }

    @RequestMapping("deleteNotice")
    public int deleteNotice(HttpServletRequest request){
        int noticeId = NumberUtils.toInt(request.getParameter("noticeId"));
        int result = noticeService.deleteNotice(noticeId);
        return result;
    }

}
