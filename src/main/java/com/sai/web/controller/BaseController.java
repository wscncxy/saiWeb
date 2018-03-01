package com.sai.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.sai.core.constents.StatusContent;
import com.sai.web.dto.ResponseCode;
import com.sai.core.dto.ResultCode;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ZhouXiang sai on 2017/6/29.
 */

public class BaseController<T> {

    public String getSuccessResult() {
        return getResult(StatusContent.RESULT_SUCCESS_CODE, StatusContent.RESULT_SUCCESS_MSG, null);
    }

    public String getSuccessResult(T data) {
        return getResult(StatusContent.RESULT_SUCCESS_CODE, StatusContent.RESULT_SUCCESS_MSG, data);
    }

    public String getSuccessResult(String msg) {
        return getResult(StatusContent.RESULT_SUCCESS_CODE, msg, null);
    }

    public String getSuccessResult(String msg, T data) {
        return getResult(StatusContent.RESULT_SUCCESS_CODE, msg, data);
    }

    public String getFailResult() {
        return getResult(StatusContent.RESULT_FAIL_CODE, StatusContent.RESULT_FAIL_MSG, null);
    }

    public String getFailResult(String msg) {
        return getResult(StatusContent.RESULT_FAIL_CODE, msg, null);
    }

    public String getFailResult(String code, String msg) {
        return getResult(code, msg, null);
    }

    public String getResult(String code, String msg, T data) {
        ResponseCode responseCode = new ResponseCode();
        responseCode.setCode(code);
        responseCode.setMsg(msg);
        responseCode.setData(data);
        return JSONObject.toJSONString(responseCode);
    }

    public String getResult(ResultCode<T> resultCode) {
        return getResult(resultCode.getCode(), resultCode.getMsg(), resultCode.getData());
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public String unauthorizedExceptionHandler(RuntimeException runtimeException) {
        runtimeException.printStackTrace();
        return getFailResult("权限不足ExceptionHandler");
    }

    @ExceptionHandler({UnknownAccountException.class})
    @ResponseBody
    public String unknownAccountExceptionHandler() {
        return getFailResult("用户不存在");
    }

}
