package com.sai.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sai.core.dto.ResultCode;
import com.sai.web.aop.MainMapper;
import com.sai.web.diamond.BaseDO;
import com.sai.web.dto.BaseDTO;
import com.sai.web.dto.ReqBaseDTO;
import com.sai.web.mapper.BaseMapper;
import com.sai.web.mapper.FrontBaseMapper;
import com.sai.web.service.FrontPageService;
import com.sai.web.utils.PageUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class FrontPageBaseServiceImpl<REQ extends ReqBaseDTO, DTO extends BaseDTO, DO extends BaseDO>
        implements FrontPageService<REQ, DTO> , InitializingBean {

    protected FrontBaseMapper<DO, REQ> frontBaseMapper;

    protected abstract DO req2Do(REQ req);

    protected abstract DTO do2Dto(DO infoDo);


    @Override
    public void afterPropertiesSet() throws Exception {
        Field[] fields=this.getClass().getDeclaredFields();
        if(fields!=null && fields.length>0){
            for(Field field : fields){
                if(field.isAnnotationPresent(MainMapper.class)){
                    field.setAccessible(true);
                    this.frontBaseMapper = (FrontBaseMapper)field.get(this);
                    field.setAccessible(false);
                    break;
                }
            }
        }
    }
    @Override
    public List<DTO> query(REQ params) {
        if(!PageUtil.startPage(params)){
            return null;
        }
        List selectResult = frontBaseMapper.selectList(params);
        List<DTO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(selectResult)) {
            selectResult.stream().forEach(record -> {
                resultList.add(do2Dto((DO)record));
            });
        }
        if (selectResult instanceof Page) {
            selectResult.clear();
            selectResult.addAll(resultList);
            return selectResult;
        }
        return resultList;
    }

    @Override
    public DTO get(Long id) {
        DO result = frontBaseMapper.selectByPrimaryKey(id);
        return do2Dto(result);
    }

}
