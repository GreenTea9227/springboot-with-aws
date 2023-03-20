package com.jojoldu.book.springbootwebservice.web;

import com.jojoldu.book.springbootwebservice.web.dto.HelloResponseDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "yohan");
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(String name, int amount) {

        return new HelloResponseDto(name, amount);
    }
}
