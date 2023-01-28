package com.online.diary.fasads;

import com.online.diary.forms.PostForm;
import com.online.diary.model.Post;
import com.online.diary.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostFacade {

    @Autowired
    PostService postService;

    public Post buildPost(PostForm postForm){
        Post post = new Post();
        post.setId(postForm.getId());
        post.setTag(postForm.getTag());
        post.setText(postForm.getText());
        post.setIsPrivate(postForm.getIsPrivate());
        post.setPublicationDate(postForm.getPublicationDate());
        post.setIsApprovedForPublication(postForm.getIsApprovedForPublication());
        post.setUser(postForm.getUser());
        return post;
    }

    public void add(PostForm postForm){
       postService.add(buildPost(postForm));
    }

    public PostForm getById(long id){
        return new PostForm(postService.getById(id));
    }

    public List<Post> getNotApproved(Boolean isPrivate, Boolean isApprovedForPublication ){
        return postService.getNotApprovedMoments(isPrivate,isApprovedForPublication);
    }

    public void update(PostForm postForm){
        if (postForm.getNewText()!=null){
            postForm.setText(postForm.getNewText());
            if (!postForm.getIsPrivate()){
                postForm.setIsApprovedForPublication(null);
            }
        }
        postService.modify(buildPost(postForm));
    }

    public void delete(long id){
        postService.delete(id);
    }
}
