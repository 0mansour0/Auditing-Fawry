package com.example.auditing.services.param;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.param.ParamModel;
import com.example.auditing.models.param.ParamTypeModel;
import com.example.auditing.repositories.param.ParamJpaRepository;
import com.example.auditing.repositories.param.ParamTypeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamService {
    @Autowired
    private ParamJpaRepository paramJpaRepository;
    @Autowired
    private ParamTypeJpaRepository paramTypeJpaRepository;

    public ParamModel create(String value, String paramType, ActionModel action){
        ParamModel param = new ParamModel();
        ParamTypeModel paramTypeModel = paramTypeJpaRepository.getParamType(paramType);

        param.setValue(value);
        param.setParam_type(paramTypeModel);
        param.setAction_id(action);

        return paramJpaRepository.saveAndFlush(param);
    }

}
