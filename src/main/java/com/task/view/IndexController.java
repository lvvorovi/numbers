package com.task.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    public String indexView() {
        return "index.html";
    }

}
