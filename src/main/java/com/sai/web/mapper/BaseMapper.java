package com.sai.web.mapper;

import com.sai.web.diamond.BaseDO;
import com.sai.web.dto.ReqBaseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface BaseMapper<DO extends BaseDO, REQ extends ReqBaseDTO> {
    int deleteById(Long id);

    int insert(DO record);

    DO selectById(Long id);

    int updateById(DO record);

    List<DO> select(REQ condition);
}
