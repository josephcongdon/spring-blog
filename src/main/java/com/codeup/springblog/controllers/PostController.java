package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

      //injecting a post dependency

    private final PostRepository postsDao;
    private UserRepository usersDao;
    private final EmailService emailService;
    public PostController(PostRepository postsDao, UserRepository usersDao, EmailService emailService ) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;

    }

    @GetMapping("/posts")
    public String index(Model viewModel) {
        List<Post> posts = postsDao.findAll();
        viewModel.addAttribute("posts", posts);
        return "posts/index";
    }

//    @GetMapping("/posts/{id}") //INDIVIDUAL POSTS CAPITAL M MODEL is a spring view
//    public long showId(@PathVariable long id, Model model){
//        model.addAttribute("id", id);
//        return id;
//    }

    @GetMapping("/posts/create") //CREATE NEW POSTS
    public String create(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String insert(@ModelAttribute Post post) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getById(principal.getId());
        post.setUser(author);
        postsDao.save(post);
        emailService.prepareAndSend(post, "You submitted: " + post.getTitle(), post.getBody());
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
   public String returnEditView(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("posts", postsDao.getById(id));
        return "/posts/edit";
    }

    @GetMapping("/index")
    public String showIndex(Model model){
        String query= "SELECT * FROM posts";
        List<Post> posts = postsDao.findAll();
        model.addAttribute(posts);
        return "posts/index";
    }
        @GetMapping("/posts/{id}")
        @ResponseBody
        public String show(@PathVariable long id) {
            return "Here is the post " + id;
        }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        //use the new form to update the existing post in the DB
        //pull the existing post object from the DB
        Post post = postsDao.getById(id);
        //set the title and body to the request param values
        post.setTitle(title);
        post.setBody(body);
        //persist the change in the database with the postDao
        postsDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

}
