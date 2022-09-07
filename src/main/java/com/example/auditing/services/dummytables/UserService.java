package com.example.auditing.services.dummytables;

import com.example.auditing.repositories.dummytables.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

}
