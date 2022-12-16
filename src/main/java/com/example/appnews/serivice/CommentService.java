package com.example.appnews.serivice;

import com.example.appnews.entity.Comment;
import com.example.appnews.entity.Post;
import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.CommentDto;
import com.example.appnews.repository.CommentRepository;
import com.example.appnews.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public ApiResponse addComment(CommentDto commentDto){
        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());
        if (optionalPost.isEmpty())
            return new ApiResponse("Post mavjud emas",false);
        Comment comment=new Comment(commentDto.getText(), optionalPost.get());
        return new ApiResponse("Komment  qo'shildi",true);
    }
    public ApiResponse editMyComment(CommentDto commentDto,Long id){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()) {
            return new ApiResponse("Comment  mavjud emas",false);
        }
        Comment comment = optionalComment.get();
        comment.setTitle(commentDto.getText());
        commentRepository.save(comment);
        return new ApiResponse("Tahrirlandi",true);
    }
    public ApiResponse deleteComment(Long id){
        commentRepository.deleteById(id);
        return new ApiResponse("Komment o'chirildi",true);
    }
    public ApiResponse deleteSelectAll(List<Long> all){
        commentRepository.deleteAllById(all);
        return new ApiResponse("Tanlangan barcha kommentariya o'chirildi",true);
    }
    public List<Comment> getAll(){
        return commentRepository.findAll();
    }
}
