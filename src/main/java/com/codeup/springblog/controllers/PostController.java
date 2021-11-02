package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/post")
    @ResponseBody
    public String postIndex(){
        return "This is the post index page: ";
    }

    @GetMapping("/post/{id}")
    @ResponseBody
    public String showId(@PathVariable long id){
        return "This is the post index page: id " + id;
    }

    @GetMapping("/post/create")
    @ResponseBody
    public String create(){
        return "Here is the post create form...";
    }

    @PostMapping("/post/create")
    @ResponseBody
    public String insert() {
        return "new post here";
    }


}
