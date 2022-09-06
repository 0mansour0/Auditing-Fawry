package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class UserJpaRepositoryImpl extends BaseRepositoryImpl<UserModel,Long> implements UserJpaRepository {
    public UserJpaRepositoryImpl(EntityManager em){
        super(UserModel.class,em);
    }

    @Override
    public UserModel findByUserName(String name) {
        return queryFactory
                .select(qUser)
                .from(qUser)
                .where(qUser.userName.eq(name))
                .fetchFirst();
    }
}
