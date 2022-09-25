package com.example.auditing.services.param;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.param.ParamModel;
import com.example.auditing.models.param.ParamTypeModel;
import com.example.auditing.repositories.param.ParamRepository;
import com.example.auditing.repositories.param.ParamTypeRepository;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(ParamServiceTest.TestConfig.class)
class ParamServiceTest {
    @Autowired
    ParamService paramService;
    @MockBean
    private ParamRepository paramRepository;
    @MockBean
    private ParamTypeRepository paramTypeRepository;
    private ActionModel actionModel = mock(ActionModel.class);

    @BeforeEach
    void setUp() {
    }

    @Test
    void addParam_ShouldAddIt() {
        ParamModel param = new ParamModel();
        ParamTypeModel paramTypeModel = new ParamTypeModel();

        when(paramTypeRepository.getParamType( anyString() )).thenReturn(paramTypeModel);

        paramTypeModel.setParamTypeId(1L);
        paramTypeModel.setParamTypeCode("customer");
        paramTypeModel.setNameAr("عميل");
        paramTypeModel.setNameEn("customer");

        param.setParam_type(paramTypeModel);
        param.setValue("ali");
        param.setAction_id(actionModel);

        when(paramRepository.saveAndFlush(param)).thenReturn(param);

        ParamModel paramModel = paramService.addParam("ali","customer",actionModel);

        assertNotNull(paramModel);
        assertEquals(param.getValue(),paramModel.getValue());
        verify(paramRepository).saveAndFlush(param);
    }

    @Test
    void getParamValuesByType_ShouldReturnListOfParamsValueWithSameType() {
        List<String> myList = new ArrayList<>( List.of("ali","ahmed") );

        when(paramRepository.getParamOfType( anyString() )).thenReturn(myList);

        List<String> paramList = paramService.getParamOfType("ali");

        assertEquals("ali",paramList.get(0));
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        ParamService paramService() {
            return new ParamServiceImpl();
        }

    }
}

