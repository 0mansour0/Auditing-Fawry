package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.models.param.ParamModel;
import com.example.auditing.repositories.action.ActionRepository;
import com.example.auditing.services.dummytables.ApplicationService;
import com.example.auditing.services.dummytables.BusinessEntityService;
import com.example.auditing.services.dummytables.UserService;
import com.example.auditing.services.param.ParamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(ActionServiceTest.TestConfig.class)
class ActionServiceTest {
    @Autowired
    ActionService actionService;
    @MockBean
    ActionRepository actionRepository;
    @MockBean
    UserService userService;
    @MockBean
    ApplicationService applicationService;
    @MockBean
    BusinessEntityService businessEntityService;
    @MockBean
    ActionTypeService actionTypeService;
    @MockBean
    ParamService paramService;

    UserModel userModel = mock(UserModel.class);
    ApplicationModel applicationModel = mock(ApplicationModel.class);
    BusinessEntityModel businessEntityModel = mock(BusinessEntityModel.class);
    ActionTypeModel actionTypeModel = mock(ActionTypeModel.class);
    ParamModel paramModel = mock(ParamModel.class);
    ActionTemplate actionTemplate = mock(ActionTemplate.class);
    ActionModel action = mock(ActionModel.class);

    @BeforeEach
    void setUp() {
    }

    @Disabled("Not ready yet for testing")
    @Test
    void addAction_ShouldAddIt() {
        ActionWrapper actionWrapper = new ActionWrapper();
        ActionModel action1 = new ActionModel();
        ActionTemplate actionTemplate = new ActionTemplate();

        List<ParamModel> myParamList = new ArrayList<>(List.of(paramModel));
        Map<String, String> description = new HashMap<>();

        description.put("en","User hany refunded order 66 created by customer: Adel");
        description.put("ar","المستخدم  hany  استرجع طلب  66  تم انشاءه بواسطه العميل Adel");

        List<Map<String,Map<String,String>>> params = List.of(Map.of("customer",Map.of("value", "Adel")),
                Map.of("order",Map.of("value", "66")));

        actionWrapper.setActionType("Order_Refunded");
        actionWrapper.setBeName("ahmed's BE");
        actionWrapper.setApplicationName("Order");
        actionWrapper.setUserEmail("ahmed@gmail.com");
        actionWrapper.setParams(params);

        action1.setAction_type(actionTypeModel);
        action1.setApplication_name(applicationModel);
        action1.setUser_email(userModel);
        action1.setBe_name(businessEntityModel);
        action1.setParams(myParamList);
        action1.setTime(LocalDateTime.now());
        action1.setDescriptionEn("User hany refunded order 66 created by customer: Adel");

        when(actionRepository.saveAndFlush(action)).thenReturn(action1);
        when(paramService.addParam(anyString(),anyString(),eq(action))).thenReturn(paramModel);
        when(actionTemplate.generatingDescriptions(eq(action1),any())).thenReturn(description);

        ActionModel actionModel1 = actionService.addAction(actionWrapper);

        assertEquals("User hany refunded order 66 created by customer: Adel",actionModel1.getDescriptionEn());

    }

    @Test
    void searchForActions_ShouldReturnListOfActionsWithTheCirateria() {
        ActionModel action1 = new ActionModel();
        List<ActionModel> myActionList = new ArrayList<>();
        List<ParamModel> myParamList = new ArrayList<>(List.of(paramModel));

        action1.setAction_type(actionTypeModel);
        action1.setApplication_name(applicationModel);
        action1.setUser_email(userModel);
        action1.setBe_name(businessEntityModel);
        action1.setParams(myParamList);
        action1.setTime(LocalDateTime.now());
        action1.setDescriptionEn("customer ${customer.value} created order ${order.value} to buy product ${product.value}");

        myActionList.add(action1);


        when(actionRepository.findActionsBySearch(any())).thenReturn(myActionList);

        List<ActionModel> actionList = actionService.searchForActions(any());

        assertEquals("customer ${customer.value} created order ${order.value} to buy product ${product.value}"
                ,actionList.get(0).getDescriptionEn());
    }

    @Test
    void actionMapping_ShouldReturnResult() {
        ActionWrapper actionWrapper = new ActionWrapper();
        List<Map<String,Map<String,String>>> params = List.of(Map.of("customer",Map.of("value", "Adel")),
                Map.of("order",Map.of("value", "66")));
        Map<String, String> description = new HashMap<>();

        description.put("en","User hany refunded order 66 created by customer: Adel");
        description.put("ar","المستخدم  hany  استرجع طلب  66  تم انشاءه بواسطه العميل Adel");

        actionWrapper.setActionType("Order_Refunded");
        actionWrapper.setBeName("ahmed's BE");
        actionWrapper.setApplicationName("Order");
        actionWrapper.setUserEmail("ahmed@gmail.com");
        actionWrapper.setParams(params);

        when(userService.findUserByEmail(anyString())).thenReturn(userModel);
        when(applicationService.findAppByName(anyString())).thenReturn(applicationModel);
        when(businessEntityService.findBeByName(anyString())).thenReturn(businessEntityModel);
        when(actionTypeService.findActionTypeByCode(anyString())).thenReturn(actionTypeModel);
        when(actionTemplate.generatingDescriptions(any(), any())).thenReturn(description);

        ActionModel actionModel = actionService.actionMapping(actionWrapper);

        assertEquals(actionTypeModel,actionModel.getAction_type());

    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        ActionService actionService() {
            return new ActionServiceImpl();
        }

    }
}