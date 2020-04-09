package com.sai.web.controller;


import com.sai.core.constants.StatusConstant;
import com.sai.core.dto.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by ZhouXiang sai on 2017/6/29.
 */

public class BaseController<T> {


    public ResultCode successResult() {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, null);
    }

    public ResultCode successResult(T data) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, data);
    }

    public ResultCode successResultMsg(String msg) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, msg, null);
    }

    public ResultCode successResult(String msg, T data) {
        return getResult(StatusConstant.RESULT_SUCCESS_CODE, msg, data);
    }

    public ResultCode failResult() {
        return getResult(StatusConstant.RESULT_FAIL_CODE, StatusConstant.RESULT_FAIL_MSG, null);
    }

    public ResultCode failResultMsg(String msg) {
        return getResult(StatusConstant.RESULT_FAIL_CODE, msg, null);
    }

    public ResultCode failResult(String code, String msg) {
        return getResult(code, msg, null);
    }

    public ResultCode getResult(String code, String msg, T data) {
        ResultCode responseCode = new ResultCode();
        responseCode.setCode(code);
        responseCode.setMsg(msg);
        responseCode.setData(data);
        return responseCode;
    }

    public ResultCode getResult(ResultCode<T> resultCode) {
        return getResult(resultCode.getCode(), resultCode.getMsg(), resultCode.getData());
    }

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception e){
        e.printStackTrace();
        return failResult();
    }


}
