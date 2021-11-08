package com.codeup.springblog.models;


import javax.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage {

    @Id
    @GeneratedValue
    private long id;

    private String urls;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostImage() {
    }

    public PostImage( String url) {
        this.urls = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return urls;
    }

    public void setUrl(String urls) {
        this.urls = urls;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
