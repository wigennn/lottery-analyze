package com.redbyte.platform.lottery.core.dao;

import com.redbyte.platform.lottery.core.domain.entity.DaLeTou;
import org.springframework.stereotype.Repository;

@Repository
public interface DaLeTouMapper {
    int insert(DaLeTou record);

    int insertSelective(DaLeTou record);
}