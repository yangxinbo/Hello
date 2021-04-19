package com.shanks.client;

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
public class HelloWorldClientHandler extends SimpleChannelInboundHandler<WrapperProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WrapperProtocol message) throws Exception {
        log.info("client:{}", message);
    }
}
