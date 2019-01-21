package com.sai.web.service;

import com.alibaba.fastjson.JSONObject;
import com.sai.core.dto.ResultCode;

import java.math.BigDecimal;
import java.util.List;

public interface PageService<T> {
    List list(JSONObject jsonObject);

    ResultCode add(T addInfo);

    ResultCode update(T updateInfo);

    ResultCode get(BigDecimal id);
}
