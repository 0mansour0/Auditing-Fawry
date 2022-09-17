package com.example.auditing.services.action;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.action.ActionRepository;
import com.example.auditing.services.dummytables.ApplicationService;
import com.example.auditing.services.dummytables.BusinessEntityService;
import com.example.auditing.services.dummytables.UserService;
import com.example.auditing.services.param.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    ActionRepository actionRepository;
    @Autowired
    ActionTypeService actionTypeService;
    @Autowired
    BusinessEntityService businessEntityService;
    @Autowired
    UserService userService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    ParamService paramService;

    @Override
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

    @Override
    public List<ActionModel> searchForActions(Map<String, String> searchCriteria){
        return actionRepository.findActionsBySearch(searchCriteria);
    }

    @Override
    public ActionModel actionMapping(ActionWrapper actionWrapper){

        ActionModel action = new ActionModel();

        ActionTemplate actionTemplate = new ActionTemplate();

        LocalDateTime localDate = LocalDateTime.now();

        UserModel user = userService.findUserByEmail(actionWrapper.getUserEmail());
        ApplicationModel app = applicationService.findAppByName(actionWrapper.getApplicationName());
        BusinessEntityModel be = businessEntityService.findBeByName(actionWrapper.getBeName());
        ActionTypeModel actionType = actionTypeService.findActionTypeByCode(actionWrapper.getActionType());

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
