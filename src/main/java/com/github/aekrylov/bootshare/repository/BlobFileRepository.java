package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.model.FileBlob;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:00 AM
 */
@Repository
public interface BlobFileRepository extends CrudRepository<FileBlob, String> {

    @Query("select b from FileBlob b where b.info.expiresAt < current_timestamp()")
    List<FileBlob> findAllExpired();
}
