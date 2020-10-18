package com.redbyte.platform.lottery.schedule;

import com.alibaba.fastjson.JSON;
import com.redbyte.platform.lottery.core.dao.DaLeTouMapper;
import com.redbyte.platform.lottery.core.domain.dto.DaleTouDTO;
import com.redbyte.platform.lottery.core.domain.entity.DaLeTou;
import com.redbyte.platform.lottery.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangwq
 */
@Component
public class DaletouJob {

    private static final String url = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=1&isVerify=1&pageNo=1";

    private static final String init_url_str = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&isVerify=1&pageNo={0}&pageSize={1}";

    @Autowired
    private DaLeTouMapper daLeTouMapper;

    /**
     * 历史数据init
     */
    public void historyInit(int pageNo, int pageSize) throws Exception {

        String json = HttpClient.get(String.format(init_url_str, pageNo, pageSize));
        Map map = (Map) JSON.parse(json);
        Map valMap = (Map) JSON.parse(map.get("value").toString());
        int pages = (Integer) valMap.get("pages");

        for (int i = pageNo; i < pages; i++) {
            String result = HttpClient.get(String.format(init_url_str, i, pageSize));
            analyze(result);
        }
    }

    /**
     * 大乐透每周一、周三、周六开奖
     * 每周二、周四、周日更新开奖结果
     */
    @Schedules(value = {@Scheduled(cron = "0 0 1 ? * 1"),
            @Scheduled(cron = "0 51 1 ? * 3"),
            @Scheduled(cron = "0 51 21 ? * 6")})
    public void updateCurDaletou() throws Exception {
        String json = HttpClient.get(url);
        this.analyze(json);
    }


    public static void main(String[] args) throws Exception {
        System.out.println(HttpClient.get(url));
    }

    public void analyze(String json) {
        // 持久化数据
        Map map = (Map) JSON.parse(json);
        Map valMap = (Map) JSON.parse(map.get("value").toString());
        String list = valMap.get("list").toString();
        List<DaleTouDTO> daleTouDTOS = JSON.parseArray(list, DaleTouDTO.class);

        System.out.println(daleTouDTOS);

        daleTouDTOS.stream().forEach(daleTouDTO -> {
            DaLeTou record = new DaLeTou();
            try {
                record.setNum(daleTouDTO.getLotteryDrawNum());
                record.setResult(daleTouDTO.getLotteryDrawResult());
                record.setUnsortResult(daleTouDTO.getLotteryUnsortDrawresult());
                record.setLotteryDate(new SimpleDateFormat("yyyy-MM-dd").parse(daleTouDTO.getLotteryDrawTime()));
                record.setSaleBeginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(daleTouDTO.getLotterySaleBeginTime()));
                record.setSaleEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(daleTouDTO.getLotterySaleEndtime()));
                record.setPoolBalance(daleTouDTO.getPoolBalanceAfterdraw());
                record.setTotalSaleAmount(daleTouDTO.getTotalSaleAmount());
                record.setCreateTime(new Date());
                record.setUpdateTime(new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
            daLeTouMapper.insert(record);
        });
    }
}
