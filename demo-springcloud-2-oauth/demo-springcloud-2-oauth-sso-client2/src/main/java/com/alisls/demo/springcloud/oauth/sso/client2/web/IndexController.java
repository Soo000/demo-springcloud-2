package com.alisls.demo.springcloud.oauth.sso.client2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController
 *
 * @author Ke Wang
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index 2";
    }

    @GetMapping("/memeber")
    public String memeber() {
        return "memeber 2";
    }

}
