package com.sai.web.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sai.web.service.PageService;

import java.io.Serializable;

public class PageUtil implements Serializable {

    public static Page page(JSONObject params, PageService pageService) {
        Integer pageSize = params.getInteger("pageSize");
        Integer pageNum = params.getInteger("pageNum");
        if (pageSize == null || pageSize < 1 || pageSize > 20) {
            pageSize = 20;
        }
        if (pageNum == null || pageNum < 1 || pageNum > Integer.MAX_VALUE) {
            pageNum = 1;
        }
        Page page = PageHelper.startPage(pageNum,pageSize);
        page = (Page)pageService.list(params);
        return page;
    }
}