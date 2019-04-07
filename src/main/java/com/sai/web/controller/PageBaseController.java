package com.sai.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sai.core.dto.ResultCode;
import com.sai.web.service.PageService;
import com.sai.web.utils.PageUtil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class PageBaseController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object add(@PathVariable Map pathVariables, @RequestBody Map addInfo) throws IOException {
        if (pathVariables != null) {
            addInfo.putAll(pathVariables);
        }
        ResultCode resultCode = getPageService().add(addInfo);
        return getResult(resultCode);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object list(@PathVariable Map pathVariables, @RequestParam Map params) throws IOException {
        if (pathVariables != null) {
            params.putAll(pathVariables);
        }
        Page page = PageUtil.page(params);
        PageInfo pageInfo = (PageInfo)getPageService().list(params);
        return getSuccessResult(pageInfo);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object update(@PathVariable Map pathVariables, @RequestBody Map UpdateInfoMap) throws IOException {
        if (pathVariables != null) {
            UpdateInfoMap.putAll(pathVariables);
        }
        ResultCode resultCode = getPageService().update(UpdateInfoMap);
        return getResult(resultCode);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") BigDecimal id) throws IOException {
        ResultCode resultCode = getPageService().get(id);
        return getResult(resultCode);
    }

    protected PageService getPageService() {
        return null;
    }

    ;
}
