package com.shanks.client;

import com.withufuture.game.proto.Message;
import com.withufuture.game.proto.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

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
                        ChannelPipeline p = ch.pipeline();
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast("decoder", new ProtobufDecoder(Message.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast("encoder", new ProtobufEncoder());
                        p.addLast(new HelloWorldClientHandler());
                    }
                });
        try {
            // 连接
            ChannelFuture future = b.connect(HOST, PORT).sync();

            Request.Builder req = Request.newBuilder();
            req.setName("1");
            Message.Builder msgReq = Message.newBuilder();
            msgReq.setReq(req);

            // 发送消息
            future.channel().writeAndFlush(msgReq.build());
            // 等待连接被关闭
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
