package com.shanks.server;

import com.shanks.common.WrapperProtocol;
import io.netty.channel.ChannelHandler;
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
@ChannelHandler.Sharable
public class HelloWorldServerHandler extends SimpleChannelInboundHandler<WrapperProtocol> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.warn("用户:[{}] 连接!!", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WrapperProtocol message) throws Exception {
        log.info("msg:{}", message);
    }
}
