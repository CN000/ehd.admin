package com.ehd.admin.withdraw.mapper;

import com.ehd.admin.withdraw.domain.Withdraw;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WithdrawMapper {
    public List<Withdraw> getWithdrawList(@Param("rowBounds") int rowBounds,@Param("bounds") int bounds);

    Withdraw getWithdrawById(@Param("cashId") int cashId);

    void update(Withdraw withdraw);

    void notifyStatus(@Param("cashId") int cashId,@Param("status") int status);
}
