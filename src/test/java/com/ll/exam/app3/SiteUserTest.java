package com.ll.exam.app3;

import com.ll.exam.app3.entity.SiteUser;
import com.ll.exam.app3.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

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
}
