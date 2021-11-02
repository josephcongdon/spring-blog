package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {

    @GetMapping("/hello")
    @ResponseBody
    public String returnHello() {
        return "Hello";
    }

    @GetMapping("/hi")
    @ResponseBody
    public String returnHi() {
        return "<h1>Hi</h1>";
    }

    @GetMapping("/bg/{color1}/font/{color2}")
    @ResponseBody
    public String returnBgFontColor(@PathVariable String color1, @PathVariable String color2) {
        return String.format("<h1 style='background-color:%s;color:%s'>THIS IS THE FONT</h1>", color1, color2);
    }


    @GetMapping("/name")
    @ResponseBody
    public String returnName(@RequestParam (defaultValue = "please enter name in URL") String n){
        return "Returning name: "+ n;
    }
}
