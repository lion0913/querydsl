package com.ll.exam.app3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
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


    public void addInterestKeywordContent(String keyword) {
        interests.add(new InterestKeyword(keyword));
    }
}
