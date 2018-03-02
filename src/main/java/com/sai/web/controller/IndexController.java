package com.sai.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by ZhouXiang on 2017/10/19 0019 16:33.
 */

public class IndexController extends BaseController {


    public String add() throws IOException {
        return getSuccessResult();
    }

}
