package com.sai.web.dto;


import com.sai.core.constants.StatusConstant;
import com.sai.core.utils.StringUtil;

/**
 * Created by ZhouXiang on 2017/8/15 0015 13:38.
 */
public class ResponseCode<T> {

    private String code;
    private String msg;
    private T data;

    public ResponseCode() {
    }

    public ResponseCode(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return StringUtil.equals(StatusConstant.RESULT_SUCCESS_CODE, code);
    }

    public final static <T> ResponseCode<T> fail(String msg) {
        return new ResponseCode<T>(StatusConstant.RESULT_FAIL_CODE, msg, null);
    }

    public final static <T> ResponseCode<T> fail() {
        return new ResponseCode<T>(StatusConstant.RESULT_FAIL_CODE, StatusConstant.RESULT_FAIL_MSG, null);
    }

    public final static <T> ResponseCode<T> success(String msg, T data) {
        return new ResponseCode<T>(StatusConstant.RESULT_SUCCESS_CODE, msg, data);
    }

    public final static <T> ResponseCode<T> success(T data) {
        return new ResponseCode<T>(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, data);
    }

    public final static <T> ResponseCode<T> success() {
        return new ResponseCode<T>(StatusConstant.RESULT_SUCCESS_CODE, StatusConstant.RESULT_SUCCESS_MSG, null);
    }

    public final static <T> ResponseCode<T> result(String code, String msg) {
        return new ResponseCode<T>(code, msg, null);
    }

    public final static <T> ResponseCode<T> result(String code, String msg, T data) {
        return new ResponseCode<T>(code, msg, data);
    }
}
