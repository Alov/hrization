package me.alov.hrization.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple/")
public class SimpleEndpoint {

    @GetMapping("/parse")
    public void getImageFromCV() {

    }
}
