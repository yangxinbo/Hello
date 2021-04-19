package com.shanks.common;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * FileName    : com.shanks.server
 * Description :
 *
 * @author : Venom
 * @version : 1.0
 * Create Date : 2021/4/15 19:50
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Data
@Accessors(chain = true)
public class WrapperHeader {

    private byte requestId;

    private byte version;

    private byte serializer;

    private byte command;

    private int length;


    /**
     * 解码
     *
     * @param buf 缓冲区
     */
    public void decode(ByteBuf buf) {
        this.requestId = buf.readByte();
        this.version = buf.readByte();
        this.serializer = buf.readByte();
        this.command = buf.readByte();
        this.length = buf.readInt();
    }

    /**
     * 编码
     *
     * @param buf 缓冲区
     */
    public void encode(ByteBuf buf) {
        buf.writeByte(this.requestId);
        buf.writeByte(this.version);
        buf.writeByte(this.serializer);
        buf.writeByte(this.command);
        buf.writeInt(this.length);
    }

}
