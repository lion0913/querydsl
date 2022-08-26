package com.ll.exam.app3.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class SiteUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    private String password;

    @Column(unique=true)
    private String email;
}
