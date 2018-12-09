package com.sai.web.utils;

import org.springframework.web.client.RestTemplate;

public class DefaultRestTemplate extends RestTemplate {

    public static RestTemplate getInstance() {
        RestTemplate restTemplate = new DefaultRestTemplate();
        return restTemplate;
    }
}
