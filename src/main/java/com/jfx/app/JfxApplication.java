package com.jfx.app;

import com.jfx.app.fx.AbstractFxApplication;
import com.jfx.app.fx.FxmlView;
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
