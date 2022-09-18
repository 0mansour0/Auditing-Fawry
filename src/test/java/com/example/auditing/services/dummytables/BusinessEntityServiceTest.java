package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.repositories.dummytables.BusinessEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(BusinessEntityServiceTest.TestConfig.class)
class BusinessEntityServiceTest {
    @Autowired
    BusinessEntityService businessEntityService;
    @MockBean
    BusinessEntityRepository businessEntityRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findBeByName_ShouldReturnResult() {
        BusinessEntityModel myBusinessEntity = new BusinessEntityModel();

        myBusinessEntity.setBeName("ahmed's BE");

        when(businessEntityRepository.findBeByName( anyString() )).thenReturn(myBusinessEntity);

        BusinessEntityModel businessEntityModel = businessEntityService.findBeByName("ahmed's BE");

        assertEquals("ahmed's BE",businessEntityModel.getBeName());
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        BusinessEntityService businessEntityService() {
            return new BusinessEntityServiceImpl();
        }

    }
}