package com.example.spring_tutorial02.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewDemoController {
    @GetMapping("/view")
    public String viewDemo() {
        return "tutorialView";
    }
}
