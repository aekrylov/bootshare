package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.model.FilePath;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 6:06 PM
 */
@Repository
public interface FilePathRepository extends CrudRepository<FilePath, String> {
}
