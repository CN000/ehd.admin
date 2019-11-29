package com.ehd.admin.user.mapper;

import com.ehd.admin.user.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {
    SysUser findUserByUserName(@Param("username") String  username);
}