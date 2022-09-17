package com.example.auditing.services.param;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.param.ParamModel;

import java.util.List;

public interface ParamService {
    ParamModel addParam(String value, String paramType, ActionModel action);

    List<String> getParamOfType(String paramType);
}
