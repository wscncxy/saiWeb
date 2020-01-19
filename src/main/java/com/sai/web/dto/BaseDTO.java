package com.sai.web.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDTO implements Serializable {

    private Long id;
    private Long creatorId;
    private Date createTime;
    private Long modifierId;
    private Date modifyTime;
    /**
     * 乐观锁
     */
    private String modifyVersion;
}
