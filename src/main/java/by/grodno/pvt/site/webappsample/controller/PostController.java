package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.domain.Post;
import by.grodno.pvt.site.webappsample.dto.PostDTO;
import by.grodno.pvt.site.webappsample.repo.PostRepo;
import by.grodno.pvt.site.webappsample.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {



    @Autowired
    private PostService postService;
    @Autowired
    private ConversionService convertionService;


    @GetMapping("/post")
    public String getpost(Model model){
        List<PostDTO> posts = postService.getPosts().stream().map(u -> convertionService.convert(u, PostDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("post", posts);

        return "posts";

    }


    @GetMapping("/post/add")
    public String postAdd(Model model){
        return "post-add";
    }

    @PostMapping("/post/add")
    public String articlePostAdd(@RequestParam String title, @RequestParam String full_text, Model model){
        Post post = new Post (title, full_text);
        postService.savePost(post);
        return "redirect:/post";
    }
}
