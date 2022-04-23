package com.careerdevs.gorestfinal.models;


import javax.persistence.*;

@Entity


//{
//        id: 3194,
//        name: "Aarya Mukhopadhyay",
//        email: "mukhopadhyay_aarya@bahringer-koch.biz",
//        gender: "female",
//        status: "active"
//        },


public class User {


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String gender;
    private String status;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
