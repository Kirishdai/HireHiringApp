package org.krish.hirehiringapp.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.krish.hirehiringapp.model.Post;
import org.krish.hirehiringapp.repository.PostRepository;
import org.krish.hirehiringapp.repository.SearchRepository;
import org.krish.hirehiringapp.repository.SearchRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepositoryImplementation searchRepositoryImplementation;

    @Hidden
    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
    @GetMapping("/posts")
    public List<Post> getAllPosts(){

        return postRepository.findAll();
    }
    @GetMapping("/posts/{text}")
    public List<Post> searchByText(@PathVariable String text){
        return searchRepositoryImplementation.findByText(text);
    }
    @PostMapping("/add")
    public Post addPost(@RequestBody Post post){
        return postRepository.save(post);
    }
    @DeleteMapping("/remove/{id}")
    public void removePost(@PathVariable Integer id){
        postRepository.deleteById(id);
    }
}
