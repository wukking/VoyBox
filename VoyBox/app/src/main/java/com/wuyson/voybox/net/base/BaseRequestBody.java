package com.wuyson.voybox.net.base;


import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class BaseRequestBody implements Serializable {
    private String serviceName ="serviceName";
    private String sessionId ="sessionId";
    private Content content = new Content();

    public BaseRequestBody(String serviceName, String sessionId, Content content) {
        this.serviceName = serviceName;
        this.sessionId = sessionId;
        this.content = content;
    }

    static class Content {
        private Map<String,Object> maps;
        String access_channel_code = "access_channel_code";
        String app_version_code ="app_version_code";
        String package_name="package_name";
        int client_type = 1;
        int product_type = 1;
        String imei = "imei";
        String ad_code = "ad_code";
        String city_code = "city_code";
        String push_id = "push_id";

    }
}
