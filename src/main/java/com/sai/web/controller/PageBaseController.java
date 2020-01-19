package com.sai.web.controller;

import com.github.pagehelper.PageInfo;
import com.sai.core.dto.ResultCode;
import com.sai.web.dto.BaseDTO;
import com.sai.web.dto.ReqBaseDTO;
import com.sai.web.service.PageService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

public class PageBaseController<REQ extends ReqBaseDTO, DTO extends BaseDTO>
        extends BaseController {

    private PageService<REQ, DTO> pageService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object add(@RequestBody REQ addInfo) throws IOException {
        ResultCode resultCode = pageService.add(addInfo);
        return getResult(resultCode);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object page(@RequestParam REQ params) throws IOException {
        PageInfo<DTO> pageInfo = (PageInfo) pageService.query(params);
        return getSuccessResult(pageInfo);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object update(@RequestBody REQ UpdateInfoMap) throws IOException {
        ResultCode resultCode = pageService.update(UpdateInfoMap);
        return getResult(resultCode);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) throws IOException {
        ResultCode<DTO> resultCode = pageService.get(id);
        return getResult(resultCode);
    }

}
