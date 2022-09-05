package com.ll.exam.app3.repository;

import com.ll.exam.app3.entity.InterestKeyword;

import java.util.List;

public interface InterestKeywordRepositoryCustom {
    List<InterestKeyword> getFollowingsOfKeywordContents(Long id);
}
