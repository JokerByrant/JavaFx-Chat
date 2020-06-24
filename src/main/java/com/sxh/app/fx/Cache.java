package com.sxh.app.fx;

import com.sxh.app.pojo.User;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存类
 * @author sxh
 * @date 2020/6/22
 */
public interface Cache {
    Map<String, Stage> ControllerMap = new HashMap<>(); 
    
    User currentUser = new User();
}
