package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionModel;

import java.util.List;
import java.util.Map;

public interface ActionService {
    ActionModel addAction(ActionWrapper actionWrapper);

    List<ActionModel> searchForActions(Map<String, String> searchCriteria);

    ActionModel actionMapping(ActionWrapper actionWrapper);
}
