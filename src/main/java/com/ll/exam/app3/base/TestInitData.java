package com.ll.exam.app3.base;

import com.ll.exam.app3.entity.SiteUser;
import com.ll.exam.app3.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.CommandLinePropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class TestInitData {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {

        return args -> {
            SiteUser u1 = SiteUser.builder()
                    .username("user1")
                    .password("{noop}1234")
                    .email("user1@test.com")
                    .build();

            SiteUser u2 = SiteUser.builder()
                    .username("user2")
                    .password("{noop}1234")
                    .email("user2@test.com")
                    .build();

            u1.addInterestKeywordContent("축구");
            u1.addInterestKeywordContent("농구");
            userRepository.saveAndFlush(u1);

            u2.addInterestKeywordContent("농구");
            u2.addInterestKeywordContent("클라이밍");
            u2.addInterestKeywordContent("마라톤");

            userRepository.saveAndFlush(u2);
        };
    }
}
