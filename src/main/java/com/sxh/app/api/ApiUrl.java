package com.sxh.app.api;

import java.util.ResourceBundle;

/**
 * @author sxh
 * @date 2020/6/28
 */
public enum ApiUrl {
    LOGIN {
        @Override
        public String url() {
            return getStringFromResourceBundle() + "/login";
        }
    };
    
    public abstract String url();

    /**
     * 获取接口的调用地址
     * @return
     */
    String getStringFromResourceBundle(){
        return "http://" + ResourceBundle.getBundle("Bundle").getString("api.base.url");
    }
}
