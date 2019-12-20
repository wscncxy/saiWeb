package com.sai.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sai.core.dto.ResultCode;
import com.sai.web.diamond.BaseDO;
import com.sai.web.dto.EditBaseDTO;
import com.sai.web.dto.ReqBaseDTO;
import com.sai.web.dto.RespBaseDTO;
import com.sai.web.mapper.BaseMapper;
import com.sai.web.service.PageService;
import org.apache.shiro.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class PageBaseServiceImpl<REQ extends ReqBaseDTO, RESP extends RespBaseDTO, EDIT extends EditBaseDTO, DO extends BaseDO>
        implements PageService<REQ, RESP, EDIT> {

    protected BaseMapper<DO, REQ> mainMapper;

    protected abstract void setBaseMapper(BaseMapper mainMapper);

    protected abstract DO dto2Do(EDIT edit);

    protected abstract RESP do2Dto(DO infoDo);

    protected ResultCode editValid(EDIT info) {
        return ResultCode.success();
    }

    @Override
    public List<RESP> query(REQ params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<DO> selectResult = mainMapper.select(params);
        List<RESP> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(selectResult)) {
            selectResult.stream().forEach(record -> {
                resultList.add(do2Dto(record));
            });
        }
        Page page = PageHelper.getLocalPage();
        if (page != null) {
            page.clear();
            page.addAll(resultList);
            return page;
        }
        return resultList;
    }

    @Override
    public ResultCode add(EDIT addInfo) {
        ResultCode saveValidResult = editValid(addInfo);
        if (saveValidResult.isSuccess()) {
            return saveValidResult;
        }
        DO record=dto2Do(addInfo);
        int result = mainMapper.insert(dto2Do(addInfo));
        if (result != 1) {
            return afterEdit(record);
        }
        return ResultCode.fail();
    }

    protected ResultCode afterEdit(DO record) {
        return ResultCode.success();
    }

    @Override
    public ResultCode update(EDIT updateInfo) {
        Long id = updateInfo.getId();
        if(id == null || id<1){
            return ResultCode.fail("ID 错误");
        }
        DO record=dto2Do(updateInfo);
        int result = mainMapper.updateById(record);
        if (result != 1) {
            return afterEdit(record);
        }
        return ResultCode.fail();
    }

    @Override
    public ResultCode<RESP> get(Long id) {
        DO result = mainMapper.selectById(id);
        return ResultCode.success(do2Dto(result));
    }

}
