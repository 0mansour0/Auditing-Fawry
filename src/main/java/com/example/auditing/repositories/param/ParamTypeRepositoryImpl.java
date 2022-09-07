package com.example.auditing.repositories.param;

import com.example.auditing.models.param.ParamTypeModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class ParamTypeRepositoryImpl extends BaseRepositoryImpl<ParamTypeModel,Long> implements ParamTypeRepository {

    public ParamTypeRepositoryImpl(EntityManager em) {
        super(ParamTypeModel.class, em);
    }

    @Override
    public ParamTypeModel getParamType(String code) {
        return queryFactory
                .select(qParamType)
                .from(qParamType)
                .where(qParamType.paramTypeCode.eq(code))
                .fetchFirst();
    }
}
