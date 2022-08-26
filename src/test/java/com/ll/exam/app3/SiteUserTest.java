package com.ll.exam.app3;

import com.ll.exam.app3.entity.SiteUser;
import com.ll.exam.app3.repository.SiteUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SiteUserTest {
    @Autowired
    SiteUserRepository siteUserRepository;

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
}
