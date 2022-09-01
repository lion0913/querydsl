package com.ll.exam.app3.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiteUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    private String password;

    @Column(unique=true)
    private String email;

    //Builder.Default :build에서 hashset을 빼먹어도 빌드가 될 수 있게 해주는 어노테이션
    @ManyToMany(cascade = CascadeType.ALL)
    @Builder.Default
    private Set<InterestKeyword> interests = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @Builder.Default
    private Set<SiteUser> followers = new HashSet<>();

    public void addInterestKeywordContent(String keyword) {
        interests.add(new InterestKeyword(keyword));
    }

    public void follow(SiteUser user) {
        user.getFollowers().add(this);
    }
}
