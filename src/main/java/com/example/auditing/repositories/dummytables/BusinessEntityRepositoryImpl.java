package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class BusinessEntityRepositoryImpl extends BaseRepositoryImpl<BusinessEntityModel,Long> implements BusinessEntityRepository {

    public BusinessEntityRepositoryImpl(EntityManager em){
        super(BusinessEntityModel.class,em);
    }
    @Override
    public BusinessEntityModel findByBeName(String name) {
        return queryFactory
                .select(qBusinessEntity)
                .from(qBusinessEntity)
                .where(qBusinessEntity.beName.eq(name))
                .fetchFirst();
    }
}
