package com.sai.web.utils;

import com.alibaba.fastjson.JSONObject;
import com.sai.core.constants.Constants;
import com.sai.core.utils.ConsulUtil;
import com.sai.core.utils.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.*;

public class ConsulPropertySourceLoader implements PropertySourceLoader {
    @Override
    public String[] getFileExtensions() {
        return new String[]{"consul"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        Map<String, Object> source = this.process(properties);
        List<PropertySource<?>> propertySources = new ArrayList();
        if (!source.isEmpty()) {
            propertySources.add(new MapPropertySource(name, source));
        }
        return propertySources;
    }

    private Map<String, Object> process(Properties properties) {
        ConsulUtil consulUtil = ConsulUtil.getInstance();
        consulUtil.init(properties);
        String curProfile = properties.getProperty("spring.profiles.active");
        String applicationName = properties.getProperty("spring.application.name");
        String[] profileArray = new String[]{"all", curProfile};

        final Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("spring.application.name", applicationName);
        result.put("spring.profiles.active", curProfile);
        for (String profile : profileArray) {
            String keyPre = "envConfig/" + applicationName + "/" + profile;
            List<String> subKeyList = consulUtil.getSubKeys(keyPre);
            if (subKeyList != null && subKeyList.size() > 0) {
                for (String subKey : subKeyList) {
                    String value = consulUtil.getValue(subKey);
                    if (StringUtils.isBlank(value)) {
                        continue;
                    }
                    String theKey = subKey.replaceFirst(keyPre, "").replaceAll("/", Constants.SYMBOL_POINT).replaceFirst(Constants.SYMBOL_POINT, "");
                    theKey = theKey.toLowerCase();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = JSONUtil.toJSONObject(value);
                    } catch (Exception e) {
                    }
                    if (jsonObject == null) {
                        result.put(theKey, value);
                    } else {
                        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, Object> entry = iterator.next();
                            Object entryValue = entry.getValue();
                            if (entryValue != null) {
                                String entryKey = entry.getKey();
                                result.put(theKey + Constants.SYMBOL_POINT + entryKey, entryValue);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
