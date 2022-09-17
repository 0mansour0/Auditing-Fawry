package com.example.auditing.repositories.dummytables;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.dummytables.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserModel user1 = new UserModel();
        UserModel user2 = new UserModel();

        user1.setUserName("ahmed");
        user1.setUserEmail("ahmed@gmail.com");

        user2.setUserName("ali");
        user2.setUserEmail("ali@gmail.com");

        entityManager.persist(user1);
        entityManager.persist(user2);
    }

    @Test
    void findUserByEmail_ShouldReturnTheRightUser() {
        UserModel user = userRepository.findUserByEmail("ahmed@gmail.com");

        assertThat(user.getUserName()).isEqualTo("ahmed");
    }

    @Test
    void findUserWithInvalidEmail_ShouldThrowException() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(()->userRepository.findUserByEmail("mohamed@gmail.com"));
    }
}