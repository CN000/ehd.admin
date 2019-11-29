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
@JobHandler(value = "RewardHandler")
public class RewardHandler extends IJobHandler {
    @Autowired
    private FrontUserMapper frontUserMapper;
    @Autowired
    private RewardLogMapper rewardLogMapper;
    private MathContext m = new MathContext(4);
    private ArrayList<Integer> idList = new ArrayList<Integer>();
    @Override
    @Transactional
    public ReturnT<String> execute(String s) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        System.out.println("福利奖结算开始："+sdf.format(startTime));
        reward(Contants.REWARD_PAGE);
        System.out.println("福利奖结算结束："+sdf.format(startTime));
        idList.clear();
        return SUCCESS;
    }
    private void reward(int rewadPage){
        List<FrontUser> list = frontUserMapper.searchUserForReward(rewadPage * 1000);
        doReward(list);
        if (list.size() == 1000) {
            list.clear();
            reward(Contants.REWARD_PAGE);
        } else {
            Contants.REWARD_PAGE = 0;
        }

    }
    private void doReward(List<FrontUser> list){
        List<Map> rewardEhdList = new ArrayList<Map>();
        List<Map> rewardLogList = new ArrayList<Map>();
        BigDecimal reward = new BigDecimal(0);

        for (FrontUser userInfo : list) {
            //同级不必再算
            try {
                if (!idList.contains(userInfo.getUserId())) {

                    FrontUser parent = frontUserMapper.getFrontUserByUserIdForReward(userInfo.getUserPid());
                    if (parent != null) {
                        reward = reward.add(parent.getLockEhd().add(parent.getStaticEhd()));
                        FrontUser graParent = frontUserMapper.getFrontUserByUserIdForReward(parent.getUserPid());
                        if (parent != null && parent.getUserPid() != 0) {
                            reward = reward.add(graParent.getLockEhd().add(graParent.getStaticEhd()));
                        }
                        //上两代收益的10%
                        reward = reward.multiply(new BigDecimal("0.1"), m);
                        //查出同级
                        List<FrontUser> chilList = frontUserMapper.getUserByPid(parent.getUserId());
                        if (chilList.size() != 0) {
                            reward = reward.divide(new BigDecimal(chilList.size()), m);
                            for (FrontUser chil : chilList) {
                                //装入idMap再遍历到不必再算
                                idList.add(chil.getUserId());
                                Map<String, Object> rewardEhdMap = new HashMap<String, Object>();
                                Map<String, Object> rewardLogMap = new HashMap<String, Object>();
                                rewardEhdMap.put("lockEhd", reward);
                                rewardEhdMap.put("userId", chil.getUserId());

                                rewardLogMap.put("liushuiNo", System.currentTimeMillis() + "");
                                rewardLogMap.put("assetsBefrom", chil.getLockEhd());
                                rewardLogMap.put("assetsAfter", chil.getLockEhd().add(reward));
                                rewardLogMap.put("assetsChange", reward);
                                rewardLogMap.put("type", 2);
                                rewardLogMap.put("userId", chil.getUserId());

                                rewardEhdList.add(rewardEhdMap);
                                rewardLogList.add(rewardLogMap);
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        frontUserMapper.updateLockehd(rewardEhdList);
        rewardLogMapper.insertLiushuiLog(rewardLogList);
        System.out.println("第" + Contants.REWARD_PAGE + "批已结算完成");
        Contants.REWARD_PAGE = Contants.REWARD_PAGE + 1;

    }
}
