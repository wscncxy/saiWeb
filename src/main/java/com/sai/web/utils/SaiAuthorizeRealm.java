package com.sai.web.utils;

import com.sai.web.pojo.StatelessToken;
import com.sai.web.service.AuthorizeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ZhouXiang on 2017/10/19 0019 17:20.
 */
public class SaiAuthorizeRealm extends AuthorizingRealm {



    public SaiAuthorizeRealm() {
        setCachingEnabled(false);
    }

    @Autowired
    private AuthorizeService authorizeService;

    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) authenticationToken;
        Long userId = authorizeService.checkUserUrlAuth(statelessToken.getToken(),statelessToken.getRequestUrl());
        if (userId == null || userId < 1) {
            return null;
        }
        return new SimpleAuthenticationInfo(statelessToken.getPrincipal(), statelessToken.getCredentials(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String principal = principals.getPrimaryPrincipal().toString();
//        TokenVo loginToken = TokenUtils.decryptToken(principal);
//        String rolesStr = loginToken.getRoles();
//        if (StringUtils.isNotBlank(rolesStr)) {
//            String[] rolesArray = rolesStr.split(",");
//            for (String role : rolesArray) {
//                authorizationInfo.addRole(role);
//            }
//        }
        return authorizationInfo;
    }
}
