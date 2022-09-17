package com.example.auditing.repositories.dummytables;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.dummytables.ApplicationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
class ApplicationRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ApplicationRepository applicationRepository;

    @BeforeEach
    void setUp() {
        ApplicationModel application1 = new ApplicationModel();
        ApplicationModel application2 = new ApplicationModel();

        application1.setAppName("Order");
        application2.setAppName("Info");

        entityManager.persist(application1);
        entityManager.persist(application2);
    }

    @Test
    void findAppByName_ShouldBeExist() {
        ApplicationModel applicationModel = applicationRepository.findAppByName("Order");

        assertThat(applicationModel.getAppName()).isEqualTo("Order");
    }

    @Test
    void getAllApps_ShouldReturnTheListOfApplications() {
        List<String> appList = applicationRepository.getAllApps();

        assertThat(appList)
                .asList()
                .contains("Order","Info");
    }

    @Test
    void findAppWithInvalidName_ShouldThrowException() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(()->applicationRepository.findAppByName("Account"));
    }
}