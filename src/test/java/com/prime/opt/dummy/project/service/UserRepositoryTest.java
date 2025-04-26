package com.prime.opt.dummy.project.service;

import com.prime.opt.dummy.project.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserRepositoryTest {

    private final UserRepository userRepository;

    public UserRepositoryTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "STU1",
            "LIB1",
            "STF1"
    })
    public void fetchUserDetailsByUserId(String userId){
      assertNotNull(userRepository.fetchUserDetailsByUserId(userId));
    }
}
