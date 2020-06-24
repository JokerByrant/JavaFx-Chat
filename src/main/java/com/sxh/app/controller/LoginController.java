package com.sxh.app.controller;

import com.sxh.app.fx.Cache;
import com.sxh.app.fx.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 登录Controller
 * @author sxh
 * @date 2020/6/19
 */
public class LoginController extends BaseController {
    @FXML
    protected TextField username;
    @FXML
    protected PasswordField password;
    @FXML
    protected Label verification;
    
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Override
    public void init() {
        try {
            super.init(this, FxmlView.LOGIN);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        this.setOnCloseRequest(event -> {
            System.exit(0);
        });
        
        super.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                login();
            }
        });
        password.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ENTER == event.getCode()) {
                login();
            }
        });
    }

    @FXML
    private void login() {
        System.out.println("Send Login Request -> username: " + username.getText() + ",  password: " + password.getText());
        checkLoginInfo();
    }

    @FXML
    private void register() {
        System.out.println("Send register Request -> username: " + username.getText() + ",  password: " + password.getText());
    }

    /**
     * 检查登录信息
     */
    private void checkLoginInfo() {
        if (StringUtils.isBlank(username.getText())) {
            verification.setText("用户名不能为空！");
            return;
        }
//        if (!username.getText().equals("admin")) {
//            verification.setText("用户名有误！");
//            return;
//        }
        if (StringUtils.isBlank(password.getText())) {
            verification.setText("密码不能为空！");
            return;
        }
//        if (!password.getText().equals("123456")) {
//            verification.setText("密码错误！");
//            return;
//        }

        doLoginSuccess();
    }

    /**
     * 登录成功
     */
    private void doLoginSuccess() {
        verification.setText("");
        Cache.currentUser.setName(username.getText());
        Cache.currentUser.setPassword(password.getText());
        FxmlView.LOGIN.stage().close();
        FxmlView.MAIN.stage().show();
        $(FxmlView.MAIN, "userInfo", Label.class).setText(username.getText());
    }
}
