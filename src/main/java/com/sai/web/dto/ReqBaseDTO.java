package com.sai.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ReqBaseDTO implements Serializable {
    private Integer pageNum;
    private Integer pageSize = 20;
}
