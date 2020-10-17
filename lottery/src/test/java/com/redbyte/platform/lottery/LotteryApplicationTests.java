package com.redbyte.platform.lottery;

import com.alibaba.fastjson.JSON;
import com.redbyte.platform.lottery.core.dao.DaLeTouMapper;
import com.redbyte.platform.lottery.core.domain.dto.DaleTouDTO;
import com.redbyte.platform.lottery.core.domain.entity.DaLeTou;
import com.redbyte.platform.lottery.schedule.DaletouJob;
import com.redbyte.platform.lottery.utils.HttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class LotteryApplicationTests {

    @Autowired
    private DaLeTouMapper daLeTouMapper;

    @Autowired
    private DaletouJob daletouJob;

    @Test
    void initHistory() throws Exception {
        historyInit(1, 100);
    }

    private static final String init_url_str = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&isVerify=1&pageNo=%d&pageSize=%d";

    /**
     * 历史数据init
     */
    private void historyInit(int pageNo, int pageSize) throws Exception {
        String reqUrl = String.format(init_url_str, pageNo, pageSize);
        String json = HttpClient.get(reqUrl);
        System.out.println(json);
        Map map = (Map) JSON.parse(json);
        Map valMap = (Map) JSON.parse(map.get("value").toString());
        int pages = (Integer) valMap.get("pages");

        for (int i = pageNo; i < pages; i++) {
            String result = HttpClient.get(String.format(init_url_str, i, pageSize));
            daletouJob.analyze(result);
        }
    }
}
