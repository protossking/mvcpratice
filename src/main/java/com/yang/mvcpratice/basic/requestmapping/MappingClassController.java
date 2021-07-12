package com.yang.mvcpratice.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

/*
  회원목록 조회 :GET /users
  회원등록 Post /users
  회원조회 GET /users/{userId}
  회원수정:PATCH /users/{userId}
  회원삭제: DELTE /users/{userId}
 */
@RestController
@RequestMapping("/users")
public class MappingClassController {

    @GetMapping
    public String users() {
        return "get users";
    }

    @PostMapping
    public String addUser() {
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userid" + userId;
    }
    /*
        PATCH /mapping/users/{userId}

     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId =" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId = " + userId;
    }



}
