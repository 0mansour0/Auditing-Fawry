package com.example.auditing.repositories.action;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;
import com.querydsl.core.BooleanBuilder;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public class ActionJpaRepositoryImpl extends BaseRepositoryImpl<ActionModel,Long> implements ActionJpaRepository {

    public ActionJpaRepositoryImpl(EntityManager em) {
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
    public List<ActionModel> getActionsBySearch(Map<String,String> searchCriteria) {
        boolean returnResult = false;

        BooleanBuilder where = new BooleanBuilder();

        if(!searchCriteria.get("beName").isEmpty()) {
            where.and(qAction.be_name.beName.eq(searchCriteria.get("beName")));
            returnResult = true;
        }
        if (!searchCriteria.get("appName").isEmpty()) {
            where.and(qAction.application_name.appName.eq(searchCriteria.get("appName")));
            returnResult = true;
        }
        if (!searchCriteria.get("userName").isEmpty()) {
            where.and(qAction.user_name.userName.eq(searchCriteria.get("userName")));
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
            return queryFactory
                    .selectFrom(qAction)
                    .where(where)
                    .fetch();
        }
        return null;
    }
}
