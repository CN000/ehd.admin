package com.ehd.admin.recharge.service.impl;

import com.ehd.admin.recharge.domain.Recharge;
import com.ehd.admin.recharge.mapper.RechargeMapper;
import com.ehd.admin.recharge.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    private RechargeMapper rechargeMapper;

    @Override
    public List<Recharge> getRechargeByStatus(int status) {
        System.out.println("开始查询");
        List<Recharge> rechargeList = new ArrayList<Recharge>();
        try {
             rechargeList = rechargeMapper.getRechargeByStatus(status);
            System.out.println(rechargeList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rechargeList;
    }
}
