package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.repositories.dummytables.ApplicationRepository;
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
@Import(ApplicationServiceTest.TestConfig.class)
class ApplicationServiceTest {
    @Autowired
    ApplicationService applicationService;
    @MockBean
    ApplicationRepository applicationRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void findAppByName_ShouldReturnResult() {
        ApplicationModel myApp = new ApplicationModel();

        myApp.setAppName("Call");

        when(applicationRepository.findAppByName( anyString() )).thenReturn(myApp);

        ApplicationModel applicationModel = applicationService.findAppByName("update Name");

        assertEquals("Call",applicationModel.getAppName());
    }

    @Test
    void getApplications_ShouldReturnListOfApps() {
        List<String> myList = new ArrayList<>(List.of("Order","Call"));

        when(applicationRepository.getAllApps()).thenReturn(myList);

        List<String> appList = applicationService.getApplications();

        assertEquals("Order",appList.get(0));
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        ApplicationService applicationService() {
            return new ApplicationServiceImpl();
        }

    }
}