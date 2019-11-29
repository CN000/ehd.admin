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

@Component
@JobHandler(value = "FreeRewardHandler")
@Transactional
public class FreeRewardHandler extends IJobHandler {
    @Autowired
    private FrontUserMapper frontUserMapper;
    @Autowired
    private RewardLogMapper rewardLogMapper;
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        System.out.println("福利释放开始："+sdf.format(startTime));
        freeReward(Contants.FREE_REWARD_PAGE);
        System.out.println("福利释放结束："+sdf.format(startTime));
        return SUCCESS;
    }
    private void freeReward(int freeRewardPage){
        List<FrontUser> list = frontUserMapper.searchUserForFreeReward(freeRewardPage*1000);
        doFreeReward(list);
        if (list.size() == 1000) {
            list.clear();
            freeReward(Contants.FREE_REWARD_PAGE);
        } else {
            Contants.FREE_REWARD_PAGE = 0;
        }
    }
    private void doFreeReward(List<FrontUser> list){
        List<Map> freeEhdList = new ArrayList<Map>();
        List<Map> freeLogList = new ArrayList<Map>();
        try {
            //激活了的用户，且佣金池有佣金的
            for(FrontUser user : list){
                Map<String,Object> freeMap = new HashMap<String,Object>();
                Map<String,Object> freeLogMap = new HashMap<String,Object>();
                if(user.getZhituiNum()!=null){
                    BigDecimal flowEhd;
                    BigDecimal lockEhd;
                    if(user.getZhituiNum()>0 && user.getZhituiNum()<=10) {
                        lockEhd = user.getLockEhd().multiply(new BigDecimal(0.1 * user.getZhituiNum()));
                        lockEhd = lockEhd.setScale(4, BigDecimal.ROUND_DOWN);
                        flowEhd = user.getStaticEhd().add(lockEhd);
                        freeMap.put("lockEhd",user.getLockEhd().subtract(lockEhd));
                    }else {
                        flowEhd = user.getLockEhd().add(user.getStaticEhd());
                        freeMap.put("lockEhd",0);
                    }
                        freeMap.put("staticEhd",user.getStaticEhd());
                        freeMap.put("userId",user.getUserId());
                        freeMap.put("flowEhd",flowEhd);

                        freeLogMap.put("liushuiNo",System.currentTimeMillis()+"");
                        freeLogMap.put("assetsBefrom",user.getFlowEhd());
                        freeLogMap.put("assetsAfter",user.getFlowEhd().add(flowEhd));
                        freeLogMap.put("assetsChange",flowEhd);
                        freeLogMap.put("type",4);
                        freeLogMap.put("userId",user.getUserId());

                        freeEhdList.add(freeMap);
                        freeLogList.add(freeLogMap);
                }

            }
            frontUserMapper.freeReward(freeEhdList);
            rewardLogMapper.insertLiushuiLog(freeLogList);
            System.out.println("第" + Contants.FREE_REWARD_PAGE + "批已结算完成");
            Contants.FREE_REWARD_PAGE = Contants.FREE_REWARD_PAGE + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
