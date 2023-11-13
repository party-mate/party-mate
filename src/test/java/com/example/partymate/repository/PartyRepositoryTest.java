package com.example.partymate.repository;

import com.example.partymate.testutils.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-13
 */

@ActiveProfiles("local")
@Import(TestConfig.class)
@DataJpaTest
class PartyRepositoryTest {

    @DisplayName("1. ")
    @Test
    void test_1(){

    }
}