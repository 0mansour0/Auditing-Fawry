package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.repositories.action.ActionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionTypeServiceImpl implements ActionTypeService {
    @Autowired
    private ActionTypeRepository actionTypeRepository;

    @Override
    public ActionTypeModel findActionTypeByCode(String code){
        return actionTypeRepository.findActionTypeByCode(code);
    }

    @Override
    public List<String> getActionTypes() {
        return actionTypeRepository.getAllActionTypes();
    }
}
