package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:49 PM
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByPhoneNumber(String phoneNumber);
}
