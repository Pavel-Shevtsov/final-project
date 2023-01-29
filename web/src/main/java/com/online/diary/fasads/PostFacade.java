package com.online.diary.fasads;

import com.online.diary.forms.PostForm;
import com.online.diary.model.Post;
import com.online.diary.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        post.setLastUpdateDate(postForm.getLastUpdateDate());
        post.setIsApprovedForPublication(postForm.getIsApprovedForPublication());
        post.setUser(postForm.getUser());
        return post;
    }

    public void add(PostForm postForm){

        if (postForm.getIsPrivate()==null){
            postForm.setIsPrivate(true);
        }
        if (!postForm.getIsPrivate()){
            postForm.setIsApprovedForPublication(null);
        }else {
            postForm.setIsApprovedForPublication(true);
        }
       postService.add(buildPost(postForm));
    }

    public PostForm getById(long id){
        return new PostForm(postService.getById(id));
    }

    public List<Post> getNotApproved(){
        return postService.getPostApprovedOrNo(false,null);
    }


    public List <Post> allApproved(){
        return postService.getPostApprovedOrNo(false, true);
    }

    public void update(PostForm postForm){
        if (postForm.getNewTag()!=null||postForm.getNewText()!=null) {
            if (!postForm.getNewTag().equals(postForm.getTag()) && !postForm.getNewTag().equals("")) {
                postForm.setTag(postForm.getNewTag());
            }
            if (!postForm.getNewText().equals(postForm.getText()) && !postForm.getNewText().equals("")) {
                postForm.setText(postForm.getNewText());
            }
            if (postForm.getIsPrivate()==null){
                postForm.setIsPrivate(true);
            }
            if (!postForm.getIsPrivate()){
                postForm.setIsApprovedForPublication(null);
            }else {
                postForm.setIsApprovedForPublication(true);
            }
            postForm.setLastUpdateDate(LocalDate.now());
        }
        postService.modify(buildPost(postForm));
    }
    public List<Post> getAllPublicPosts(){
        return postService.getPostApprovedOrNo(false,true);
    }

    public void delete(long id){
        postService.delete(id);
    }
   }
