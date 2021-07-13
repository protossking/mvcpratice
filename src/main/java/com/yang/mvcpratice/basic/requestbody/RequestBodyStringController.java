package com.yang.mvcpratice.basic.requestbody;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messagebody={}", messageBody);
        response.getWriter().write("ok");

    }
    /*
        InputStream(Reader) Http 요청 메시지 바디의 내용을 직접조회
        OutputStream(Writer) Http 응답 메시지의 바디에 직접 결과 조회
     */

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messagebody={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/reqeust-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");

    }
    /*
        HttpEntity: Http header, body 정보를 편리하게 조회
        - 메시지 바디 정보를 직접 조회
        - 요청 파라미터를 조회하는 기능과 관계없음 @ReqeustParam, @ModelAttribute

        HttpEntity는 응답에도 사용가능
        - 메시지 바디 정보 직접 반환
        - 헤더 정보 포함 기능
        - view 조회x


        스프링MVC 내부에서 Http 메시지 바디를 읽어서 문자나 객체로 변환해서 전달해주는데, 이때 HTTP메시지
        컨버터라는 기능을 사용한다.
     */

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messagebody={}", messageBody);
        return "ok";
    }

}
