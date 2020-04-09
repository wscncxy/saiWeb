package com.sai.web.service;

import com.sai.web.dto.BaseDTO;
import com.sai.web.dto.ReqBaseDTO;

import java.util.List;

public interface FrontPageService<REQ extends ReqBaseDTO, DTO extends BaseDTO> {
    List<DTO> query(REQ params);

    DTO get(Long id);
}
