package com.sai.web.diamond;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDO {
    private Long id;
    private Long createBy;
    private Date createTime = new Date();
    private Long modifyBy;
    private Date modifyTime = new Date();
    /**
     * 乐观锁
     */
    private String modifyVersion;
    private boolean isDelete = false;
}
