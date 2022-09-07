package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.action.ActionRepository;
import com.example.auditing.repositories.action.ActionTypeRepository;
import com.example.auditing.repositories.dummytables.ApplicationRepository;
import com.example.auditing.repositories.dummytables.BusinessEntityRepository;
import com.example.auditing.repositories.dummytables.UserRepository;
import com.example.auditing.services.param.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ActionService {
    @Autowired
    ActionRepository actionRepository;
    @Autowired
    ActionTypeRepository actionTypeRepository;
    @Autowired
    BusinessEntityRepository businessEntityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ParamService paramService;

    public ActionModel addAction(ActionWrapper actionWrapper){
        ActionModel action = actionRepository.saveAndFlush(actionMapping(actionWrapper));

        actionWrapper
                .getParams()
                .forEach(param->param
                        .forEach((k,v)-> {
                            paramService.addParam(v.get("value"),k,action);
                        }));

        return action;
    }

    public List<ActionModel> searchForActions(Map<String,String> searchCriteria){
        return actionRepository.findActionsBySearch(searchCriteria);
    }

    public ActionModel actionMapping(ActionWrapper actionWrapper){

        ActionModel action = new ActionModel();

        ActionTemplate actionTemplate = new ActionTemplate();

        LocalDateTime localDate = LocalDateTime.now();

        UserModel user = userRepository.findByUserEmail(actionWrapper.getUserEmail());
        ApplicationModel app = applicationRepository.findByAppName(actionWrapper.getApplicationName());
        BusinessEntityModel be = businessEntityRepository.findByBeName(actionWrapper.getBeName());
        ActionTypeModel actionType = actionTypeRepository.findByActionTypeCode(actionWrapper.getActionType());

        action.setUser_email(user);
        action.setApplication_name(app);
        action.setBe_name(be);
        action.setAction_type(actionType);
        action.setTime(localDate);

        Map<String, String> description = actionTemplate.generatingDescriptions(action, actionWrapper.getParams());

        action.setDescriptionAr(description.get("ar"));
        action.setDescriptionEn(description.get("en"));

        return action;
    }

}
