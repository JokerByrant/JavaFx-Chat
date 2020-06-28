package com.sxh.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.sxh.app.api.ApiUrl;
import com.sxh.app.fx.Cache;
import com.sxh.app.fx.FxmlView;
import com.sxh.app.utils.HttpUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
    }

    /**
     * 检查登录信息
     */
    private void checkLoginInfo() {
        if (StringUtils.isBlank(username.getText())) {
            verification.setText("用户名不能为空！");
            return;
        }
        if (StringUtils.isBlank(password.getText())) {
            verification.setText("密码不能为空！");
            return;
        }

        sendLoginRequest(username.getText(), password.getText());
    }

    /**
     * 发送登录请求
     * @param username
     * @param password
     * @return
     */
    private void sendLoginRequest(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_STREAM_JSON);
        HttpEntity<String> entity = new HttpEntity(jsonObject.toJSONString(), headers);
        ResponseEntity<String> responseEntity = HttpUtil.doPostForEntity(ApiUrl.LOGIN, entity);
        jsonObject = JSONObject.parseObject(responseEntity.getBody());
        if (responseEntity.getHeaders().containsKey("authorization") && jsonObject.getIntValue("code") == 200) {
            doLoginSuccess();
        } else {
            verification.setText(jsonObject.getString("message"));
            return;
        }
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
//        $(FxmlView.MAIN, "userInfo", Label.class).setText(username.getText());
    }
}
