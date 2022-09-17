package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.dummytables.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(UserServiceTest.TestConfig.class)
class UserServiceTest {
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findUserByEmail_ShouldReturnResult() {
        UserModel myUser = new UserModel();

        myUser.setUserEmail("ahmed@gmail.com");
        myUser.setUserName("ahmed");

        when(userRepository.findUserByEmail(anyString())).thenReturn(myUser);

        UserModel user = userService.findUserByEmail("hany");

        assertEquals("ahmed@gmail.com",user.getUserEmail());
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        UserService userService() {
            return new UserServiceImpl();
        }

    }
}