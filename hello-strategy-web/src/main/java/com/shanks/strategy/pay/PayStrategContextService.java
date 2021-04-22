package com.shanks.strategy.pay;

/**
 * FileName    : com.shanks.strategy.pay
 * Description :
 *
 * @author : shanks
 * @version : 1.0
 * Create Date : 2021/3/22 9:47
 * @Copyright : shanks
 * @Company : shanks
 **/
public interface PayStrategContextService {

    PayStrategy getStrategy(String strategyKey);

    String pay(PayReq strategyKey);
}
