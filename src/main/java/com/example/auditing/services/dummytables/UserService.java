package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.UserModel;

public interface UserService {
    UserModel findUserByEmail(String email);
}
