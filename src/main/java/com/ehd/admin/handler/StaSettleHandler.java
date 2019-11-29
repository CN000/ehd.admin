package com.ehd.admin.handler;

import com.ehd.admin.contant.Contants;
import com.ehd.admin.log.mapper.RewardLogMapper;
import com.ehd.admin.user.domain.FrontUser;
import com.ehd.admin.user.mapper.FrontUserMapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.*;

@JobHandler(value = "StaSettleHandler")
@Component
public class StaSettleHandler extends IJobHandler {
    @Autowired
    private FrontUserMapper frontUserMapper;
    @Autowired
    private RewardLogMapper rewardLogMapper;
    private MathContext m = new MathContext(4);

    @Override
    @Transactional
    public ReturnT<String> execute(String s) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        System.out.println("静态结算开始:"+sdf.format(startTime));
        staSettle(Contants.SETTLE_STATIC_PAGE);
        Date endTime = new Date();
        System.out.println("静态結算完成:"+sdf.format(endTime));
        return SUCCESS;
    }
    private void staSettle(int settlePage){
        List<FrontUser> list = frontUserMapper.searchUserForStaticSettle(settlePage * 1000);
        System.out.println("list size is "+list.size());
        doStaSettle(list);
        if(list.size()==1000){
            list.clear();
            staSettle(Contants.SETTLE_STATIC_PAGE);
        }else{
            Contants.SETTLE_STATIC_PAGE=0;
        }
    }

    private void doStaSettle(List<FrontUser> list){
        List<Map> staEhdList = new ArrayList<Map>();
        List<Map> staLogList = new ArrayList<Map>();
        for (FrontUser userInfo : list) {
            Map<String,Object> staEhdMap = new HashMap<String, Object>();
            Map<String,Object> staLogMap = new HashMap<String, Object>();
            BigDecimal staEhd = new BigDecimal(0.01);
            if(userInfo.getEhd().compareTo(new BigDecimal(1000))>0){
                staEhd = new BigDecimal(10);
            }else{
                staEhd = userInfo.getEhd().multiply(staEhd,m);
            }
            staEhdMap.put("staEhd",staEhd);
            staEhdMap.put("todayPriot",staEhd);
            staEhdMap.put("userId",userInfo.getUserId());
            staLogMap.put("liushuiNo",System.currentTimeMillis()+"");
            staLogMap.put("assetsBefrom",userInfo.getLockEhd());
            staLogMap.put("assetsAfter",userInfo.getLockEhd().add(staEhd));
            staLogMap.put("assetsChange",staEhd);
            staLogMap.put("type",6);
            staLogMap.put("userId",userInfo.getUserId());

            staEhdList.add(staEhdMap);
            staLogList.add(staLogMap);
        }
        frontUserMapper.updateStaticEhd(staEhdList);
        rewardLogMapper.insertLiushuiLog(staLogList);
        System.out.println("第" + Contants.SETTLE_STATIC_PAGE + "批已结算完成");
        Contants.SETTLE_STATIC_PAGE = Contants.SETTLE_STATIC_PAGE + 1;
    }
}
