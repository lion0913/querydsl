package com.ll.exam.app3.repository;


import com.ll.exam.app3.entity.InterestKeyword;
import com.ll.exam.app3.entity.QInterestKeyword;
import com.ll.exam.app3.entity.QSiteUser;
import com.ll.exam.app3.entity.SiteUser;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.ll.exam.app3.entity.QInterestKeyword.interestKeyword;
import static com.ll.exam.app3.entity.QSiteUser.siteUser;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public SiteUser getQslUser(Long id){
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .where(siteUser.id.eq(id))
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

    @Override
    public List<SiteUser> getQslUsersOrderByIdAsc() {
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .orderBy(siteUser.id.asc())
                .fetch();
    }

    @Override
    public List<SiteUser> searchQsl(String keyword) {
        return jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .where(siteUser.id.like(keyword))
                .fetch();
    }

    @Override
    public Page<SiteUser> searchQsl(String keyword, Pageable pageable) {
        List<SiteUser> content = jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .where(siteUser.username.contains(keyword).or(siteUser.email.contains(keyword)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(siteUser.count())
                .from(siteUser)
                .where(siteUser.username.contains(keyword).or(siteUser.email.contains(keyword)))
                .fetchOne();

        return new PageImpl<>(content,pageable,count);
    }

    @Override
    public List<SiteUser> listQslByInterests(String keyword) {

        List<SiteUser> content = jpaQueryFactory
                .select(siteUser)
                .from(siteUser)
                .join(siteUser.interests, interestKeyword)
                .where(interestKeyword.content.eq(keyword))
                .fetch();

        return content;
    }

    @Override
    public List<SiteUser> findQslFollowsById(long id) {
//        from site_user_followers as f inner join site_user as u on f.followers_id = u.id where f.site_user_id = 3

//        List<SiteUser> follows = jpaQueryFactory
//                .select(siteUser)
//                .from(siteUser.followers, followers)
//                .join(siteUser)
//                .where()
        return null;
    }

//    @Override
//    public List<InterestKeyword> getFollowingsOfKeywordContents(Long id) {
//        QSiteUser siteUser2 = new QSiteUser("siteUser2");
//
//        return jpaQueryFactory
//                .select(interestKeyword)
//                .distinct()
//                .from(interestKeyword)
//                .innerJoin(interestKeyword.user, siteUser)
//                .innerJoin(siteUser.followers, siteUser2)
//                .where(siteUser2.id.eq(id))
//                .fetch();
//    }
}
