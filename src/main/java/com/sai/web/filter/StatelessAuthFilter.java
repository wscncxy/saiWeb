package com.sai.web.filter;

import com.sai.core.constants.AuthContants;
import com.sai.core.constants.StatusConstant;
import com.sai.core.dto.ResultData;
import com.sai.core.utils.JSONUtil;
import com.sai.core.utils.UserAuthUtil;
import com.sai.web.pojo.StatelessToken;
import com.sai.web.utils.IgnoreUrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by ZhouXiang on 2017/10/23 0023 17:00.
 */
public class StatelessAuthFilter {
//    private static final Logger log = LoggerFactory.getLogger(StatelessAuthFilter.class);
//
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        UserAuthUtil.remove();
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String uri = httpRequest.getRequestURI();
//        if (IgnoreUrlUtils.isUriIsIgnored(uri)){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        try {
//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            String token = httpRequest.getHeader(AuthContants.AUTH_TOKEN_KEY);
//            Enumeration enumeration=httpRequest.getHeaderNames();
//            StatelessToken statelessToken = new StatelessToken(token, token);
//            ThreadContext.unbindSubject();
//            Subject subject = getSubject(request, response);
//            subject.login(statelessToken);
//            ThreadContext.bind(subject);
//        } catch (Exception e) {
//            ResultData resultData = new ResultData();
//            resultData.setCode(StatusConstant.RESULT_AUTH_FAIL_CODE);
//            resultData.setMsg("登录失效,请重新登陆");
//            response.setContentType("application/json; charset=utf-8");
//            response.getWriter().write(JSONUtil.toJSONString(ResultData));
//            return false;
//        }
//
//        return true;
//    }

}
