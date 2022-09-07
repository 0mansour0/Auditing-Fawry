package com.example.auditing.repositories.param;

import com.example.auditing.models.param.ParamModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class ParamRepositoryImpl extends BaseRepositoryImpl<ParamModel,Long> implements ParamRepository {

    public ParamRepositoryImpl(EntityManager em) {
        super(ParamModel.class, em);
    }
}
