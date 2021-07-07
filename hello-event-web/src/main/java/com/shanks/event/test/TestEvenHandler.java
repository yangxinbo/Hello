package com.shanks.event.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * FileName    : com.shanks.event.test
 * Description :
 *
 * @author : shanks
 * @version : 1.0
 * Create Date : 2021/7/7 11:29
 **/
@Slf4j
@Component
public class TestEvenHandler {

    @Autowired
    private EventDispatcher dispatcher;

    @PostConstruct
    public void init() {
        dispatcher.bind(EventType.event1, this::handleProcess1);
        dispatcher.bind(EventType.event2, this::handleProcess2);
        dispatcher.bind(EventType.event3, this::handleProcess3);
    }

    /**
     * 事件1的处理逻辑
     *
     * @param eventReq
     */
    private void handleProcess1(EventReq eventReq) {
        log.info("事件1的处理逻辑");
    }

    /**
     * 事件2的处理逻辑
     *
     * @param eventReq
     */
    private void handleProcess2(EventReq eventReq) {
        log.info("事件2的处理逻辑");
    }

    /**
     * @param eventReq
     */
    private void handleProcess3(EventReq eventReq) {
        log.info("事件3的处理逻辑");
    }

}
