package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.dummytables.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;

}
