package com.shanks.event.test;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FileName    : com.shanks.event.test
 * Description :
 *
 * @author : shanks
 * @version : 1.0
 * Create Date : 2021/7/7 11:29
 **/
@Component
public class EventDispatcher {

    private Map<EventType, EventHandler> handlers = new ConcurrentHashMap<>();


    /**
     * 将XXX事件注册到派发器
     *
     * @param eventType
     * @param eventHandler
     */
    public void bind(EventType eventType, EventHandler eventHandler) {
        this.handlers.put(eventType, ((eventReq) -> {
            eventHandler.handle(eventReq);
        }));
    }

    /**
     * 进行事件派发
     *
     * @param eventType
     */
    public void dispatch(EventType eventType, EventReq eventReq) {
        handlers.get(eventType).handle(eventReq);
    }
}
