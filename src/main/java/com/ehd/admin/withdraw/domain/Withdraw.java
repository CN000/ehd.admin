package com.ehd.admin.withdraw.domain;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Withdraw {
    private int cashId;
    private String cashUser;
    private BigDecimal cashNum;
    private Timestamp cashTime;
    private int cashStatus;
    private BigDecimal cashRate;
    private BigDecimal cashCharge;
    private int cardType;
    private int cardDel;
    private String ethAddress;
    private String thHash;
    private String orderId;

    public int getCashId() {
        return cashId;
    }

    public void setCashId(int cashId) {
        this.cashId = cashId;
    }

    public String getCashUser() {
        return cashUser;
    }

    public void setCashUser(String cashUser) {
        this.cashUser = cashUser;
    }

    public BigDecimal getCashNum() {
        return cashNum;
    }

    public void setCashNum(BigDecimal cashNum) {
        this.cashNum = cashNum;
    }

    public Timestamp getCashTime() {
        return cashTime;
    }

    public void setCashTime(Timestamp cashTime) {
        this.cashTime = cashTime;
    }

    public int getCashStatus() {
        return cashStatus;
    }

    public void setCashStatus(int cashStatus) {
        this.cashStatus = cashStatus;
    }

    public BigDecimal getCashRate() {
        return cashRate;
    }

    public void setCashRate(BigDecimal cashRate) {
        this.cashRate = cashRate;
    }

    public BigDecimal getCashCharge() {
        return cashCharge;
    }

    public void setCashCharge(BigDecimal cashCharge) {
        this.cashCharge = cashCharge;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getCardDel() {
        return cardDel;
    }

    public void setCardDel(int cardDel) {
        this.cardDel = cardDel;
    }

    public String getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }

    public String getThHash() {
        return thHash;
    }

    public void setThHash(String thHash) {
        this.thHash = thHash;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
