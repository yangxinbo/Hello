package com.shanks.strategy.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FileName    : com.shanks.strategy.pay
 * Description :
 *
 * @author : shanks
 * @version : 1.0
 * Create Date : 2021/3/22 9:48
 * @Copyright : shanks
 * @Company : shanks
 **/
@Slf4j
@Component
public class PayStrategContextServiceImpl implements PayStrategContextService {

    private Map<String, PayStrategy> strategyMap = new ConcurrentHashMap<>();

    @Autowired
    public void initStrategy(Map<String, PayStrategy> payStrategyMap) {
        for (Map.Entry<String, PayStrategy> strategyEntry : payStrategyMap.entrySet()) {
            for (Map.Entry<String, String> strategyKeyEntry : strategyEntry.getValue().strategyMap().entrySet()) {
                this.strategyMap.put(strategyKeyEntry.getKey(), strategyEntry.getValue());
            }
        }
        log.info("[initStrategy] strategy:{}", strategyMap);
    }


    @Override
    public PayStrategy getStrategy(String strategyKey) {
        return strategyMap.get(strategyKey);
    }
}
