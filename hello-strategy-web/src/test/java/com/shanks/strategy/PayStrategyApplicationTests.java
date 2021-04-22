package com.shanks.strategy;

import com.shanks.strategy.pay.AliPayReq;
import com.shanks.strategy.pay.PayStrategContextService;
import com.shanks.strategy.pay.WechatPayReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class PayStrategyApplicationTests {

    @Autowired
    private PayStrategContextService payStrategContextService;

    @Test
    void contextLoads() {
        {
            AliPayReq payReq = new AliPayReq();
            payReq.setTitle("title");
            payReq.setTest("aaaa");
            String result = payStrategContextService.pay(payReq);
            log.info("[pay]: payType:{} ,result:{}", result);
        }
        {
            WechatPayReq payReq = new WechatPayReq();
            payReq.setTitle("title");
            payReq.setTest2("aaaa2");
            String result = payStrategContextService.pay(payReq);
            log.info("[pay]: payType:{} ,result:{}", result);
        }
    }


    @Test
    void contextLoads1() {
        String payType = "aliPay";
        if ("aliPay".equals(payType)) {
            // TODO 调用支付宝
        } else if ("wechatPay".equals(payType)) {
            // TODO 调用微信
        }
        // TODO 调用其他支付平台
    }

}
