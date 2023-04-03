package com.cnu.real_coding_server.service;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(PostRequest postRequest) {
         postRepository.save(postRequest.toEntity());
    }

    public Post findById(Integer postId) {
        return getPost(postId);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void edit(Integer postId, PostRequest postRequest) {
        Post post = getPost(postId);

        post.setTitle(postRequest.getTitle());
        post.setContents(postRequest.getContents());
        post.setTag(postRequest.getTag());
    }

    public void delete(Integer postId) {
        Post post = getPost(postId);

        postRepository.delete(post);
    }

    private Post getPost(Integer postId) {
        return postRepository.findById(postId).stream().findAny()
                .orElseThrow(() -> new RuntimeException("포스트가 존재하지 않습니다."));
    }

}
