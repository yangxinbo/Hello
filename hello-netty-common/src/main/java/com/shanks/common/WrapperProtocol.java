package com.shanks.common;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.withufuture.game.proto.Test;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import lombok.Data;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class WrapperProtocol {

    private WrapperHeader header;

    private MessageLite body;

    public WrapperProtocol() {
    }

    public WrapperProtocol(WrapperHeader header, MessageLite body) {
        this.header = header;
        this.body = body;
    }

    /**
     * 编码
     *
     * @param buf 缓冲区
     */
    public void encode(ByteBuf buf) {
        header.encode(buf);
        buf.writeBytes(body.toByteArray());
    }

    /**
     * 解码
     *
     * @param buf
     * @throws InvalidProtocolBufferException
     */
    public void decode(ByteBuf buf) throws InvalidProtocolBufferException {
        WrapperHeader header = new WrapperHeader();
        header.decode(buf);
        MessageLite body = Test.getDefaultInstance();

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
        this.header = header;
        this.body = body.getParserForType().parseFrom(array, offset, length);
    }


}
