package com.sai.web.utils;

import java.util.ArrayList;
import java.util.List;

public class IgnoreUrlUtils {

    private static final List<String> defaultIgnoreUriList = new ArrayList<>();
    private static final List<String> defaultIgnoreUriPreList = new ArrayList<>();

    static {
        defaultIgnoreUriList.add("/login");
        defaultIgnoreUriPreList.add("/test");
    }

    public static boolean isUriIsIgnored(String uri) {
        if (defaultIgnoreUriList.contains(uri)) {
            return true;
        }
        for (String pre : defaultIgnoreUriPreList) {
            if (uri.startsWith(pre)) {
                return true;
            }
        }
        return false;
    }
}
