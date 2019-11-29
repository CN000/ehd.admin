package com.ehd.admin.withdraw.service;

import com.ehd.admin.withdraw.domain.Withdraw;

import java.util.List;

public interface WithdrawService {
    List<Withdraw> getWithdrawList(int rowBounds, int bounds);

    void notifyStatus(int cashId, int status);
}
