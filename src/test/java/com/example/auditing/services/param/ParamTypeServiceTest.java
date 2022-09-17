package com.example.auditing.services.param;

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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(ParamTypeServiceTest.TestConfig.class)
class ParamTypeServiceTest {

    @Autowired
    ParamTypeService paramTypeService;
    @MockBean
    ParamTypeRepository paramTypeRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllParamTypes_ShouldReturnListOfTypes() {
        List<String> myList = new ArrayList<>( List.of("customer", "order"));
        when(paramTypeRepository.getAllParams()).thenReturn(myList);

        List<String> paramList = paramTypeService.getParamTypes();

        assertEquals("customer",paramList.get(0));
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        ParamTypeService paramTypeService() {
            return new ParamTypeServiceImpl();
        }

    }
}