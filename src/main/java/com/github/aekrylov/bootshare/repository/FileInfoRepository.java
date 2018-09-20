package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.model.FileInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:00 AM
 */
@Repository
public interface FileInfoRepository extends CrudRepository<FileInfo, String> {
}
