package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class BusinessEntityJpaRepositoryImpl extends BaseRepositoryImpl<BusinessEntityModel,Long> implements BusinessEntityJpaRepository {

    public BusinessEntityJpaRepositoryImpl(EntityManager em){
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
