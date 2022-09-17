package com.example.auditing.repositories.action;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.models.dummytables.ApplicationModel;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
class ActionRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    ActionRepository actionRepository;
    ActionModel action1 = new ActionModel();
    ActionModel action2 = new ActionModel();
    ActionTypeModel actionType1 = new ActionTypeModel();
    ActionTypeModel actionType2 = new ActionTypeModel();
    ApplicationModel application1 = new ApplicationModel();
    ApplicationModel application2 = new ApplicationModel();
    BusinessEntityModel be1 = new BusinessEntityModel();
    BusinessEntityModel be2 = new BusinessEntityModel();
    UserModel user1 = new UserModel();
    UserModel user2 = new UserModel();
    ParamTypeModel paramType1 = new ParamTypeModel();
    ParamTypeModel paramType2 = new ParamTypeModel();
    ParamModel param1 = new ParamModel();
    ParamModel param2 = new ParamModel();
    ParamModel param3 = new ParamModel();
    ParamModel param4 = new ParamModel();
    Map<String,String> searchCriteria = new HashMap<>();

    @BeforeEach
    void setUp() {

        searchCriteria.put("beName","");
        searchCriteria.put("appName","");
        searchCriteria.put("userEmail","");
        searchCriteria.put("actionType","");
        searchCriteria.put("paramType","");
        searchCriteria.put("paramValue","");

        actionType1.setActionTypeCode("Order_Refunded");
        actionType1.setNameEn("order refunded");
        actionType2.setActionTypeCode("Order_Created");
        actionType2.setNameEn("order created");

        application1.setAppName("Order");
        application2.setAppName("Info");

        be1.setBeName("Ahmed's Be");
        be2.setBeName("Hany's Be");

        user1.setUserName("ahmed");
        user1.setUserEmail("ahmed@gmail.com");
        user2.setUserName("ali");
        user2.setUserEmail("ali@gmail.com");

        paramType1.setParamTypeCode("customer");
        paramType2.setParamTypeCode("order");

        entityManager.persist(actionType1);
        entityManager.persist(actionType2);
        entityManager.persist(application1);
        entityManager.persist(application2);
        entityManager.persist(be1);
        entityManager.persist(be2);
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(paramType1);
        entityManager.persist(paramType2);

        action1.setUser_email(user1);
        action1.setAction_type(actionType1);
        action1.setBe_name(be1);
        action1.setApplication_name(application1);

        action2.setUser_email(user2);
        action2.setAction_type(actionType2);
        action2.setBe_name(be2);
        action2.setApplication_name(application2);

        entityManager.persist(action1);
        entityManager.persist(action2);

        param1.setParam_type(paramType1);
        param1.setValue("mohamed");
        param1.setAction_id(action1);
        param2.setParam_type(paramType2);
        param2.setValue("1");
        param2.setAction_id(action1);
        param3.setParam_type(paramType1);
        param3.setValue("khaled");
        param3.setAction_id(action2);
        param4.setParam_type(paramType2);
        param4.setValue("2");
        param4.setAction_id(action2);

        entityManager.persist(param1);
        entityManager.persist(param2);
        entityManager.persist(param3);
        entityManager.persist(param4);
    }

    @Test
    void findActionsByBe_ShouldPass() {
        searchCriteria.put("beName",be1.getBeName());

        List<ActionModel> actionList = actionRepository.findActionsBySearch(searchCriteria);

        assertThat(actionList)
                .asList()
                .contains(action1);
    }

    @Test
    void findActionsByApp_ShouldPass() {
        searchCriteria.put("beApp",application1.getAppName());

        List<ActionModel> actionList = actionRepository.findActionsBySearch(searchCriteria);

        assertThat(actionList)
                .asList()
                .contains(action1);
    }

    @Test
    void findActionsByActionType_ShouldPass() {
        searchCriteria.put("actionType",actionType1.getActionTypeCode());

        List<ActionModel> actionList = actionRepository.findActionsBySearch(searchCriteria);

        assertThat(actionList)
                .asList()
                .contains(action1);
    }

    @Test
    void findActionsByUser_ShouldPass() {
        searchCriteria.put("userEmail",user1.getUserEmail());

        List<ActionModel> actionList = actionRepository.findActionsBySearch(searchCriteria);

        assertThat(actionList)
                .asList()
                .contains(action1);
    }

    @Test
    void findActionsByParams_ShouldPass() {
        searchCriteria.put("paramType",paramType1.getParamTypeCode());
        searchCriteria.put("paramValue",param1.getValue());
        List<ActionModel> actionList = actionRepository.findActionsBySearch(searchCriteria);

        assertThat(actionList)
                .asList()
                .contains(action1);
    }

    @Test
    void searchForActionsWithoutCriteria_ShouldReturnAllActions(){
        List<ActionModel> actionList = actionRepository.findActionsBySearch(searchCriteria);

        assertThat(actionList)
                .asList()
                .contains(action1,action2);
    }

    @Test
    void findActionsByParamsAndBe_ShouldPass() {
        searchCriteria.put("beName",be2.getBeName());
        searchCriteria.put("paramType",paramType1.getParamTypeCode());
        searchCriteria.put("paramValue",param3.getValue());
        List<ActionModel> actionList = actionRepository.findActionsBySearch(searchCriteria);

        assertThat(actionList)
                .asList()
                .contains(action2);
    }

    @Test
    void findActionsByUserWithInvalidData_ShouldThrowException() {
        searchCriteria.put("userEmail","khaled@gmail.com");

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(()->actionRepository.findActionsBySearch(searchCriteria))
                .withMessage("no matching searching criteria");
    }
}