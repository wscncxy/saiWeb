package com.sai.web.pojo;

import com.sai.core.utils.JSONUtil;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by ZhouXiang on 2017/10/23 0023 18:35.
 */
public class StatelessToken implements AuthenticationToken {
    private String principal;
    private String token;
    private String requestUrl;
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public StatelessToken(String principal, String token) {
        this.principal = principal;
        this.token = token;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String toString() {
        return "StatelessToken:" + JSONUtil.toJSONString(this);
    }
}
