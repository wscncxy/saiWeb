package com.sai.web.mapper;

import com.sai.web.diamond.BaseDO;
import com.sai.web.dto.ReqBaseDTO;

import java.util.List;

public interface FrontBaseMapper<DO extends BaseDO, REQ extends ReqBaseDTO> {
    DO selectByPrimaryKey(Long id);

    List<DO> selectList(REQ condition);
}
