package com.ehd.admin.user.controller;

import com.ehd.admin.user.domain.FrontUser;
import com.ehd.admin.user.service.FrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontUser")
public class FrontUserController {
    @Autowired
    private FrontUserService frontUserService;
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model){
        int RowBounds=0;
        int rowBounds=20;
        Map<String,String> map = new HashMap<String, String>();
        List<FrontUser> frontUserList = frontUserService.getFrontUserList(RowBounds,rowBounds,map);
        model.addAttribute("frontUserList",frontUserList);
        System.out.println("user list coming");
        return "user/list";
    }
    @RequestMapping("/createChiIds")
    public String createChiIds(){
        frontUserService.createChiIds();
        return "";
    }
}
