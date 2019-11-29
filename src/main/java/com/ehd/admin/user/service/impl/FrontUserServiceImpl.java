package com.ehd.admin.user.service.impl;

import com.ehd.admin.user.domain.FrontUser;
import com.ehd.admin.user.mapper.FrontUserMapper;
import com.ehd.admin.user.service.FrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FrontUserServiceImpl implements FrontUserService {
    @Autowired
    private FrontUserMapper frontUserMapper;
    @Override
    public List<FrontUser> getFrontUserList(int rowBounds, int rowBounds1, Map<String, String> map) {
        return frontUserMapper.getFrontUserList(rowBounds,rowBounds1,map);
    }

    @Override
    public void createChiIds() {
        List<FrontUser> frontUserList = frontUserMapper.getChiIds();
        for(FrontUser frontUser:frontUserList){
            frontUserMapper.createChiIds(frontUser.getChildrenIdsAll(),frontUser.getUserId());
        }
    }
}
