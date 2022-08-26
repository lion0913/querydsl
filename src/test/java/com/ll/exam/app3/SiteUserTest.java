package com.ll.exam.app3;

import com.ll.exam.app3.entity.SiteUser;
import com.ll.exam.app3.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SiteUserTest {
    @Autowired
    UserRepository siteUserRepository;

    @Test
    public void createUserTest() {
        for(int i = 0; i < 5; i++) {
            SiteUser siteUser = new SiteUser();
            siteUser.setUsername("박다정"+(i+1));
            siteUser.setPassword("1234");
            siteUser.setEmail("test%d.com".formatted(i+1));
            siteUserRepository.save(siteUser);
        }

    }

    @Test
    @DisplayName("1번 회원을 Querydsl로 가져오기")
    void t2() {
        SiteUser user = siteUserRepository.getQslUser(1L);

        assertThat(user.getId()).isEqualTo(1);
    }
}
