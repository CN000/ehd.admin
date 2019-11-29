package com.ehd.admin.user.service.impl;

import com.ehd.admin.user.domain.SysUser;
import com.ehd.admin.user.mapper.SysUserMapper;
import com.ehd.admin.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser findUserByUserName(String username) {
        return sysUserMapper.findUserByUserName(username);
    }
}
