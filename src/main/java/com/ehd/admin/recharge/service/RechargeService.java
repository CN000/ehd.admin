package com.ehd.admin.recharge.service;

import java.util.List;
import com.ehd.admin.recharge.domain.Recharge;

public interface RechargeService {

    List<Recharge> getRechargeByStatus(int i);
}
