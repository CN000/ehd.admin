package com.ehd.admin.log.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RewardLog {
    private int liuShuiId;
    private String liuShuiNo;
    private BigDecimal assetsBefrom;
    private BigDecimal assetsAfter;
    private BigDecimal assetsChange;
    private String orderNo;
    private int type;
    private Timestamp cteateTime;
    private int userId;

    public int getLiuShuiId() {
        return liuShuiId;
    }

    public void setLiuShuiId(int liuShuiId) {
        this.liuShuiId = liuShuiId;
    }

    public String getLiuShuiNo() {
        return liuShuiNo;
    }

    public void setLiuShuiNo(String liuShuiNo) {
        this.liuShuiNo = liuShuiNo;
    }

    public BigDecimal getAssetsBefrom() {
        return assetsBefrom;
    }

    public void setAssetsBefrom(BigDecimal assetsBefrom) {
        this.assetsBefrom = assetsBefrom;
    }

    public BigDecimal getAssetsAfter() {
        return assetsAfter;
    }

    public void setAssetsAfter(BigDecimal assetsAfter) {
        this.assetsAfter = assetsAfter;
    }

    public BigDecimal getAssetsChange() {
        return assetsChange;
    }

    public void setAssetsChange(BigDecimal assetsChange) {
        this.assetsChange = assetsChange;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getCteateTime() {
        return cteateTime;
    }

    public void setCteateTime(Timestamp cteateTime) {
        this.cteateTime = cteateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



}
