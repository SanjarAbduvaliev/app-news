package com.example.appnews.serivice;

import com.example.appnews.entity.Post;
import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.PostDto;
import com.example.appnews.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    public ApiResponse addPost(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Yangi post joylandi",true);
    }

    public ApiResponse editPost(PostDto postDto,Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()){
            return new ApiResponse("Bunday post mavjud emas",false);
        }
        Post post = optionalPost.get();
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setUrl(postDto.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post tahrirlandi",true);
    }

    public Post getId(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(()->null);
    }
    public ApiResponse deletePost(Long id){
        postRepository.deleteById(id);
        return new ApiResponse("Post o'chirildi",true);
    }
    public List<Post> getAll(){
        return postRepository.findAll();
    }
}
