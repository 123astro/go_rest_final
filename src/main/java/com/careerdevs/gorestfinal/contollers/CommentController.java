package com.careerdevs.gorestfinal.contollers;


import com.careerdevs.gorestfinal.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {




    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/test")
    public String testRoute() {
        return "Testing";
    }

}
