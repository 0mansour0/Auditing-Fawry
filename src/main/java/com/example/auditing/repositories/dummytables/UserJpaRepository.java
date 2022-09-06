package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.base.BaseRepository;

public interface UserJpaRepository extends BaseRepository<UserModel,Long> {
    UserModel findByUserName(String name);
}
