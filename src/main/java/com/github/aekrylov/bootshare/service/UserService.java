package com.github.aekrylov.bootshare.service;

import com.github.aekrylov.bootshare.model.User;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 12:11 AM
 */
public interface UserService {

    User getByPhone(String phone);

    User getByPhoneOrCreate(String phone);
}
