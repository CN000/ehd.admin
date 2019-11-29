package com.ehd.admin.log.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface RewardLogMapper {
    void addLockEHDLiuShuiLog(@Param("liushuiNo") String liushuiNo, @Param("assetsBefrom") BigDecimal lockEhd, @Param("assetsAfter") BigDecimal add,
                              @Param("assetsChange") BigDecimal flow, @Param("type") int type, @Param("userId") Integer userId);
    void insertLiushuiLog(@Param("logList") List<Map> logList);

}
