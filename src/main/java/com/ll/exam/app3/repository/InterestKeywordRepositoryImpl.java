package com.ll.exam.app3.repository;


import com.ll.exam.app3.entity.InterestKeyword;
import com.ll.exam.app3.entity.QSiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ll.exam.app3.entity.QInterestKeyword.interestKeyword;
import static com.ll.exam.app3.entity.QSiteUser.siteUser;

@RequiredArgsConstructor
public class InterestKeywordRepositoryImpl implements InterestKeywordRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<InterestKeyword> getFollowingsOfKeywordContents(Long id) {
        QSiteUser siteUser2 = new QSiteUser("siteUser2");

        return jpaQueryFactory
                .select(interestKeyword)
                .distinct()
                .from(interestKeyword)
                .innerJoin(interestKeyword.user, siteUser)
                .innerJoin(siteUser.followers, siteUser2)
                .where(siteUser2.id.eq(id))
                .fetch();
    }
}
