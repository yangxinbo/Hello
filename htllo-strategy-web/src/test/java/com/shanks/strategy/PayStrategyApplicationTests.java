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
            String payType = "aliPay";
            String result = payStrategContextService.getStrategy(payType).pay();
            log.info("[pay]: payType:{} ,result:{}", payType, result);
        }
        {
            String payType = "wechatPay";
            String result = payStrategContextService.getStrategy(payType).pay();
            log.info("[pay]: payType:{} ,result:{}", payType, result);
        }
    }

}
