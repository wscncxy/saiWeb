package com.sai.web.controller;

import com.github.pagehelper.PageInfo;
import com.sai.core.dto.ResultCode;
import com.sai.web.aop.MainService;
import com.sai.web.dto.BaseDTO;
import com.sai.web.dto.ReqBaseDTO;
import com.sai.web.service.FrontPageService;
import com.sai.web.service.PageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Field;

public class PageBaseController<REQ extends ReqBaseDTO, DTO extends BaseDTO>
        extends BaseController implements InitializingBean {

    private PageService<REQ, DTO> pageService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Field[] fields=this.getClass().getDeclaredFields();
        if(fields!=null && fields.length>0){
            for(Field field : fields){
                if(field.isAnnotationPresent(MainService.class)){
                    field.setAccessible(true);
                    this.pageService = (PageService)field.get(this);
                    field.setAccessible(false);
                    break;
                }
            }
        }
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object add(@RequestBody REQ addInfo) throws IOException {
        ResultCode resultCode = pageService.add(addInfo);
        return getResult(resultCode);
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public Object page(@RequestParam REQ params) throws IOException {
        PageInfo<DTO> pageInfo = (PageInfo) pageService.query(params);
        return successResult(pageInfo);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
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
