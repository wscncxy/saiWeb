package com.sai.web.utils;

import com.sai.core.constants.NumberContants;
import com.sai.core.utils.UserAuthUtil;
import com.sai.web.pojo.StatelessToken;
import com.sai.web.service.DefaultAuthorizeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhouXiang on 2017/10/19 0019 17:20.
 */
public class SaiAuthorizeRealm extends AuthorizingRealm {


    public SaiAuthorizeRealm() {
        setCachingEnabled(false);
    }

    @Autowired
    private DefaultAuthorizeService authorizeService;

    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) authenticationToken;
        BigDecimal userId = authorizeService.checkUserAuthInfo(statelessToken.getToken());
        if (userId == null || userId.compareTo(NumberContants.BIG_ONE) == -1) {
            return null;
        }
        return new SimpleAuthenticationInfo(statelessToken.getPrincipal(), statelessToken.getCredentials(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        String principal = principals.getPrimaryPrincipal().toString();
        List<String> roleList = new ArrayList<>();
        roleList.addAll(UserAuthUtil.getRoles());
        authorizationInfo.addRoles(roleList);
        return authorizationInfo;
    }
}
