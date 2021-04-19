package com.shanks.client;

import com.withufuture.game.proto.Wrapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * FileName    : com.shanks.client
 * Description :
 *
 * @author : 毒液
 * @version : 1.0
 * Create Date : 2021/3/23 15:11
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Slf4j
public class HelloWorldClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                //该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        //ch.pipeline().addLast("decoder", new ProtobufDecoder(Message.getDefaultInstance()));
                        //ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        //ch.pipeline().addLast("encoder", new ProtobufEncoder());
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 4, 4));
                        ch.pipeline().addLast("encoder", new TestProtobufEncoder());
                        ch.pipeline().addLast("decoder", new TestProtobufDecoder());
                        ch.pipeline().addLast(new HelloWorldClientHandler());
                    }
                });
        try {
            // 连接
            ChannelFuture future = b.connect(HOST, PORT).sync();


            for (int i = 0; i < 10; i++) {
                Wrapper wrapper = Wrapper.newBuilder()
                        .setRequestId(String.valueOf(i))
                        .setCode("1")
                        .setMsg("msg").build();

                byte by = 1;
                WrapperHeader header = new WrapperHeader();
                header.setRequestId(by);
                header.setVersion(by);
                header.setSerializer(by);
                header.setCommand(by);
                header.setLength(wrapper.toByteArray().length);

                WrapperProtocol protocol = new WrapperProtocol();
                protocol.setHeader(header);
                protocol.setBody(wrapper);
                // 发送消息
                future.channel().writeAndFlush(protocol);
                log.info("msg{},{}", i, protocol);
            }

            // 等待连接被关闭
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
