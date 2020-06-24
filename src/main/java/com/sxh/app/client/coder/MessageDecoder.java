package com.sxh.app.client.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 处理传入的消息，相当于解码器
 * @author sxh
 * @date 2020/4/26
 */
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String msg = in.readCharSequence(in.readableBytes(), Charset.forName("utf-8")).toString();
        out.add(msg);
    }
}
