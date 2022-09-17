package com.example.auditing.repositories.action;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.action.ActionTypeModel;
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
class ActionTypeRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ActionTypeRepository actionTypeRepository;

    @BeforeEach
    void setUp() {
        ActionTypeModel actionType1 = new ActionTypeModel();
        ActionTypeModel actionType2 = new ActionTypeModel();

        actionType1.setActionTypeCode("Order_Refunded");
        actionType1.setNameEn("order refunded");
        actionType2.setActionTypeCode("Order_Created");
        actionType2.setNameEn("order created");

        entityManager.persist(actionType1);
        entityManager.persist(actionType2);
    }

    @Test
    void findActionTypeByCode_ShouldReturnTheRightActionAndPass() {
        ActionTypeModel actionType = actionTypeRepository.findActionTypeByCode("Order_Refunded");

        assertThat(actionType.getNameEn()).isEqualTo("order refunded");
    }

    @Test
    void getAllActionTypes_ShouldReturnListOfActionTypes() {
        List<String> listOfTypes = actionTypeRepository.getAllActionTypes();

        assertThat(listOfTypes)
                .asList()
                .contains("Order_Created","Order_Refunded");
    }

    @Test
    void findActionTypeWithInvalidCode_ShouldThrowException() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(()->actionTypeRepository.findActionTypeByCode("Order_Updated"))
                .withMessage("Action Type not found");
    }
}