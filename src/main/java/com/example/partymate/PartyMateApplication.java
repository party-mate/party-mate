package com.example.partymate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties({
        com.example.partymate.domain.FileStorageProperties.class
})
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class PartyMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartyMateApplication.class, args);
    }

}
