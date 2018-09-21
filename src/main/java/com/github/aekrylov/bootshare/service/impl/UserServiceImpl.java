package com.github.aekrylov.bootshare.service.impl;

import com.github.aekrylov.bootshare.model.User;
import com.github.aekrylov.bootshare.repository.UserRepository;
import com.github.aekrylov.bootshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 12:12 AM
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.findByPhoneNumber(phone);
    }
}
