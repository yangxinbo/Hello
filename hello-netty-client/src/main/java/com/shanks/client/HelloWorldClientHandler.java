package com.shanks.client;

import com.shanks.common.WrapperHeader;
import com.shanks.common.WrapperProtocol;
import com.withufuture.game.proto.Test;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * FileName    : com.shanks.client
 * Description :
 *
 * @author : 毒液
 * @version : 1.0
 * Create Date : 2021/3/23 15:10
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Slf4j
@ChannelHandler.Sharable
public class HelloWorldClientHandler extends SimpleChannelInboundHandler<WrapperProtocol> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 10; i++) {
            Test wrapper = Test.newBuilder()
                    .setRequestId(String.valueOf(i))
                    .setCode("1")
                    .setMsg("msg").build();

            byte by = (byte) i;
            WrapperHeader header = new WrapperHeader();
            header.setRequestId(by);
            header.setVersion(by);
            header.setSerializer(by);
            header.setCommand(by);
            header.setLength(wrapper.toByteArray().length);

            WrapperProtocol protocol = new WrapperProtocol();
            protocol.setHeader(header);
            protocol.setBody(wrapper);
            ctx.writeAndFlush(protocol);
            log.info("msg{},{}", i, protocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WrapperProtocol wrapper) throws Exception {
        log.info("wrapper:{}", wrapper);
    }
}
