package com.sai.web.service;

import com.sai.core.dto.ResultCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PageService<T> {
    List list(Map params);

    ResultCode add(Map addInfo);

    ResultCode update(Map updateInfo);

    ResultCode get(BigDecimal id);
}
