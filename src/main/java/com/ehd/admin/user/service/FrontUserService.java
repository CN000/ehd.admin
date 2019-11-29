package com.ehd.admin.user.service;

import com.ehd.admin.user.domain.FrontUser;

import java.util.List;
import java.util.Map;

public interface FrontUserService {
    List<FrontUser> getFrontUserList(int rowBounds, int rowBounds1, Map<String, String> map);

    void createChiIds();
}
