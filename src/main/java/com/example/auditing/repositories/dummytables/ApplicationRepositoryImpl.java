package com.example.auditing.repositories.dummytables;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.dummytables.ApplicationModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationRepositoryImpl extends BaseRepositoryImpl<ApplicationModel,Long> implements ApplicationRepository {

    public ApplicationRepositoryImpl(EntityManager em){
        super(ApplicationModel.class,em);
    }
    @Override
    public ApplicationModel findAppByName(String name) {
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

    @Override
    public List<String> getAllApps() {
        return queryFactory
                .selectFrom(qApplication)
                .fetch()
                .stream()
                .map(ApplicationModel::getAppName)
                .distinct()
                .collect(Collectors.toList());
    }
}
