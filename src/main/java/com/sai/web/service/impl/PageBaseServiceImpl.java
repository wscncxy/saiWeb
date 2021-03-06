package com.sai.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sai.core.dto.ResultCode;
import com.sai.web.aop.MainMapper;
import com.sai.web.aop.MainService;
import com.sai.web.diamond.BaseDO;
import com.sai.web.dto.BaseDTO;
import com.sai.web.dto.ReqBaseDTO;
import com.sai.web.mapper.BaseMapper;
import com.sai.web.service.FrontPageService;
import com.sai.web.service.PageService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class PageBaseServiceImpl<REQ extends ReqBaseDTO, DTO extends BaseDTO, DO extends BaseDO>
        implements PageService<REQ, DTO>, InitializingBean {

    protected BaseMapper<DO, REQ> baseMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        Field[] fields=this.getClass().getDeclaredFields();
        if(fields!=null && fields.length>0){
            for(Field field : fields){
                if(field.isAnnotationPresent(MainMapper.class)){
                    field.setAccessible(true);
                    this.baseMapper = (BaseMapper)field.get(this);
                    field.setAccessible(false);
                    break;
                }
            }
        }
    }

    protected abstract DO req2Do(REQ req);

    protected abstract DTO do2Dto(DO infoDo);

    protected ResultCode editValid(REQ info) {
        return ResultCode.success();
    }

    @Override
    public List<DTO> query(REQ params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List selectResult = baseMapper.selectList(params);
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
    public ResultCode add(REQ addInfo) {
        ResultCode saveValidResult = editValid(addInfo);
        if (saveValidResult.isSuccess()) {
            return saveValidResult;
        }
        DO record=req2Do(addInfo);
        int result = baseMapper.insert(record);
        if (result == 1) {
            return afterEdit(record);
        }
        return ResultCode.fail();
    }

    protected ResultCode afterEdit(DO record) {
        return ResultCode.success();
    }

    @Override
    public ResultCode update(REQ updateInfo) {
        Long id = updateInfo.getId();
        if(id == null || id<1){
            return ResultCode.fail("ID 错误");
        }
        DO record=req2Do(updateInfo);
        int result = baseMapper.updateById(record);
        if (result == 1) {
            return afterEdit(record);
        }
        return ResultCode.fail();
    }

    @Override
    public ResultCode<DTO> get(Long id) {
        DO result = baseMapper.selectByPrimaryKey(id);
        return ResultCode.success(do2Dto(result));
    }

}
