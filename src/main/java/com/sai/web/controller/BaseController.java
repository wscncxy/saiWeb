package com.sai.web.controller;

import com.sai.core.constants.StatusConstant;
import com.sai.core.dto.ResultData;
import com.sai.web.dto.BaseRequestDTO;
import com.sai.web.dto.BaseResponseDTO;

public class BaseController {

    protected BaseResponseDTO responseSuccess(BaseRequestDTO request, Object respData){
        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode(StatusConstant.RESULT_SUCCESS_CODE);
        return response;
    }

    protected BaseResponseDTO responseFail(String errorMsg){
        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode(StatusConstant.RESULT_FAIL_CODE);
        response.setMessage(errorMsg);
        return response;
    }


    protected BaseResponseDTO response(BaseRequestDTO request, ResultData resultData){
        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode(resultData.getCode());
        response.setMessage(resultData.getMsg());
        response.setData(resultData.getData());
        return response;
    }
}
