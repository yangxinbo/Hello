package com.shanks.strategy;

import com.shanks.strategy.pay.PayStrategContextService;
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
            String payType = "aliPay1";
            String result = payStrategContextService.getStrategy(payType).pay();
            log.info("[pay]: payType:{} ,result:{}", payType, result);
        }
        {
            String payType = "wechatPay";
            String result = payStrategContextService.getStrategy(payType).pay();
            log.info("[pay]: payType:{} ,result:{}", payType, result);
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
