package com.example.auditing.repositories.param;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.param.ParamTypeModel;
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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
class ParamTypeRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ParamTypeRepository paramTypeRepository;

    @BeforeEach
    void setUp() {
        ParamTypeModel paramType1 = new ParamTypeModel();
        ParamTypeModel paramType2 = new ParamTypeModel();

        paramType1.setParamTypeCode("customer");
        paramType2.setParamTypeCode("product");

        entityManager.persist(paramType1);
        entityManager.persist(paramType2);
    }

    @Test
    void getParamType_ShouldReturnTheRightTypeAndPass() {
        ParamTypeModel myParamType = paramTypeRepository.getParamType("customer");

        assertEquals("customer",myParamType.getParamTypeCode());
    }

    @Test
    void getAllParams_ShouldReturnTheListOfTypes() {
        List<String> listOfParamType = paramTypeRepository.getAllParams();
        assertThat(listOfParamType)
                .asList()
                .contains("customer","product");
    }

    @Test
    void findParamTypeWithInvalidCode_ShouldThrowException() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(()->paramTypeRepository.getParamType("order"));
    }
}