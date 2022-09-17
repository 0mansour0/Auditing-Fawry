package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserModel,Long> {
    UserModel findUserByEmail(String name);
}
