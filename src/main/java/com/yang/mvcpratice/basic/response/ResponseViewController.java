package com.yang.mvcpratice.basic.response;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello");
        mav.addObject("data", "hello!");

        return mav;
    }
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hihi");
        return "response/hello";
    }
    //명시성이 떨어져 권장하지 않음
//    @RequestMapping("/response-view-v3")
//    public void responseViewV3(Model model) {
//        model.addAttribute("data", "helload");
//
//    }
}
