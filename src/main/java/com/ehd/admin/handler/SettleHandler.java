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

@JobHandler(value = "SettleHandler")
@Component
public class SettleHandler extends IJobHandler {
    @Autowired
    private FrontUserMapper frontUserMapper;
    @Autowired
    private RewardLogMapper rewardLogMapper;
    private  MathContext m = new MathContext(4);

    @Transactional
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        System.out.println("动态結算開始:"+sdf.format(startTime));
        settle(Contants.SETTLE_PAGE);
        Date endTime = new Date();
        System.out.println("动态結算完成:"+sdf.format(endTime));
        return SUCCESS;
    }
    private void settle(int settlePage){
        List<FrontUser> list = frontUserMapper.searchUserForSettle(settlePage * 1000);
        doSettle(list);
        if (list.size() == 1000) {
            list.clear();
            settle(Contants.SETTLE_PAGE);
        } else {
            Contants.SETTLE_PAGE = 0;
        }
    }
    private void doSettle(List<FrontUser> list){
        try {
            List<Map> ehdList = new ArrayList<Map>();
            List<Map> logList = new ArrayList<Map>();
            for (FrontUser userInfo : list) {
                Map<String, Object> ehdMap = new HashMap<String, Object>();
                Map<String, Object> logMap = new HashMap<String, Object>();
                //推荐奖
                if (userInfo.getChildrenIds() != null && userInfo.getChildrenIds() != "") {
                    String[] chiIds;

                    if (userInfo.getUserLevel() > 0&&userInfo.getChildrenIdsAll()!=null&&userInfo.getChildrenIdsAll()!="") {
                        chiIds = userInfo.getChildrenIdsAll().split(",");
                    } else {
                        chiIds = userInfo.getChildrenIds().split(",");
                    }
                    //统计静态奖励
                    BigDecimal lockEhd = frontUserMapper.sumStaticByChilchenIds(chiIds);
                    lockEhd = lockEhd.multiply(new BigDecimal("0.1"), m);
                    ehdMap.put("lockEhd", lockEhd);
                    ehdMap.put("userId", userInfo.getUserId());

                    logMap.put("liushuiNo", System.currentTimeMillis() + "");
                    logMap.put("assetsBefrom", userInfo.getLockEhd());
                    logMap.put("assetsAfter", userInfo.getLockEhd().add(lockEhd));
                    logMap.put("assetsChange", lockEhd);
                    logMap.put("type", 1);
                    logMap.put("userId", userInfo.getUserId());
                    ehdList.add(ehdMap);
                    logList.add(logMap);
                }
            }
            frontUserMapper.updateLockehd(ehdList);
            rewardLogMapper.insertLiushuiLog(logList);
            System.out.println("第" + Contants.SETTLE_PAGE + "批已结算完成");
            Contants.SETTLE_PAGE = Contants.SETTLE_PAGE + 1;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
