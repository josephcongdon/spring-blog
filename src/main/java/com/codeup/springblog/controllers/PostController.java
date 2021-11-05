package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

      //injecting a post dependency

    private PostRepository postsDao;

    public PostController(PostRepository postsDao){
        this.postsDao = postsDao;
    }

    @GetMapping("/posts") //VIEW ALL POSTS
    public String postIndex(Model model){
         Post post1 = new Post("title 1", "body 1", 1);
         Post post2 = new Post("title 2", "body 2", 2);

        List<Post> post = new ArrayList<>();
        post.add(post1);
        post.add(post2);

        model.addAttribute("posts", post);

        return "posts/index" ;
    }

    @GetMapping("/posts/{id}") //INDIVIDUAL POSTS CAPITAL M MODEL is a spring view
    public long showId(@PathVariable long id, Model model){
        model.addAttribute("id", id);
        return id;
    }

    @GetMapping("/posts/create") //CREATE NEW POSTS
    @ResponseBody
    public String create(){
        return "Here is the post create form...";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String insert() {
        return "new post here";
    }

    @GetMapping("/index")
    public String index(){
        // seed posts in the DB
        //fetch posts with postsDAO
        //create a posts index view
        //sedn list of objects to index view
        return "posts/index";
    }
    @GetMapping("/show")
    public String show(){
        return "posts/show";
    }

        //add an endpoint to send the user an edit posts form/ view
        // create an edit post form
        // create another endpoint to handle the post request of editing a post
}
