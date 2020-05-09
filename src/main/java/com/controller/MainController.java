package com.controller;

import com.course.exception.CustomException;
import com.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/")
    public String login(){
        return "/Admin/login";
    }

    @RequestMapping("/tip")
    public String tip(){
        return "/Admin/tip";
    }

    @RequestMapping("/addActivity")
    public String addActivity(){
        return "/Admin/addActivity";
    }

    @RequestMapping("/addNotice")
    public String addNotice(){
        return "/Admin/addNotice";
    }

    @RequestMapping("/addPresence")
    public String addPresence(){
        return "/Admin/addPresence";
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request){
        String errorMsg = request.getParameter("errorMsg");
        request.setAttribute("errorMsg",request.getAttribute("errorMsg"));
        return "/error?errorMsg="+errorMsg;
    }

    @RequestMapping("/uploadImage")
    @ResponseBody
    public Map<String,Object> uploadImage(HttpServletRequest request) throws CustomException {
        Map<String,Object>          map         = new HashMap<>();
        try {
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)request;
            MultipartFile               file = mreq.getFile("file");

            String uplodeImgPath = request.getSession().getServletContext().getRealPath("uploadImg/");
            String imgType = file.getContentType();
            long imgSize = file.getSize();
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            int lastIndexOf = StringUtils.lastIndexOf(fileName, ".");
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
            File uplodeFile = new File(uplodeImgPath + fileName);
            if (!uplodeFile.getParentFile().exists()){
                uplodeFile.getParentFile().mkdirs();
            }
            file.transferTo(uplodeFile);
            String projectPath = "C:/Users/liuji.li/Desktop/study/graduation_project/src/main"
                                 + "/webapp/uploadImg/";
            uplodeFile = new File(projectPath+fileName);
            if (uplodeFile.getParentFile().exists()){
                mreq = (MultipartHttpServletRequest)request;
                file =  mreq.getFile("file");
                file.transferTo(uplodeFile);
            }
            map.put("uploadFlag",true);
            map.put("imgPath","uploadImg/"+fileName);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        return map;
    }
}
