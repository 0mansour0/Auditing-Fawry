package com.example.auditing.repositories.action;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ActionTypeRepositoryImpl extends BaseRepositoryImpl<ActionTypeModel,Long> implements ActionTypeRepository {

    public ActionTypeRepositoryImpl(EntityManager em){
        super(ActionTypeModel.class,em);
    }
    @Override
    public ActionTypeModel findActionTypeByCode(String code) {
        ActionTypeModel actionTypeModel =
                queryFactory
                        .select(qActionType)
                        .from(qActionType)
                        .where(qActionType.actionTypeCode.eq(code))
                        .fetchFirst();

        if (actionTypeModel == null) {
            throw new ResourceNotFoundException("Action Type not found");
        }

        return actionTypeModel;
    }

    @Override
    public List<String> getAllActionTypes() {
        return queryFactory
                .selectFrom(qActionType)
                .fetch()
                .stream()
                .map(ActionTypeModel::getActionTypeCode)
                .distinct()
                .collect(Collectors.toList());
    }
}
