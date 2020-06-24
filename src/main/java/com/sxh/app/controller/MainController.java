package com.sxh.app.controller;

import com.sxh.app.fx.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
            super.init(this, FxmlView.MAIN);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        this.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void exit() {
        FxmlView.LOGIN.stage().show();
        FxmlView.MAIN.stage().close();
    }
}
