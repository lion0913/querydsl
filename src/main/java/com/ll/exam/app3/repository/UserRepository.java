package com.ll.exam.app3.repository;

import com.ll.exam.app3.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface UserRepository extends JpaRepository<SiteUser, Integer>, UserRepositoryCustom {

    List<SiteUser> findByInterestKeyword_content(String keyword);
}
