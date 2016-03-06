package io.github.acdcjunior.boot.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloRepository helloRepository;

    @RequestMapping("/hello")
    @ResponseBody
    public Iterable<Hello> hellos() {
        return helloRepository.findAll();
    }

}