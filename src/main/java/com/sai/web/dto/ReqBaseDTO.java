package com.sai.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class ReqBaseDTO implements Serializable {
    private Long id;
    private Date modifyTime;
    private Date createTime;
    private Integer pageNum;
    private Integer pageSize = 20;
}
