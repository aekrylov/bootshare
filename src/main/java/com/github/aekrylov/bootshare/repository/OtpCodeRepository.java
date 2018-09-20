package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.model.OtpCode;
import com.github.aekrylov.bootshare.model.OtpKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/20/18 11:50 PM
 */
@Repository
public interface OtpCodeRepository extends CrudRepository<OtpCode, OtpKey> {

    boolean existsById(OtpKey id);
}
