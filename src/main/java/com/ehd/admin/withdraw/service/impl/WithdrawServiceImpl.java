package com.ehd.admin.withdraw.service.impl;

import com.ehd.admin.recharge.domain.Recharge;
import com.ehd.admin.user.mapper.FrontUserMapper;
import com.ehd.admin.withdraw.domain.Withdraw;
import com.ehd.admin.withdraw.mapper.WithdrawMapper;
import com.ehd.admin.withdraw.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawServiceImpl implements WithdrawService {
    @Autowired
    private WithdrawMapper withdrawMapper;
    @Autowired
    private FrontUserMapper frontUserMapper;
    @Override
    public List<Withdraw> getWithdrawList(int rowBounds, int bounds) {
        List<Withdraw> withdrawList = new ArrayList<Withdraw>();
        try {
            withdrawList = withdrawMapper.getWithdrawList(rowBounds,bounds);
        }catch (Exception e){
            e.printStackTrace();
        }
        return withdrawList;
    }

    @Override
    public void notifyStatus(int cashId, int status) {
        Withdraw withdraw = withdrawMapper.getWithdrawById(cashId);
        System.out.println("提现金额为:"+withdraw.getCashNum());
        if(withdraw!=null&&withdraw.getCashStatus()==0){
            if (status==-1){
                frontUserMapper.backEHD(withdraw.getCashNum(),withdraw.getCashUser());
                System.out.println("返还结束");
            }
            withdrawMapper.notifyStatus(cashId,status);
        }
        System.out.println("更改状态结束");
    }
}
