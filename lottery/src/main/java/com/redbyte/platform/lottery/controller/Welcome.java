package com.redbyte.platform.lottery.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwq
 */
@RestController
public class Welcome {

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
