package com.ehd.admin.recharge.controller;

import com.ehd.admin.recharge.domain.Recharge;
import com.ehd.admin.recharge.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/recharge")
public class RechargeController {
    @Autowired
    private RechargeService rechargeService;
    @RequestMapping("/list")
    @ResponseBody
    public List<Recharge> getRechargeList(HttpServletRequest request){
        //AjaxJson
        return rechargeService.getRechargeByStatus(1);
    }
}
