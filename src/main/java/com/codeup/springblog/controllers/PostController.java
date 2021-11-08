package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

      //injecting a post dependency

    private PostRepository postsDao;
    private UserRepository usersDao;

    public PostController(PostRepository postsDao, UserRepository usersDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
    }

//    public PostController(PostRepository postsDao){
//        this.postsDao = postsDao;
//    }
//
    @GetMapping("/posts") //VIEW ALL POSTS
    public String postIndex(Model model){
        List<Post> posts = postsDao.findAll();

        model.addAttribute("posts", postsDao.findAll());

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
    public String insert(@RequestParam String title, @RequestParam String body, @RequestParam List<String> urls) {
        List<PostImage> images = new ArrayList<>();
        User author = usersDao.getById(1L);
        Post post = new Post(title, body);

        // create list of post image objects to pass to the new post constructor
        for (String url : urls) {
            PostImage postImage = new PostImage(url);
            postImage.setPost(post);
            images.add(postImage);
        }

        post.setImages(images);

        post.setUser(author);

        // save a post object with images

        postsDao.save(post);

        // modify the post index view to display post images
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/index")
    public String index(Model model){
        String query= "SELECT * FROM posts";
        // seed posts in the DB
        List<Post> posts = postsDao.findAll();
        //fetch posts with postsDAO
        //create a posts index view
        //sedn list of objects to index view
        model.addAttribute(posts);
        return "posts/index";
    }
    @GetMapping("/show")
    public String show(){
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
   public String returnEditView(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("posts", postsDao.getById(id));
        return "posts/edit";
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

//    @PostMapping("/posts/{id}/delete")
//    public String deletePost(@PathVariable )


        //add an endpoint to send the user an edit posts form/ view
        // create an edit post form
        // create another endpoint to handle the post request of editing a post
}
