package com.weather.rest;

import com.weather.model.request.PostRequest;
import com.weather.model.request.ReplyRequest;
import com.weather.model.response.PostResponse;
import com.weather.model.response.ReplyResponse;
import com.weather.service.PostService;
import com.weather.utils.RequestValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Suresh Chaudhari
 */
@RestController
@RequestMapping(value = "/api/v1")
public class PostRestController {

    @Autowired
    private PostService postService;


    /**
     * It create post
     */
    @PostMapping(value = "/post",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostResponse createPost(@RequestBody PostRequest postRequest) {

        RequestValidatorUtil.validatePostRequest(postRequest);
        return postService.addPostData(postRequest);
    }

    /**
     * To get all posts from database.
     *
     * Note: First time it will hit the database to fetch the posts and when you make request again it will fetch the data from
     * redis cache
     *
     */
    @Cacheable(value = "getAllPosts")
    @GetMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }


    /**
     * It save reply of Posts
     */
    @PostMapping(value = "/post/{postId}/reply",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReplyResponse replyPostById(@PathVariable Integer postId, @RequestBody ReplyRequest replyRequest) {

        RequestValidatorUtil.validatePostReplyRequest(replyRequest);
        return postService.addPostReplyData(postId, replyRequest);
    }

    /**
     * To get all posts from database.
     *
     * Note: First time it will hit the database to fetch the replied post by post id and when you make request again it will fetch the data from
     * redis cache
     *
     */
    @Cacheable(value = "getReplyByPostId")
    @GetMapping(value = "/post/{postId}/reply", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReplyResponse> getReplyByPostId(@PathVariable int postId) {
        return postService.getAllReplyByPostId(postId);
    }

    /**
     * To get all posts from database.
     *
     * Note: First time it will hit the database to fetch the replied post by post id and when you make request again it will fetch the data from
     * redis cache
     *
     */
    @DeleteMapping(value = "/post/{postId}/reply/{replyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getReplyByPostId(@PathVariable int postId, @PathVariable int replyId) {
        postService.deleteReplyPostById(postId, replyId);
    }
}
