package com.sxh.app.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author sxh
 * @date 2020/6/24
 */
public class MessageHandler extends ChannelInboundHandlerAdapter {
    // 读取客户端传输的消息并处理
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 经过了消息解码器TransferMsgHandler的处理，msg已经被转换为而来Message类型
            String content = (String) msg;
            System.out.println(content);

            ctx.writeAndFlush(content);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
