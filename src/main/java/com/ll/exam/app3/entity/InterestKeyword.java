package com.ll.exam.app3.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InterestKeyword {

    @Id
    @EqualsAndHashCode.Include
    private String content;


}
