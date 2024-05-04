package com.sai.web.service;

import com.sai.core.dto.ResultData;
import com.sai.web.pojo.LoginToken;
import com.sai.web.pojo.PageInfo;

import java.math.BigDecimal;
import java.util.Map;

public interface PageService<T> {

    ResultData<T> add(Map<String, Object> params, LoginToken loginToken);

    ResultData<T> update(Map<String, Object> params, LoginToken loginToken);

    ResultData<T> get(BigDecimal id);

    PageInfo<T> page(Map<String, Object> params, LoginToken loginToken);

    ResultData<T> delete(Map<String, Object> params, LoginToken loginToken);
}
