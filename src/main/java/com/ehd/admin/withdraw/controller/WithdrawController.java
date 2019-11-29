package com.ehd.admin.withdraw.controller;

import com.ehd.admin.common.AjaxJson;
import com.ehd.admin.withdraw.domain.Withdraw;
import com.ehd.admin.withdraw.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/withdraw")
@Controller
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model){
        int RowBounds=0;
        int rowBounds=100;
        List<Withdraw> withdrawList = withdrawService.getWithdrawList(RowBounds,rowBounds);
        model.addAttribute("withdrawList",withdrawList);
        return "withdraw/list";
    }
    @RequestMapping("/notifyStatus")
    @ResponseBody
    public String notifyStatus(HttpServletRequest request,int cashId,int status){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        System.out.println("单号是："+cashId+"status:"+status);
        withdrawService.notifyStatus(cashId,status);
        return "success";
    }
}
