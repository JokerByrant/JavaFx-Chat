package com.jfx.app.controller;

import com.jfx.app.fx.Cache;
import com.jfx.app.fx.FxmlView;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author sxh
 * @date 2020/6/22
 */
public abstract class BaseController extends Stage implements Initializable {

    public abstract void init();
    
    /**
     * 初始化Controller
     * @param fxmlView
     * @param handler
     */
    protected void init(Stage stage, FxmlView fxmlView, EventHandler handler) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlView.fxml()));
        stage.setTitle(fxmlView.title());
        stage.setScene(new Scene(root));
        Cache.ControllerMap.put(fxmlView.fName(), stage);
        stage.setOnCloseRequest(handler);
    }

    /**
     * 获取指定页面的元素
     * @param fxmlView 指定页面
     * @param id 元素id
     * @param clazz 获取的元素类型
     * @param <T>
     * @return
     */
    public <T> T $(FxmlView fxmlView, String id, Class<T> clazz) {
        return (T) fxmlView.stage().getScene().getRoot().lookup("#" + id);
    }
}
