package com.example.auditing.repositories.dummytables;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;

public class UserRepositoryImpl extends BaseRepositoryImpl<UserModel,Long> implements UserRepository {
    public UserRepositoryImpl(EntityManager em){
        super(UserModel.class,em);
    }

    @Override
    public UserModel findByUserEmail(String name) {
        UserModel userModel = queryFactory
                .select(qUser)
                .from(qUser)
                .where(qUser.userEmail.eq(name))
                .fetchFirst();

        if (userModel == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return userModel;
    }
}
