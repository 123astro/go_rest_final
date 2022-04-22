package com.careerdevs.gorestfinal.contollers;

import com.careerdevs.gorestfinal.models.Post;
import com.careerdevs.gorestfinal.models.User;
import com.careerdevs.gorestfinal.repositories.PostRepository;
import com.careerdevs.gorestfinal.repositories.UserRepository;
import com.careerdevs.gorestfinal.utils.ApiErrorHandling;
import com.careerdevs.gorestfinal.validation.PostValidation;
import com.careerdevs.gorestfinal.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


@RestController
@RequestMapping("/api/posts")

public class PostController {

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
    PostRepository postRepository;


    @GetMapping("/test")
    public String testRoute() {
        return "Testing Post";
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPost() {
        try {

            Iterable<Post> allPosts = postRepository.findAll();

            return new ResponseEntity<>(allPosts, HttpStatus.OK);

        } catch (Exception e) {
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createPost(@RequestBody Post newPost) {
        try {

            ValidationError errors = PostValidation.validatePost(newPost, postRepository, false);

            if(errors.hasError()) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, errors.toJSONString());
            }

            Post createdPost = postRepository.save(newPost);

            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

        } catch (HttpClientErrorException e) {
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode());
        }catch (Exception e) {
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @PostMapping("/uploadall")
    public ResponseEntity<?> uploadAll(
            RestTemplate restTemplate
    ) {
        try {
            String url = "https://gorest.co.in/public/v2/users";

            ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);

            User[] firstPageUsers = response.getBody();

            // assert firstPageUsers != null;

            if (firstPageUsers == null) {
                throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to GET first page of " +
                        "users from GOREST");
            }

            ArrayList<User> allUsers = new ArrayList<>(Arrays.asList(firstPageUsers));

            HttpHeaders responseHeaders = response.getHeaders();

            String totalPages = Objects.requireNonNull(responseHeaders.get("X-Pagination-Pages")).get(0);
            int totalPgNum = Integer.parseInt(totalPages);

            for (int i = 2; i <= totalPgNum; i++) {
                String pageUrl = url + "?page=" + i;
                User[] pageUsers = restTemplate.getForObject(pageUrl, User[].class);

                if (pageUsers == null) {
                    throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                            "Failed to GET first page " + i + " of users from GoRest ");
                }
                allUsers.addAll(Arrays.asList(firstPageUsers));
            }

//            postRepository.saveAll(allUsers, );

            return new ResponseEntity<>("Users Created " + allUsers.size(), HttpStatus.OK);

        } catch (HttpClientErrorException e) {
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            return ApiErrorHandling.genericApiError(e);
        }
    }
}
