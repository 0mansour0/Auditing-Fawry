package com.example.auditing.services.param;

import com.example.auditing.repositories.param.ParamTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamTypeServiceImpl implements ParamTypeService {
    @Autowired
    private ParamTypeRepository paramTypeRepository;

    @Override
    public List<String> getParamTypes() {
        return paramTypeRepository.getAllParams();
    }
}
