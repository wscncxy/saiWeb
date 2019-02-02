package com.sai.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.sai.core.constants.AuthContants;
import com.sai.core.constants.StatusConstant;
import com.sai.core.dto.ResultCode;
import com.sai.core.utils.UserAuthUtil;
import com.sai.web.pojo.StatelessToken;
import com.sai.web.utils.IgnoreUrlUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by ZhouXiang on 2017/10/23 0023 17:00.
 */
public class StatelessAuthFilter extends AccessControlFilter {
    private static final Logger log = LoggerFactory.getLogger(StatelessAuthFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        UserAuthUtil.remove();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        if (IgnoreUrlUtils.isUriIsIgnored(uri)){
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String token = httpRequest.getHeader(AuthContants.AUTH_TOKEN_KEY);
            Enumeration enumeration=httpRequest.getHeaderNames();
            StatelessToken statelessToken = new StatelessToken(token, token);
            ThreadContext.unbindSubject();
            Subject subject = getSubject(request, response);
            subject.login(statelessToken);
            ThreadContext.bind(subject);
        } catch (Exception e) {
            ResultCode resultCode = new ResultCode();
            resultCode.setCode(StatusConstant.RESULT_AUTH_FAIL_CODE);
            resultCode.setMsg("登录失效,请重新登陆");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(resultCode));
            return false;
        }

        return true;
    }

}
