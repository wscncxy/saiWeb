package com.sai.web.service;

import com.sai.core.dto.ResultCode;
import com.sai.web.dto.ReqBaseDTO;
import com.sai.web.dto.BaseDTO;

import java.util.List;

public interface PageService<REQ extends ReqBaseDTO, DTO extends BaseDTO> {
    List<DTO> query(REQ params);

    ResultCode add(REQ addInfo);

    ResultCode update(REQ updateInfo);

    ResultCode<DTO> get(Long id);
}
