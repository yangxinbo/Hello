package com.shanks.server;

import com.withufuture.game.proto.Message;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * FileName    : 服务启动监听器
 * Description :
 *
 * @author : 毒液
 * @version : 1.0
 * Create Date : 2021/3/23 15:07
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Slf4j
public class HelloWorldServer {

    private int port;

    public HelloWorldServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new HelloWorldServer(port).start();
    }

    public void start() {
        //new 一个主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //new 一个工作线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap sbs = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    /**
                     * netty服务初始化器
                     * @param ch
                     * @throws Exception
                     */
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        ch.pipeline().addLast("decoder",new ProtobufDecoder(Message.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        ch.pipeline().addLast("encoder", new ProtobufEncoder());
                        ch.pipeline().addLast(new HelloWorldServerHandler());
                    }
                })
                //设置队列大小
                .option(ChannelOption.SO_BACKLOG, 128)
                // 两小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            // 绑定端口，开始接收进来的连接
            ChannelFuture future = sbs.bind(port).sync();
            log.info("Server start listen at: {}", port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("[] e:{}", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
