package com.kaka.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@Slf4j
public class CommonController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @ResponseBody
    @RequestMapping("/test")
    public void test() {

        try {
            userService.add();
        } catch (IOException e) {
            log.error("error", e);
        }

    }
}
