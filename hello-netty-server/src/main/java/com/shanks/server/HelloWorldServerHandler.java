package com.shanks.server;

import com.withufuture.game.proto.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * FileName    : 服务端处理器
 * Description :
 *
 * @author : 毒液
 * @version : 1.0
 * Create Date : 2021/3/23 15:06
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Slf4j
public class HelloWorldServerHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        log.info("msg:{}", message);
    }
}
