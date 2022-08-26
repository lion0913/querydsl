package com.ll.exam.app3.repository;

import com.ll.exam.app3.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Integer>, UserRepositoryCustom {
}
