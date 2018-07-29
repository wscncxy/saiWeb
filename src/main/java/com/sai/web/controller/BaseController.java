package com.sai.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.sai.core.constants.StatusConstant;
import com.sai.core.dto.ResultCode;
import com.sai.web.dto.ResponseCode;

/**
 * Created by ZhouXiang sai on 2017/6/29.
 */

public class BaseController<T> {

    public ResponseCode getSuccessResult() {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, null);
    }

    public ResponseCode getSuccessResult(T data) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, data);
    }

    public ResponseCode getSuccessResult(String msg) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, msg, null);
    }

    public ResponseCode getSuccessResult(String msg, T data) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, msg, data);
    }

    public ResponseCode getFailResult() {
        return getResult(StatusConstant.RESULT_FAIL_CODE, StatusConstant.RESULT_FAIL_MSG, null);
    }

    public ResponseCode getFailResult(String msg) {
        return getResult(StatusConstant.RESULT_FAIL_CODE, msg, null);
    }

    public ResponseCode getFailResult(String code, String msg) {
        return getResult(code, msg, null);
    }

    public ResponseCode getResult(String code, String msg, T data) {
        ResponseCode responseCode = new ResponseCode();
        responseCode.setCode(code);
        responseCode.setMsg(msg);
        responseCode.setData(data);
        return responseCode;
    }

    public ResponseCode getResult(ResultCode<T> resultCode) {
        return getResult(resultCode.getCode(), resultCode.getMsg(), resultCode.getData());
    }

}
