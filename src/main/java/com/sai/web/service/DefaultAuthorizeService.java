package com.sai.web.service;


import com.sai.core.pojo.UserAuthInfo;
import com.sai.core.utils.UserAuthUtil;

import java.math.BigDecimal;

/**
 * Created by ZhouXiang on 2017/10/19 0019 10:49.
 */
public interface DefaultAuthorizeService {
    default BigDecimal checkUserAuthInfo(String token){
        UserAuthInfo userAuthInfo = getUserAuthInfo(token);
        if(userAuthInfo!=null){
            UserAuthUtil.set(userAuthInfo);
            return userAuthInfo.getUserId();
        }
        return new BigDecimal(-1);
    }

    UserAuthInfo getUserAuthInfo(String token);
}
