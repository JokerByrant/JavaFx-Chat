package com.sxh.app.utils;

import com.alibaba.fastjson.JSONObject;
import com.sxh.app.api.ApiUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Http工具类
 * @author sxh
 * @date 2020/6/28
 */
public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {}

    private static class SingletonRestTemplate {
        /**
         * 单例对象实例
         */
        static final RestTemplate INSTANCE = new RestTemplate();
    }

    private static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }

    /**
     * get方式获取数据
     * @param apiUrl
     * @return
     */
    public static JSONObject doGet(ApiUrl apiUrl) {
        return HttpUtil.getInstance().getForObject(apiUrl.url(), JSONObject.class, new Object[]{});
    }

    /**
     * post方式获取数据
     * @param apiUrl
     * @param data
     * @return
     */
    public static String doPostForObject(ApiUrl apiUrl, String data) {
        return HttpUtil.getInstance().postForObject(apiUrl.url(), null, String.class, data);
    }

    /**
     * post方式获取数据
     * @param apiUrl
     * @param entity
     * @param data
     * @return
     */
    public static ResponseEntity<String> doPostForEntity(ApiUrl apiUrl, HttpEntity<String> entity) {
        return HttpUtil.getInstance().postForEntity(apiUrl.url(), entity, String.class);
    }

}
