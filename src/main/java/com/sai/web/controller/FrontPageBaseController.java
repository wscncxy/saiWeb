package com.sai.web.controller;

import com.github.pagehelper.Page;
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class FrontPageBaseController<REQ extends ReqBaseDTO, DTO extends BaseDTO>
        extends BaseController implements InitializingBean {

    protected FrontPageService<REQ, DTO> frontPageService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Field[] fields=this.getClass().getDeclaredFields();
        if(fields!=null && fields.length>0){
            for(Field field : fields){
                if(field.isAnnotationPresent(MainService.class)){
                    field.setAccessible(true);
                    this.frontPageService = (FrontPageService)field.get(this);
                    field.setAccessible(false);
                    break;
                }
            }
        }
    }

    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Object page(@RequestBody REQ params) throws IOException {
        Page<DTO> pageInfo = (Page) frontPageService.query(params);
        return successResult(pageInfo);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") Long id) throws IOException {
        DTO resultCode = frontPageService.get(id);
        return successResult(resultCode);
    }

}
