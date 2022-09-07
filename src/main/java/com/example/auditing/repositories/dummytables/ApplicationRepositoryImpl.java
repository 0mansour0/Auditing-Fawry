package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class ApplicationRepositoryImpl extends BaseRepositoryImpl<ApplicationModel,Long> implements ApplicationRepository {

    public ApplicationRepositoryImpl(EntityManager em){
        super(ApplicationModel.class,em);
    }
    @Override
    public ApplicationModel findByAppName(String name) {
        return queryFactory
                .select(qApplication)
                .from(qApplication)
                .where(qApplication.appName.eq(name))
                .fetchFirst();
    }
}
