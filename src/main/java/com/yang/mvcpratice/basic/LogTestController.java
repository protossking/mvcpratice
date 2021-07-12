package com.yang.mvcpratice.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String title = "MVC tutorial";
        log.trace("trace log = {}", title);
        log.debug("debug log = {}", title);
        log.info("info log = {}", title);
        log.warn("warn log = {}", title);
        log.error("error log= {}", title);

        // 다음과 같은 방식은 권장하지 않음
        log.debug("debug = " + title);
        //
        /*
            개발 서버는 debug 출력
            운영 서버는 Info 출력
         */
        return "ok";
    }
}
