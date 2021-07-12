package com.yang.mvcpratice.basic.request;

import com.yang.mvcpratice.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    /*
        @RequestParam :파라 미터 이름으로 바인딩
        @ResponseBody: view 조회를 무시하고, Http message body에 직접 해당내용 입력
        @RequestParam("username")String username ---> request.getParameter("username");
     */

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }
    //ModelAttribute가 사용되기전
    @ResponseBody
    @RequestMapping("/model-attribute")
    public String modelAttribute(@RequestParam("username") String username, @RequestParam("age") int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /*
        스프링MVC에서는 @ModelAttribute가 있으면 다음을 실행한다.
        HelloData 객체를 생성한다.
        요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다
        해당 프로퍼티의 세터를 호출해서 파라미터의 값을 바인딩한다
        예) 파라미터이름이 username 이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.

     */

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /*
           너무 많은 생략으로 혼란발생!
           String, int , Integer 같은 단순타입  = @RequestParam
           나머지 @ModelAttribute
     */


}
