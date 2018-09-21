package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.model.BlobFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:00 AM
 */
@Repository
public interface BlobFileRepository extends CrudRepository<BlobFile, String> {

    @Query("select b from BlobFile b where b.info.expiresAt < current_timestamp()")
    List<BlobFile> findAllExpired();
}
