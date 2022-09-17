package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionTypeModel;

import java.util.List;

public interface ActionTypeService {
    ActionTypeModel findActionTypeByCode(String code);

    List<String> getActionTypes();
}
