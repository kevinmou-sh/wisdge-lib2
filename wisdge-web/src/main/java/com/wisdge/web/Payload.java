package com.wisdge.web;

import com.wisdge.utils.JSonUtils;
import com.wisdge.utils.MapUtils;
import com.wisdge.web.springframework.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class Payload {
    private Map<String, Object> params;

    public Payload(HttpServletRequest request) throws IOException {
        params = JSonUtils.read(WebUtils.getRequestPayload(request), Map.class);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public <T> T get(String key) {
        if (params.containsKey(key)) {
            return (T)params.get(key);
        } else {
            return null;
        }
    }

    public <T> T getBean(Class<T> clazz, String key) throws Exception {
        if (params.containsKey(key)) {
            return MapUtils.toBean(clazz, (Map) params.get(key));
        } else {
            return null;
        }
    }

    public String getString(String key, String defaultValue) {
        if (! params.containsKey(key))
            return defaultValue;

        return get(key);
    }

    public long getLong(String key, long defaultValue) {
        if (! params.containsKey(key))
            return defaultValue;

        return get(key);
    }

    public int getInt(String key, int defaultValue) {
        if (! params.containsKey(key))
            return defaultValue;

        return get(key);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (! params.containsKey(key))
            return defaultValue;

        Object obj = params.get(key);
        if (obj instanceof Boolean)
            return ((Boolean)obj).booleanValue();
        else if (obj instanceof String)
            return Boolean.valueOf((String) obj);
        else if (obj instanceof Integer)
            return ((Integer)obj).intValue() != 0;
        else
            return false;
    }

}
