package com.example.auditing.services.param;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.param.ParamModel;
import com.example.auditing.models.param.ParamTypeModel;
import com.example.auditing.repositories.param.ParamRepository;
import com.example.auditing.repositories.param.ParamTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService {
    @Autowired
    private ParamRepository paramRepository;
    @Autowired
    private ParamTypeRepository paramTypeRepository;

    @Override
    public ParamModel addParam(String value, String paramType, ActionModel action){
        ParamModel param = new ParamModel();
        ParamTypeModel paramTypeModel = paramTypeRepository.getParamType(paramType);

        param.setValue(value);
        param.setParam_type(paramTypeModel);
        param.setAction_id(action);

        return paramRepository.saveAndFlush(param);
    }

    @Override
    public List<String> getParamOfType(String paramType){
        return paramRepository.getParamOfType(paramType);
    }

}
