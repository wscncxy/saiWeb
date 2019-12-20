package com.sai.web.service;

import com.sai.core.dto.ResultCode;
import com.sai.web.dto.EditBaseDTO;
import com.sai.web.dto.ReqBaseDTO;
import com.sai.web.dto.RespBaseDTO;

import java.util.List;

public interface PageService<REQ extends ReqBaseDTO, RESP extends RespBaseDTO, EDIT extends EditBaseDTO> {
    List<RESP> query(REQ params);

    ResultCode add(EDIT addInfo);

    ResultCode update(EDIT updateInfo);

    ResultCode<RESP> get(Long id);
}
