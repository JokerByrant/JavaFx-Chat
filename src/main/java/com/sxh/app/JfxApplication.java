package com.sxh.app;

import com.sxh.app.fx.AbstractFxApplication;
import com.sxh.app.fx.FxmlView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sxh
 * @date 2020/6/19
 */
@SpringBootApplication
public class JfxApplication extends AbstractFxApplication {
    public static void main(String[] args) {
        run(JfxApplication.class, args, FxmlView.LOGIN);
    }
}
