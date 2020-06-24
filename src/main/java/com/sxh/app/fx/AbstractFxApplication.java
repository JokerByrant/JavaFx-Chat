package com.sxh.app.fx;

import com.sxh.app.controller.LoginController;
import com.sxh.app.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CompletableFuture;

/**
 * 自定义一个javafx容器
 * @author sxh
 * @date 2020/6/19
 */
public abstract class AbstractFxApplication extends Application {
    protected static Logger logger = LoggerFactory.getLogger(AbstractFxApplication.class);
    
    // spring context
    protected static ConfigurableApplicationContext applicationContext;

    // pre-load views
    protected static FxmlView initView; // 主界面视图实例
    
    /**
     * 启动Spring容器和JFX容器
     * @param appClass
     * @param args
     */
    public static void run (final Class<? extends Application> appClass, final String[] args,
                            final FxmlView _initView) {
        initView = _initView;
        
        // 加载Spring容器
        CompletableFuture.supplyAsync(() -> applicationContext = SpringApplication.run(appClass, args))
                .whenComplete((ctx, throwable) -> {
                    if (throwable != null) {
                        logger.error("Failed to load spring application context: ", throwable);
                    } else {
                        // 加载fx容器
                        launch(appClass, args);
                    }
                });
    }
    
    // 启动前的初始化操作
    @Override
    public void init() throws Exception {
        super.init();
    }

    // 启动主程序
    @Override
    public void start(Stage main) throws Exception {
        new MainController().init();
        new LoginController().init();
        
    }
    
    // 停止主程序
    @Override
    public void stop() throws Exception {
        super.stop();
        if (applicationContext != null) { // 关闭Spring容器
            applicationContext.close();
        }
    }

}
