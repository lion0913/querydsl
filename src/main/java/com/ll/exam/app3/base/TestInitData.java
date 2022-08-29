package com.ll.exam.app3.base;

import com.ll.exam.app3.entity.SiteUser;
import com.ll.exam.app3.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.CommandLinePropertySource;

@Configuration
@Profile("test")
public class TestInitData {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            for(int i = 0; i < 5; i++) {
                SiteUser user = SiteUser.builder()
                        .username("박다정"+(i+1))
                        .email("test%d.com".formatted(i+1))
                        .password("1234")
                        .build();

                userRepository.save(user);
            }
        };
    }
}
