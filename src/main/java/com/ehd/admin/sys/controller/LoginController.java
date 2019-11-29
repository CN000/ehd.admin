package com.ehd.admin.sys.controller;

import com.ehd.admin.common.AjaxJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {
    @GetMapping({"/", ""})
    public String welcome(HttpServletRequest request) {
        return "redirect:/login";

    }
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        return "sys/login";
    }

    @RequestMapping("/toLogin")
    @ResponseBody
    public AjaxJson toLogin(HttpServletRequest request){
        AjaxJson ajaxJson = new AjaxJson();
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        if(userName!=""&&userName!=null){
            try{
                UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
                SecurityUtils.getSubject().login(token);
                ajaxJson.setMsg("登录成功");
                ajaxJson.setSuccess(true);
            }catch (IncorrectCredentialsException e){
                ajaxJson.setMsg("密码错误");
                ajaxJson.setSuccess(true);
            }catch (Exception e){
                ajaxJson.setMsg("密码错误");
                ajaxJson.setSuccess(true);
            }
        }else{
            ajaxJson.setMsg("密码错误");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }
}
