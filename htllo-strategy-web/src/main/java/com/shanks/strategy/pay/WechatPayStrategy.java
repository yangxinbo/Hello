package com.shanks.strategy.pay;

import org.springframework.stereotype.Service;

/**
 * FileName    : com.shanks.strategy
 * Description :
 *
 * @author : 毒液
 * @version : 1.0
 * Create Date : 2021/3/17 22:16
 * @Copyright : WithUFuture Software Co.,Ltd.Rights Reserved
 * @Company : 深圳幻影未来信息科技有限公司
 **/
@Service
public class WechatPayStrategy implements PayStrategy {

    @Override
    public String pay() {
        return "WechatPay";
    }

}
