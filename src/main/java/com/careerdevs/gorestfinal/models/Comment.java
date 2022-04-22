package com.careerdevs.gorestfinal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

//    {
//        id: 1601,
//                post_id: 1612,
//            name: "Lakshmi Pilla",
//            email: "lakshmi_pilla@dietrich.biz",
//            body: "Quae eaque occaecati. Voluptas dolor libero."
//    },

public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int post_id;
    private String name;
    private String email;
    private String body;

    public long getId() {
        return id;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post_id=" + post_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
