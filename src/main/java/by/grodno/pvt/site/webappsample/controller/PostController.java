package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.domain.Post;
import by.grodno.pvt.site.webappsample.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostRepo postRepo;



    @GetMapping("/post")
    public String post(Model model){

        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "post";
    }

    @GetMapping("/post/add")
    public String postAdd(Model model){


        return "post-add";
    }
}
