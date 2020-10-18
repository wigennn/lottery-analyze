package com.redbyte.platform.lottery.core.dao;

import com.redbyte.platform.lottery.core.domain.entity.DaLeTou;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaLeTouMapper {
    int insert(DaLeTou record);

    int insertSelective(DaLeTou record);

    @Select("select * from daletou where result like #{result}")
    List<DaLeTou> select(@Param("result") String result);
}