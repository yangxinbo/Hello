package com.shanks.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * FileName    : 编码器
 * Description :
 *
 * @author : Venom
 * @version : 1.0
 * Create Date : 2021/4/15 17:00
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@ChannelHandler.Sharable
public class ProtoEncoder extends MessageToMessageEncoder<WrapperProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, WrapperProtocol msg, List<Object> out) throws Exception {
        ByteBuf buf = ctx.alloc().buffer();
        msg.encode(buf);
        out.add(buf);
    }
}
