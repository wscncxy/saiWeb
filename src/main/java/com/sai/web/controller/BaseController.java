package com.sai.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.sai.core.constants.StatusConstant;
import com.sai.core.dto.ResultCode;
import com.sai.web.dto.ResponseCode;

/**
 * Created by ZhouXiang sai on 2017/6/29.
 */

public class BaseController<T> {

    public String getSuccessResult() {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, null);
    }

    public String getSuccessResult(T data) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, data);
    }

    public String getSuccessResult(String msg) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, msg, null);
    }

    public String getSuccessResult(String msg, T data) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, msg, data);
    }

    public String getFailResult() {
        return getResult(StatusConstant.RESULT_FAIL_CODE, StatusConstant.RESULT_FAIL_MSG, null);
    }

    public String getFailResult(String msg) {
        return getResult(StatusConstant.RESULT_FAIL_CODE, msg, null);
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

}
