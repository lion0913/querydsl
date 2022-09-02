package com.ll.exam.app3;

import com.ll.exam.app3.entity.SiteUser;
import com.ll.exam.app3.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest// 이렇게 클래스 @Transactional를 붙이면, 클래스의 각 테스트케이스에 전부 @Transactional 붙은 것과 동일
// @Test + @Transactional 조합은 자동으로 롤백을 유발시킨다.
@Transactional
@ActiveProfiles("test")
public class SiteUserTest {
    @Autowired
    UserRepository siteUserRepository;

    @Test
    public void createUserTest() {
//        for(int i = 0; i < 5; i++) {
            SiteUser user = SiteUser.builder()
                    .username("박다정")
                    .email("dajung.com")
                    .password("1234")
                    .build();

            siteUserRepository.save(user);
//        }

    }

    @Test
    @DisplayName("1번 회원을 Querydsl로 가져오기")
    void t2() {
        SiteUser user = siteUserRepository.getQslUser(1L);

        assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("모든 회원의 수")
    void t3(){
        Long count = siteUserRepository.getQslCount();
        assertThat(count).isEqualTo(5L);
    }

    @Test
    @DisplayName("가장 오래된 회원 한명")
    void t4(){
        SiteUser siteUser = siteUserRepository.getQslUserOrderByIdAscLimit1();
        assertThat(siteUser.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("전체회원, 오래된순")
    void t5(){
        List<SiteUser> siteUser = siteUserRepository.getQslUsersOrderByIdAsc();
        assertThat(siteUser.get(0).getId()).isEqualTo(1L);
        assertThat(siteUser.get(1).getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("like 검색")
    void t6(){
        List<SiteUser> siteUser = siteUserRepository.searchQsl("1");
        assertThat(siteUser.get(0).getId()).isEqualTo(1L);
//        assertThat(siteUser.get(1).getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("검색, Page 리턴")
    void t7() {
        int itemsInAPage = 1; // 한 페이지에 보여줄 아이템 개수
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(1, itemsInAPage, Sort.by(sorts)); // 한 페이지에 10까지 가능
        Page<SiteUser> users =  siteUserRepository.searchQsl("user", pageable);
        // 검색어 : user1
        // 한 페이지에 나올 수 있는 아이템 수 : 1개
        // 현재 페이지 : 1
        // 정렬 : id 역순

        // 내용 가져오는 SQL
        /*
        SELECT site_user.*
        FROM site_user
        WHERE site_user.username LIKE '%user%'
        OR site_user.email LIKE '%user%'
        ORDER BY site_user.id ASC
        LIMIT 1, 1
         */

        // 전체 개수 계산하는 SQL
        /*
        SELECT COUNT(*)
        FROM site_user
        WHERE site_user.username LIKE '%user%'
        OR site_user.email LIKE '%user%'
         */
    }

    @Test
    @DisplayName("검색, Page 리턴, id DESC, pageSize=1, page=0")
    void t10() {
        SiteUser u2 = siteUserRepository.getQslUser(2L);

        u2.addInterestKeywordContent("축구");
        u2.addInterestKeywordContent("롤");
        u2.addInterestKeywordContent("헬스");
        u2.addInterestKeywordContent("헬스"); // 중복등록은 무시

        siteUserRepository.save(u2);
        // 엔티티클래스 : InterestKeyword(interest_keyword 테이블)
        // 중간테이블도 생성되어야 함, 힌트 : @ManyToMany
        // interest_keyword 테이블에 축구, 롤, 헬스에 해당하는 row 3개 생성
    }

    @Test
    @DisplayName("축구에 관심이 있는 회원을 검색")
    void t11() {
        List<SiteUser> userList = siteUserRepository.listQslByInterests("축구");

        assertThat(userList.size()).isEqualTo(1);
        assertThat(userList.get(0).getUsername()).isEqualTo("user1");
    }

    @Test
    @DisplayName("no qsl, jpa 기본기능으로 content 검색하기")
    void t12() {
        List<SiteUser> users = siteUserRepository.findByInterests_content("축구");

        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUsername()).isEqualTo("user1");
    }

    @Test
    @DisplayName("u1이 u2를 팔로우")
    void t13() {
        SiteUser u1 = siteUserRepository.getQslUser(1L);
        SiteUser u2 = siteUserRepository.getQslUser(2L);

        //09.01 u1이 u2를 팔로우한다는 직관적인 함수로 변경
        u1.follow(u2);

        siteUserRepository.save(u2);

    }

    @Test
    @DisplayName("본인이 본인을 팔로우 할 수 없다.")
    @Rollback(false)
    void t14() {
        SiteUser u1 = siteUserRepository.getQslUser(1L);
        u1.follow(u1);

        siteUserRepository.save(u1);

        assertThat(u1.getFollowers().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("특정 회원의 팔로워들과 팔로잉들을 알 수 있어야 한다.")
    @Rollback(false)
    void t15() {
        SiteUser u1 = siteUserRepository.getQslUser(1L);
        SiteUser u2 = siteUserRepository.getQslUser(2L);

        u1.follow(u2);

        assertThat(u1.getFollowers().size()).isEqualTo(0);
        assertThat(u2.getFollowers().size()).isEqualTo(1);

        u1.getFollowers();
        u1.getFollowings();

        u2.getFollowers();
        u2.getFollowings();
    }
}
