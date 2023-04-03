package com.cnu.real_coding_server.controller;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("post")
    public ResponseEntity<?> save(
            @RequestBody PostRequest postRequest
    ) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("post")
    public ResponseEntity<?> findAllPost() {
        List<Post> postList = postService.findAll();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("post/{postId}")
    public ResponseEntity<?> findPostById(@PathVariable Integer postId) {
        Post post = postService.findById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("post/{postId}")
    public ResponseEntity<?> editPostPyId(
            @PathVariable Integer postId,
            @RequestBody PostRequest postRequest) {
        postService.edit(postId, postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<?> editPostPyId(
            @PathVariable Integer postId) {
        postService.delete(postId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
