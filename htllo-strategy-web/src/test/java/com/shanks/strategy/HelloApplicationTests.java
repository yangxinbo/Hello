package com.shanks.strategy;

import com.shanks.strategy.pay.PayStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;


@SpringBootTest
class HelloApplicationTests {


    @Autowired
    public void initStrategy(Map<String, PayStrategy> payStrategyMap) {

    }

    @Test
    void contextLoads() {

    }

}
