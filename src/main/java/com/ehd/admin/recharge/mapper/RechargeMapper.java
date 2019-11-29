package com.ehd.admin.recharge.mapper;

import com.ehd.admin.recharge.domain.Recharge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RechargeMapper {
     List<Recharge> getRechargeByStatus(int status) throws Exception;

    void updateStatus(@Param("id") int rechargeId);
}
