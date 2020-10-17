package com.redbyte.platform.lottery.core.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangwq
 */
@Data
public class DaleTouDTO implements Serializable {
    private static final long serialVersionUID = 802420220081939336L;

    private String lotteryDrawNum;
    private String lotteryDrawResult;
    private String lotteryDrawTime;
    private String lotterySaleBeginTime;
    private String lotterySaleEndtime;
    private String lotteryUnsortDrawresult;
    private String poolBalanceAfterdraw;
    private String totalSaleAmount;
}
