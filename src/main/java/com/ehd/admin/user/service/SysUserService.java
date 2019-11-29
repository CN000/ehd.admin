package com.ehd.admin.user.service;

import com.ehd.admin.user.domain.SysUser;
import org.springframework.stereotype.Service;

public interface SysUserService {
     SysUser findUserByUserName(String username);
}
