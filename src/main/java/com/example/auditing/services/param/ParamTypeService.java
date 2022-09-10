package com.example.auditing.services.param;

import com.example.auditing.models.param.ParamTypeModel;
import com.example.auditing.repositories.param.ParamTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParamTypeService {
    @Autowired
    private ParamTypeRepository paramTypeRepository;

    public List<String> getParamTypes() {
        return paramTypeRepository
                    .findAll()
                    .stream()
                    .map(ParamTypeModel::getParamTypeCode)
                    .collect(Collectors.toList());
    }
}
