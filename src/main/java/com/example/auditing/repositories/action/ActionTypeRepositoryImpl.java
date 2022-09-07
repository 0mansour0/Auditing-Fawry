package com.example.auditing.repositories.action;

import com.example.auditing.models.action.ActionTypeModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class ActionTypeRepositoryImpl extends BaseRepositoryImpl<ActionTypeModel,Long> implements ActionTypeRepository {

    public ActionTypeRepositoryImpl(EntityManager em){
        super(ActionTypeModel.class,em);
    }
    @Override
    public ActionTypeModel findByActionTypeCode(String code) {
        return queryFactory
                .select(qActionType)
                .from(qActionType)
                .where(qActionType.actionTypeCode.eq(code))
                .fetchFirst();
    }
}
