package com.shanks.common;

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

}
