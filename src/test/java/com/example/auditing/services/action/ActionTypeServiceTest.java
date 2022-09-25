package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.repositories.action.ActionTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(ActionTypeServiceTest.TestConfig.class)
class ActionTypeServiceTest {
    @Autowired
    ActionTypeService actionTypeService;
    @MockBean
    ActionTypeRepository actionTypeRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void findActionTypeByCode_ShouldReturnResult() {
        ActionTypeModel myAction = new ActionTypeModel();

        myAction.setActionTypeCode("Order_Created");
        myAction.setNameAr("انشاء طلب");
        myAction.setNameEn("order created");
        myAction.setMessageTemplateAr("العميل ${customer.value} قام بإنشاء طلب ${order.value} لشراء منتج ${product.value}");
        myAction.setMessageTemplateEn("customer ${customer.value} created order ${order.value} to buy product ${product.value}");

        when(actionTypeRepository.findActionTypeByCode( anyString() )).thenReturn(myAction);

        ActionTypeModel actionTypeModel = actionTypeService.findActionTypeByCode("Order_Created");

        assertEquals("Order_Created",actionTypeModel.getActionTypeCode());
    }

    @Test
    void getAllActionTypes_ShouldReturnListOfTypes() {
        List<String> myList = new ArrayList<>(List.of("Order_Created","Order_Refunded"));

        when(actionTypeRepository.getAllActionTypes()).thenReturn(myList);

        List<String> actionTypes = actionTypeService.getActionTypes();

        assertEquals("Order_Created",actionTypes.get(0));
        assertEquals("Order_Refunded",actionTypes.get(1));
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        ActionTypeService actionTypeService() {
            return new ActionTypeServiceImpl();
        }

    }
}