package com.careerdevs.gorestfinal.contollers;

import com.careerdevs.gorestfinal.models.Comment;
import com.careerdevs.gorestfinal.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


           /*

      Required Routes for GoRestSQL Final: complete for each resource; User, Post, Comment, Todo,

           * GET route that returns one [resource] by ID from the SQL database
           * GET route that returns all [resource]s stored in the SQL database
           * DELETE route that deletes one [resource] by ID from SQL database (returns the deleted SQL [resource] data)
           * DELETE route that deletes all [resource]s from SQL database (returns how many [resource]s were deleted)
           * POST route that queries one [resource] by ID from GoREST and saves their data to your local database (returns
           the SQL [resource] data)
           *POST route that uploads all [resource]s from the GoREST API into the SQL database (returns how many
           [resource]s were uploaded)
           *POST route that create a [resource] on JUST the SQL database (returns the newly created SQL [resource] data)
           *PUT route that updates a [resource] on JUST the SQL database (returns the updated SQL [resource] data)
    * */




    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/test")
    public String testRoute() {
        return "Testing Comments";
    }

}
