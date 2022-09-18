package com.example.auditing.repositories.param;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.models.param.ParamModel;
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
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
class ParamRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ParamRepository paramRepository;

    @BeforeEach
    void setUp() {
        ParamTypeModel paramTypeModel = new ParamTypeModel();
        ParamModel paramModel = new ParamModel();
        ActionModel action = new ActionModel();
        ActionTypeModel actionTypeModel = new ActionTypeModel();
        BusinessEntityModel businessEntityModel = new BusinessEntityModel();
        UserModel userModel = new UserModel();

        entityManager.persist(actionTypeModel);
        entityManager.persist(businessEntityModel);
        entityManager.persist(userModel);

        action.setAction_type(actionTypeModel);
        action.setBe_name(businessEntityModel);
        action.setUser_email(userModel);
        entityManager.persist(action);

        paramTypeModel.setParamTypeCode("customer");
        entityManager.persist(paramTypeModel);


        paramModel.setParam_type(paramTypeModel);
        paramModel.setValue("ali");
        paramModel.setAction_id(action);
        entityManager.persist(paramModel);
    }

    @Test
    void getParamOfType_ShouldReturnListOfParamValueOfSpecificType() {
        List<String> paramModelList = paramRepository.getParamOfType("customer");
        assertThat(paramModelList)
                .asList()
                .contains("ali");
    }

}