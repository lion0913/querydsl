package com.ll.exam.app3.repository;

import com.ll.exam.app3.entity.InterestKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestKeywordRepository extends JpaRepository<InterestKeyword, Long>, InterestKeywordRepositoryCustom {

}
