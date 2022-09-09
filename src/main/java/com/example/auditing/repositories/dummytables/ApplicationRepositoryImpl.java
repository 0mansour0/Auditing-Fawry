package com.example.auditing.repositories.dummytables;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class ApplicationRepositoryImpl extends BaseRepositoryImpl<ApplicationModel,Long> implements ApplicationRepository {

    public ApplicationRepositoryImpl(EntityManager em){
        super(ApplicationModel.class,em);
    }
    @Override
    public ApplicationModel findByAppName(String name) {
        ApplicationModel applicationModel =
                queryFactory
                        .select(qApplication)
                        .from(qApplication)
                        .where(qApplication.appName.eq(name))
                        .fetchFirst();

        if (applicationModel == null) {
            throw new ResourceNotFoundException("Application not found");
        }

        return applicationModel;
    }
}
