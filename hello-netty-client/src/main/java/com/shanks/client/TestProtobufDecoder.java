package com.shanks.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * FileName    : 解码器
 * Description :
 *
 * @author : Venom
 * @version : 1.0
 * Create Date : 2021/4/15 16:59
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Slf4j
public class TestProtobufDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        WrapperProtocol protocol = new WrapperProtocol();
        protocol.decode(msg);
        out.add(protocol);
    }

    /*
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 标记一下当前的readIndex的位置
        in.markReaderIndex();
        // 判断包头长度
        if (in.readableBytes() < 2) {// 不够包头
            return;
        }
        // 读取传送过来的消息的长度。
        int length = in.readUnsignedShort();
        // 长度如果小于0
        if (length < 0) {// 非法数据，关闭连接
            ctx.close();
        }
        if (length > in.readableBytes()) {// 读到的消息体长度如果小于传送过来的消息长度
            // 重置读取位置
            in.resetReaderIndex();
            return;
        }

        ByteBuf frame = Unpooled.buffer(length);
        in.readBytes(frame);
        try {
            byte[] inByte = frame.array();
            // 字节转成对象
            ProtoMsg.Message msg = ProtoMsg.Message.parseFrom(inByte);
            if (msg != null) {
                // 获取业务消息头
                out.add(msg);
            }
        } catch (Exception e) {
            log.info(ctx.channel().remoteAddress() + ",decode failed.", e);
        }
    }
     */
}
