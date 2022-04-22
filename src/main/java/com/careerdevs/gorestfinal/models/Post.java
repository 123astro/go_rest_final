package com.careerdevs.gorestfinal.models;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity

public class Post {

//    @Id
//    @Column(name = "id")
//    @Type(type = "uuid-char")
//    private UUID uuid = UUID.randomUUID();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long user_id;

    private String title;

    @Column(length = 512)  //update
    private String body;

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
