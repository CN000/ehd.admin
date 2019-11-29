package com.ehd.admin.user.mapper;

import com.ehd.admin.user.domain.FrontUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface FrontUserMapper {
    void backEHD(@Param("cashNum") BigDecimal cashNum , @Param("cashUser") String cashUser);

    FrontUser getFrontUserByUserId(@Param("userId") int userId);

    List<FrontUser> searchUserForSettle(@Param("settlePage") int settlePage);

    List<FrontUser> searchUserForStaticSettle(@Param("settlePage") int settlePage);

    List<FrontUser> getUserByPid(@Param("pid") Integer pid);

    List<FrontUser> getFrontUserList(@Param("rowBounds")int rowBounds,@Param("pageSize")int pageSize,@Param("param") Map<String,String> param);

    void updateStaticEhd(@Param("ehdList") List<Map> ehdList);

    BigDecimal sumStaticByChilchenIds(@Param("chiIds")String[] chiIds);

    void updateLockehd(@Param("ehdList") List<Map> ehdList);

    FrontUser getFrontUserByUserIdForReward(@Param("userId") Integer userId);

    List<FrontUser> searchUserForReward(@Param("rewardPage") int rewardPage);

    List<FrontUser> getChiIds();

    void createChiIds(@Param("ChiIds")String childrenIdsAll,@Param("userId") Integer userId);

    List<FrontUser> searchUserForFreeReward();

    void freeReward(@Param("freeEhdList")List<Map> freeEhdList);

    List<FrontUser> searchUserForFreeReward(@Param("freeRewardPage") int page);
}
