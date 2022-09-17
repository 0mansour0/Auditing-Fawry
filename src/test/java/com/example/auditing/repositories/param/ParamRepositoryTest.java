package com.example.auditing.repositories.param;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.param.ParamModel;
import com.example.auditing.models.param.ParamTypeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
class ParamRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ParamRepository paramRepository;
    ActionModel actionModel = mock(ActionModel.class);

    @BeforeEach
    void setUp() {
        ParamTypeModel paramTypeModel = new ParamTypeModel();
        ParamModel paramModel = new ParamModel();

        paramTypeModel.setParamTypeCode("customer");
        entityManager.persist(paramTypeModel);


        paramModel.setParam_type(paramTypeModel);
        paramModel.setValue("ali");
        paramModel.setAction_id(actionModel);
        entityManager.persist(paramModel);
    }

    @Test
    @Disabled("Not ready yet for testing")
    void getParamOfType_ShouldReturnListOfParamValueOfSpecificType() {
        List<String> paramModelList = paramRepository.getParamOfType("customer");
        assertThat(paramModelList)
                .asList()
                .contains("ali");
    }

}