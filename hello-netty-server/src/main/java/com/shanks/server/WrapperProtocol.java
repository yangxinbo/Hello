package com.shanks.server;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.withufuture.game.proto.Wrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import lombok.Data;

/**
 * FileName    : com.shanks.server
 * Description :
 *
 * @author : Venom
 * @version : 1.0
 * Create Date : 2021/4/15 19:54
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Data
public class WrapperProtocol {

    private WrapperHeader header;

    private MessageLite body;

    /**
     * 编码
     *
     * @param buf 缓冲区
     */
    public void encode(ByteBuf buf) {
        header.encode(buf);
        buf.writeBytes(body.toByteArray());
    }

    public void decode(ByteBuf buf) throws InvalidProtocolBufferException {
        WrapperHeader header = new WrapperHeader();
        header.decode(buf);
        MessageLite body = Wrapper.getDefaultInstance();

        // 解码body
        final byte[] array;
        final int offset;
        final int length = buf.readableBytes();

        if (buf.hasArray()) {
            array = buf.array();
            offset = buf.arrayOffset() + buf.readerIndex();
        } else {
            array = ByteBufUtil.getBytes(buf, buf.readerIndex(), length, false);
            offset = 0;
        }
        this.body = body.getParserForType().parseFrom(array, offset, length);
    }


}
