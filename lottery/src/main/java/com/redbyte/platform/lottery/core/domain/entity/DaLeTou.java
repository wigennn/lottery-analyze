package com.redbyte.platform.lottery.core.domain.entity;

import java.util.Date;

public class DaLeTou {
    private Long id;

    private String num;

    private String result;

    private String unsortResult;

    private Date lotteryDate;

    private Date saleBeginTime;

    private Date saleEndTime;

    private String poolBalance;

    private String totalSaleAmount;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUnsortResult() {
        return unsortResult;
    }

    public void setUnsortResult(String unsortResult) {
        this.unsortResult = unsortResult;
    }

    public Date getLotteryDate() {
        return lotteryDate;
    }

    public void setLotteryDate(Date lotteryDate) {
        this.lotteryDate = lotteryDate;
    }

    public Date getSaleBeginTime() {
        return saleBeginTime;
    }

    public void setSaleBeginTime(Date saleBeginTime) {
        this.saleBeginTime = saleBeginTime;
    }

    public Date getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(Date saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public String getPoolBalance() {
        return poolBalance;
    }

    public void setPoolBalance(String poolBalance) {
        this.poolBalance = poolBalance;
    }

    public String getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(String totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}