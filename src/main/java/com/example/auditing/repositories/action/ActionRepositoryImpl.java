package com.example.auditing.repositories.action;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.action.ActionModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;
import com.querydsl.core.BooleanBuilder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public class ActionRepositoryImpl extends BaseRepositoryImpl<ActionModel,Long> implements ActionRepository {

    public ActionRepositoryImpl(EntityManager em) {
        super(ActionModel.class, em);
    }

    @Override
    public ActionModel getActionById(Long id) {
        return queryFactory
                .selectFrom(qAction)
                .where(qAction.actionId.eq(id))
                .fetchFirst();
    }

    @Override
    public List<ActionModel> findActionsBySearch(Map<String,String> searchCriteria) {
        boolean returnResult = false;

        BooleanBuilder where = new BooleanBuilder();

        List<ActionModel> actionList = null;

        if(!searchCriteria.get("beName").isEmpty()) {
            where.and(qAction.be_name.beName.eq(searchCriteria.get("beName")));
            returnResult = true;
        }
        if (!searchCriteria.get("appName").isEmpty()) {
            where.and(qAction.application_name.appName.eq(searchCriteria.get("appName")));
            returnResult = true;
        }
        if (!searchCriteria.get("userEmail").isEmpty()) {
            where.and(qAction.user_email.userEmail.eq(searchCriteria.get("userEmail")));
            returnResult = true;
        }
        if (!searchCriteria.get("actionType").isEmpty()) {
            where.and(qAction.action_type.actionTypeCode.eq(searchCriteria.get("actionType")));
            returnResult = true;
        }
        if (!searchCriteria.get("paramType").isEmpty() && !searchCriteria.get("paramValue").isEmpty()) {
            where.and(qAction.params.any().param_type.paramTypeCode.eq(searchCriteria.get("paramType"))
                    .and(qAction.params.any().value.eq(searchCriteria.get("paramValue"))));
            returnResult = true;
        }

        if(returnResult) {
            actionList =
                    queryFactory
                    .selectFrom(qAction)
                    .where(where)
                    .fetch();
        }

        if (actionList == null) {
            throw new ResourceNotFoundException("no matching searching criteria");
        }

        return actionList;
    }
}
