package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.repositories.dummytables.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public List<String> getApplications() {
        return applicationRepository
                .findAll()
                .stream()
                .map(ApplicationModel::getAppName)
                .collect(Collectors.toList());
    }
}
