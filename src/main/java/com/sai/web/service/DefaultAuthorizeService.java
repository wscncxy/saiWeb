package com.sai.web.service;


import com.sai.core.pojo.UserAuthInfo;
import com.sai.core.utils.UserAuthUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhouXiang on 2017/10/19 0019 10:49.
 */
public interface DefaultAuthorizeService {
    default Long checkUserAuthInfo(String token){
        UserAuthInfo userAuthInfo = getUserAuthInfo(token);
        if(userAuthInfo!=null){
            UserAuthUtil.set(userAuthInfo);
            return userAuthInfo.getUserId();
        }
        return -1L;
    }

    UserAuthInfo getUserAuthInfo(String token);
}
