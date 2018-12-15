package com.sai.web.service;

import com.sai.core.dto.ResultCode;

import java.util.List;

/**
 * Created by ZhouXiang on 2017/10/19 0019 10:49.
 */
public interface AuthorizeService {

    Long checkUserUrlAuth(String token, String reqUrl);

}
