package com.example.auditing.repositories.dummytables;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.dummytables.BusinessEntityModel;
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
class BusinessEntityRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    BusinessEntityRepository businessEntityRepository;

    @BeforeEach
    void setUp() {
        BusinessEntityModel be1 = new BusinessEntityModel();
        BusinessEntityModel be2 = new BusinessEntityModel();

        be1.setBeName("Ahmed's Be");
        be2.setBeName("Hany's Be");

        entityManager.persist(be1);
        entityManager.persist(be2);
    }

    @Test
    void findBeByName() {
        BusinessEntityModel be = businessEntityRepository.findBeByName("Ahmed's Be");

        assertThat(be.getBeName()).isEqualTo("Ahmed's Be");
    }

    @Test
    void findBeWithInvalidName_ShouldThrowException() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(()->businessEntityRepository.findBeByName("Mohamed's Be"));
    }
}