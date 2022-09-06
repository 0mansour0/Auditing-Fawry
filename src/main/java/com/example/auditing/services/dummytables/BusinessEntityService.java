package com.example.auditing.services.dummytables;

import com.example.auditing.repositories.dummytables.BusinessEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessEntityService {
    @Autowired
    private BusinessEntityJpaRepository businessEntityJpaRepository;
}
