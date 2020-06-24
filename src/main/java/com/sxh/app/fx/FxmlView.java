package com.sxh.app.fx;

import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * 页面枚举类
 * @author FengJuan
 * @date 2020年6月11日
 * @Description 
 *
 */
public enum FxmlView {
    LOGIN { //登录页
        @Override
        public String fName() {
            return "login";
        }

        @Override
        public String title() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String fxml() {
            return "/template/login/login.fxml";//主页面xml
        }

        @Override
        public Stage stage() {
            return Cache.ControllerMap.get("login");
        }

    },
    MAIN { //主页
        @Override
        public String fName() {
            return "main";
        }

        @Override
        public String title() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String fxml() {
            return "/template/main/main.fxml";//主页面xml
        }

        @Override
        public Stage stage() {
            return Cache.ControllerMap.get("main");
        }

    };
	
    public abstract String fName();
    public abstract String title();
    public abstract String fxml();
    public abstract Stage stage();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
