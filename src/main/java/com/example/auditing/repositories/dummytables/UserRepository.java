package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.base.BaseRepository;

public interface UserRepository extends BaseRepository<UserModel,Long> {
    UserModel findByUserEmail(String name);
}
