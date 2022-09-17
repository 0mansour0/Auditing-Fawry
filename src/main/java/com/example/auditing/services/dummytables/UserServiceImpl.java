package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.UserModel;
import com.example.auditing.repositories.dummytables.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
