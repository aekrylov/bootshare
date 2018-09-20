package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.model.BlobFile;
import com.github.aekrylov.bootshare.model.FileInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:00 AM
 */
public interface BlobFileRepository extends CrudRepository<BlobFile, String> {
}
