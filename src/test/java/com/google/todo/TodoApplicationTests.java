package com.google.todo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

//@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
class TodoApplicationTests {

    @Test
    void contextLoads() {
    }

}
