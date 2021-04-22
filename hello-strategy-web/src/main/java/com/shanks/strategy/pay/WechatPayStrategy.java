package com.shanks.strategy.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName    : com.shanks.strategy
 * Description :
 *
 * @author : shanks
 * @version : 1.0
 * Create Date : 2021/3/17 22:16
 * @Copyright : dd
 * @Company : dd
 **/
@Slf4j
@Service
public class WechatPayStrategy implements PayStrategy {

    @Override
    public Map<String, String> strategyMap() {
        return new HashMap<String, String>() {{
            put("wechatPay", "1");
        }};
    }

    @Override
    public String pay(PayReq payReq) {
        WechatPayReq wechatPayReq = (WechatPayReq) payReq;
        log.info("req:{}", wechatPayReq);
        return "调用WechatPay";
    }

}
