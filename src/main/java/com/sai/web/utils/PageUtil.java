package com.sai.web.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sai.web.service.PageService;
import org.apache.commons.collections4.MapUtils;

import java.io.Serializable;
import java.util.Map;

public class PageUtil implements Serializable {

    public static Page page(Map params, PageService pageService) {
        Integer pageSize = MapUtils.getInteger(params,"pageSize");
        Integer pageNum = MapUtils.getInteger(params,"pageNum");
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