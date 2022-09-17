package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.repositories.dummytables.BusinessEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessEntityServiceImpl implements BusinessEntityService {
    @Autowired
    private BusinessEntityRepository businessEntityRepository;

    @Override
    public BusinessEntityModel findBeByName(String name){
        return businessEntityRepository.findBeByName(name);
    }
}
