package com.sxh.app;

import com.sxh.app.client.NettyClient;
import com.sxh.app.fx.AbstractFxApplication;
import com.sxh.app.fx.FxmlView;
import com.sxh.app.utils.SpringUtils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

/**
 * @author sxh
 * @date 2020/6/19
 */
@SpringBootApplication
public class JfxApplication extends AbstractFxApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(JfxApplication.class);
    
    public static void main(String[] args) {
        run(JfxApplication.class, args, FxmlView.LOGIN);
    }

    @Override
    public void run(String... args){
        NettyClient nettyClient = SpringUtils.getBean(NettyClient.class);

        logger.info("开始与netty服务器建立连接...");
        // 连接监听事件
        GenericFutureListener<ChannelFuture> connectListener = (ChannelFuture f) -> {
            final EventLoop eventLoop = f.channel().eventLoop();
            if (!f.isSuccess()) { // 连接失败，进行重试
                logger.error("连接失败，在10s后尝试重新连接...");
                // 每10s执行一次
                eventLoop.schedule(() -> nettyClient.doConnect("localhost", 8888), 10, TimeUnit.SECONDS);
            } else {
                logger.info("连接IM服务器成功！");
                logger.info("" + f.channel().remoteAddress() + f.channel().localAddress());
            }
        };

        nettyClient.setConnectListener(connectListener);
        nettyClient.doConnect("localhost", 8888);
    }
}
