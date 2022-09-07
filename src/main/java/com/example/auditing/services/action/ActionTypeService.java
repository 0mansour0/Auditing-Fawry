package com.example.auditing.services.action;

import com.example.auditing.repositories.action.ActionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionTypeService {
    @Autowired
    private ActionTypeRepository actionTypeRepository;
}
