package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.repositories.action.ActionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionTypeService {
    @Autowired
    private ActionTypeRepository actionTypeRepository;

    public List<String> getActionTypes() {
        return actionTypeRepository
                .findAll()
                .stream()
                .map(ActionTypeModel::getActionTypeCode)
                .collect(Collectors.toList());
    }
}
