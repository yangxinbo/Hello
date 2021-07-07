package com.shanks.event.example;

import com.shanks.event.test.EventDispatcher;
import com.shanks.event.test.EventReq;
import com.shanks.event.test.EventType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class HelloEventApplicationTests {

    @Autowired
    private EventDispatcher eventDispatcher;

    @Test
    void test1() {
        EventReq req = new EventReq();
        eventDispatcher.dispatch(EventType.event1, req);
        eventDispatcher.dispatch(EventType.event2, req);
        eventDispatcher.dispatch(EventType.event3, req);
    }

}
