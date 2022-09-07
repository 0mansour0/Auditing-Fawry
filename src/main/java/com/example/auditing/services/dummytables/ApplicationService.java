package com.example.auditing.services.dummytables;

import com.example.auditing.repositories.dummytables.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
}
