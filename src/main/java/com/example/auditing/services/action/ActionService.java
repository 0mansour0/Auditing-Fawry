package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.action.ActionJpaRepository;
import com.example.auditing.repositories.action.ActionTypeJpaRepository;
import com.example.auditing.repositories.dummytables.ApplicationJpaRepository;
import com.example.auditing.repositories.dummytables.BusinessEntityJpaRepository;
import com.example.auditing.repositories.dummytables.UserJpaRepository;
import com.example.auditing.services.param.ParamService;
import com.mysema.commons.lang.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ActionService {
    @Autowired
    ActionJpaRepository actionJpaRepository;
    @Autowired
    ActionTypeJpaRepository actionTypeJpaRepository;
    @Autowired
    BusinessEntityJpaRepository businessEntityJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    ApplicationJpaRepository applicationJpaRepository;
    @Autowired
    ParamService paramService;

    public ActionModel create(ActionWrapper actionWrapper){
        ActionModel action = actionJpaRepository.saveAndFlush(actionMapping(actionWrapper));

        actionWrapper
                .getParams()
                .forEach(param->param
                        .forEach((k,v)-> {
                            paramService.create(v.get("value"),k,action);
                        }));

        return action;
    }

    public List<ActionModel> searchForActions(Map<String,String> searchCriteria){
        return actionJpaRepository.getActionsBySearch(searchCriteria);
    }

    public ActionModel actionMapping(ActionWrapper actionWrapper){

        ActionModel action = new ActionModel();

        ActionTemplate actionTemplate = new ActionTemplate();

        LocalDateTime localDate = LocalDateTime.now();

        List<Pair<String,String>> paramList = new ArrayList<>();

        UserModel user = userJpaRepository.findByUserName(actionWrapper.getUserName());
        ApplicationModel app = applicationJpaRepository.findByAppName(actionWrapper.getApplicationName());
        BusinessEntityModel be = businessEntityJpaRepository.findByBeName(actionWrapper.getBeName());
        ActionTypeModel actionType = actionTypeJpaRepository.findByActionTypeCode(actionWrapper.getActionType());

        action.setUser_name(user);
        action.setApplication_name(app);
        action.setBe_name(be);
        action.setAction_type(actionType);

        action.setTime(localDate);

        actionWrapper
                .getParams()
                .forEach(param->param
                        .forEach((k,v)-> {
                            paramList.add(new Pair<>(k,v.get("value")));
                        }));

        Map<String, String> description = actionTemplate.generatingDescriptions(action, paramList);

        action.setDescriptionAr(description.get("ar"));
        action.setDescriptionEn(description.get("en"));

        return action;
    }

}
