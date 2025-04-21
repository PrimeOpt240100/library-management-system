package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomGeneratorServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "STU",
            "LIB",
            "STF"
    })
    public void testFindMaxIdWithPrefix(String prefix){
        assertNotNull(userRepository.findMaxIdWithPrefix(prefix));
    }
}
