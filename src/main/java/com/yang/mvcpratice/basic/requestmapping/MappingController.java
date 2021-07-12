package com.yang.mvcpratice.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

//    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("hello Basic");
        return "OK";
    }
    @RequestMapping({"/hello-basic2", "/hello-basic2"})
    public String multiBasic() {
        log.info("multi basic");
        return "ok";
    }

    /*
        @RestController 를 사용하는 이유
        @Controller 을 하였을시 반환값이 String 이면 뷰 이름으로 인식된다
        그래서 위에처럼 return "Ok" 면 OK.html 뷰를 찾는다.
        @RestController 는 뷰를 찾는것이 아니라 HTTP 메시지 바디에 바로입력한다.

        따라서 실행결과로 ok 메시지를 받을수 있다.

        @RequestMapping("/hello-basic")
        /hello-basic url 호출이 오면 이 메서드가 실행될수 있도록함
        @RequestMapping({"/hello-basic", "/hello-go"}     */

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    //@RequestMapping 을해서 method= 를 설정해주는것보다 처음부터 직관적으로
    //@GetMapping 하자
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return  "ok";
    }

    /*
        PathVariable(경로 변수) 사용
        최근 HttpApi는 다음과 같이 리소스 경로에 식별자를 넣는 스타일을 선호
        /mapping/userA
        /users/1

        @RequestMapping은 url 경로를 템플릿화 할수 있는데 @PathVariable을
        사용하면 매칭되는 부분을 편리하게 조회 할수 있다.
        @PathVariable의 이름과 파라미터 이름이 같으면 생략할수 있다.



     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

//    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
//    public String mappingPath2(@PathVariable("userId") String userId, @PathVariable("orderId") Long orderId) {
//        log.info("mappingPath userId={}, orderId={}", userId, orderId);
//        return "ok";
//    }


    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mapping userId={}, orderId={}", userId, orderId);
        return "ok";
        //결과 localhost:8080/mapping/users/userA/orders/23
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mapping Param");
        return "ok";

        //결과 localhost:8080/mapping-param?mode=debug
    }
    /*
        특정 헤더로 추가 매핑
        headers = "mode",
        headers ="!mode",
        headers="mode=debug"
        headers="mode!=debug"

     */
    @GetMapping(value = "/mapping-header", headers= "mode=debug")
    public String mappingHeader() {
        log.info("mappingheader");
        return "ok";
    }
    /*
        Content-type 헤더 기반 추가 매핑
        consumes="application.json"
        consumes="!application/json"

        http요청의 Content-Type 헤더를 기반으로 미디어 타입으로 매핑한다.
        만약 맞지 않으면 Http 415 상태코드(Unsupported Media Type)을 반환

     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappiongConsumes");
        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProdeuces() {
        log.info("mappingproduces");
        return "ok";
    }

}
