package com.jfx.app.controller;

import com.jfx.app.fx.Cache;
import com.jfx.app.fx.FxmlView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author sxh
 * @date 2020/6/22
 */
public class MainController extends BaseController {
    @FXML
    protected Label userInfo;

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Override
    public void init() {
        try {
            super.init(this, FxmlView.MAIN, new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void exit() {
        Cache.ControllerMap.get("main").close();
        Cache.ControllerMap.get("login").show();
    }
}
