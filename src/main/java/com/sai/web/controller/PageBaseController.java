package com.sai.web.controller;

import com.github.pagehelper.PageInfo;
import com.sai.core.dto.ResultCode;
import com.sai.web.dto.EditBaseDTO;
import com.sai.web.dto.ReqBaseDTO;
import com.sai.web.dto.RespBaseDTO;
import com.sai.web.service.PageService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

public class PageBaseController<REQ extends ReqBaseDTO, RESP extends RespBaseDTO, EDIT extends EditBaseDTO>
        extends BaseController {

    private PageService<REQ, RESP, EDIT> pageService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object add(@RequestBody EDIT addInfo) throws IOException {
        ResultCode resultCode = pageService.add(addInfo);
        return getResult(resultCode);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object page(@RequestParam REQ params) throws IOException {
        PageInfo<RESP> pageInfo = (PageInfo) pageService.query(params);
        return getSuccessResult(pageInfo);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object update(@RequestBody EDIT UpdateInfoMap) throws IOException {
        ResultCode resultCode = pageService.update(UpdateInfoMap);
        return getResult(resultCode);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) throws IOException {
        ResultCode<RESP> resultCode = pageService.get(id);
        return getResult(resultCode);
    }

}
