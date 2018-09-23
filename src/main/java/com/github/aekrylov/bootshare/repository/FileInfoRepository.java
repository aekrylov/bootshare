package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.model.FileInfo;
import com.github.aekrylov.bootshare.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:00 AM
 */
@Repository
public interface FileInfoRepository extends CrudRepository<FileInfo, String> {

    List<FileInfo> getByOwner(User owner);

    @Query("select f from FileInfo f where f.expiresAt < current_timestamp")
    List<FileInfo> findAllExpired();
}
