package com.dave.employee_service.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {

    @GetMapping(
            value = { "/", "/{path:[^\\.]*}" },
            produces = MediaType.TEXT_HTML_VALUE
    )
    public String forward() {
        return "forward:/index.html";
    }
}
