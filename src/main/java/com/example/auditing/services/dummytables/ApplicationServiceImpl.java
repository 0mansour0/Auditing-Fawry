package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.repositories.dummytables.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public ApplicationModel findAppByName(String name){
        return applicationRepository.findAppByName(name);
    }

    @Override
    public List<String> getApplications() {
        return applicationRepository.getAllApps();
    }
}
