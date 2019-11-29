package com.ehd.admin.user.domain;

import java.math.BigDecimal;

public class FrontUser {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userPhone;
    private String mobile;
    private String mobileVerified;
    private String childrenIds;
    private String childrenIdsAll;

    public String getChildrenIdsAll() {
        return childrenIdsAll;
    }

    public void setChildrenIdsAll(String childrenIdsAll) {
        this.childrenIdsAll = childrenIdsAll;
    }

    public BigDecimal getStaticEhd() {
        return staticEhd;
    }

    public void setStaticEhd(BigDecimal staticEhd) {
        this.staticEhd = staticEhd;
    }

    private BigDecimal staticEhd;

    public String getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(String childrenIds) {
        this.childrenIds = childrenIds;
    }

    public String getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(String mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    private BigDecimal ehd;

    private BigDecimal lockEhd;

    private BigDecimal flowEhd;

    private BigDecimal hdb;

    private String ehdWallet;

    private String hdbWallet;

    private String userPass;

    private String userPayPass;

    private String headPoratil;

    private String userCode;

    private BigDecimal teamAchieve;

    private BigDecimal teamNum;

    private Integer zhituiNum;

    private Integer userPid;

    private String pidList;

    private Integer daishuNum;

    private Integer userLevel;

    private BigDecimal totalProfit;

    private String activation;

    private BigDecimal dayNum;

    private String regTime;

    private String userState;

    private String orderNo;

    private Integer type;

    private BigDecimal todayPriot;

    private Integer days;

    private String ishdb;

    private int isBd;

    private String privateKey;

    private String publicKey;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public BigDecimal getEhd() {
        return ehd;
    }

    public void setEhd(BigDecimal ehd) {
        this.ehd = ehd;
    }

    public BigDecimal getLockEhd() {
        return lockEhd;
    }

    public void setLockEhd(BigDecimal lockEhd) {
        this.lockEhd = lockEhd;
    }

    public BigDecimal getFlowEhd() {
        return flowEhd;
    }

    public void setFlowEhd(BigDecimal flowEhd) {
        this.flowEhd = flowEhd;
    }

    public BigDecimal getHdb() {
        return hdb;
    }

    public void setHdb(BigDecimal hdb) {
        this.hdb = hdb;
    }

    public String getEhdWallet() {
        return ehdWallet;
    }

    public void setEhdWallet(String ehdWallet) {
        this.ehdWallet = ehdWallet;
    }

    public String getHdbWallet() {
        return hdbWallet;
    }

    public void setHdbWallet(String hdbWallet) {
        this.hdbWallet = hdbWallet;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserPayPass() {
        return userPayPass;
    }

    public void setUserPayPass(String userPayPass) {
        this.userPayPass = userPayPass;
    }

    public String getHeadPoratil() {
        return headPoratil;
    }

    public void setHeadPoratil(String headPoratil) {
        this.headPoratil = headPoratil;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public BigDecimal getTeamAchieve() {
        return teamAchieve;
    }

    public void setTeamAchieve(BigDecimal teamAchieve) {
        this.teamAchieve = teamAchieve;
    }

    public BigDecimal getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(BigDecimal teamNum) {
        this.teamNum = teamNum;
    }

    public Integer getZhituiNum() {
        return zhituiNum;
    }

    public void setZhituiNum(Integer zhituiNum) {
        this.zhituiNum = zhituiNum;
    }

    public Integer getUserPid() {
        return userPid;
    }

    public void setUserPid(Integer userPid) {
        this.userPid = userPid;
    }

    public String getPidList() {
        return pidList;
    }

    public void setPidList(String pidList) {
        this.pidList = pidList;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public BigDecimal getDayNum() {
        return dayNum;
    }

    public void setDayNum(BigDecimal dayNum) {
        this.dayNum = dayNum;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDaishuNum() {
        return daishuNum;
    }

    public void setDaishuNum(Integer daishuNum) {
        this.daishuNum = daishuNum;
    }

    public BigDecimal getTodayPriot() {
        return todayPriot;
    }

    public void setTodayPriot(BigDecimal todayPriot) {
        this.todayPriot = todayPriot;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getIshdb() {
        return ishdb;
    }

    public void setIshdb(String ishdb) {
        this.ishdb = ishdb;
    }

    public int getIsBd() {
        return isBd;
    }

    public void setIsBd(int isBd) {
        this.isBd = isBd;
    }
}
