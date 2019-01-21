package com.sai.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sai.core.dto.ResultCode;
import com.sai.web.service.PageService;
import com.sai.web.utils.PageUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.math.BigDecimal;

public class PageBaseController<T> extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object add(@RequestBody T  addInfo) throws IOException {
        ResultCode<T> resultCode = getPageService().add(addInfo);
        return getResult(resultCode);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object list(JSONObject paramJson) throws IOException {
        Page page = PageUtil.page(paramJson, getPageService());
        return getSuccessResult(page);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object update(@RequestBody T roleinfo) throws IOException {
        ResultCode<T> resultCode = getPageService().update(roleinfo);
        return getResult(resultCode);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") BigDecimal id) throws IOException {
        ResultCode<T> resultCode = getPageService().get(id);
        return getResult(resultCode);
    }

    protected PageService getPageService(){
        return null;
    };
}
