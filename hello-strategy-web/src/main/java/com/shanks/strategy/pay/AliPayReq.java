package com.shanks.strategy.pay;

import lombok.Data;

/**
 * FileName    : com.shanks.strategy.pay
 * Description :
 *
 * @author : Venom
 * @version : 1.0
 * Create Date : 2021/4/22 14:02
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Data
public class AliPayReq extends BasePayReq implements PayReq {

    private String test;

    @Override
    public String payCode() {
        return "aliPay";
    }
}
