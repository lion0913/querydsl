package com.ll.exam.app3.repository;

import com.ll.exam.app3.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends JpaRepository<SiteUser, Integer>, UserRepositoryCustom {

}
