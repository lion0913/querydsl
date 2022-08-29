package com.ll.exam.app3.repository;


import com.ll.exam.app3.entity.SiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.ll.exam.app3.entity.QSiteUser.siteUser;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public SiteUser getQslUser(Long id){
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .where(siteUser.id.eq(1L))
                .fetchOne();
//        return null;
    }

    @Override
    public Long getQslCount() {
        return jpaQueryFactory
                .select(siteUser.count())
                .from(siteUser)
                .fetchOne();
    }

    @Override
    public SiteUser getQslUserOrderByIdAscLimit1() {
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .orderBy(siteUser.id.asc())
                .limit(1L)
                .fetchOne();
    }
}
