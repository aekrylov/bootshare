package com.github.aekrylov.bootshare.repository;

import com.github.aekrylov.bootshare.service.FileNotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Repository that stores file contents as blobs and allows for InputStream based APIs by using raw JDBC statements.
 * Designed to work in JPA+Hibernate environment, so cConnections are obtained through the EntityManager
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/23/18 12:02 AM
 */
@Component
public class RawFileBlobRepository {

    private final EntityManager em;

    @Autowired
    public RawFileBlobRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void insert(String fileId, InputStream contents) {
        em.flush();
        //obtain a connection through Hibernate to get inside current transaction
        em.unwrap(Session.class).doWork(connection -> {
            PreparedStatement stmt = connection.prepareStatement("INSERT into blobs (info_id, data) values (?, ?)");
            stmt.setString(1, fileId);
            stmt.setBinaryStream(2, contents);
            stmt.execute();
        });
    }

    public InputStream findById(String fileId) {
        //obtain a connection through Hibernate to get inside current transaction, if any
        return em.unwrap(Session.class).doReturningWork(connection -> {
            PreparedStatement stmt = connection.prepareStatement("SELECT data from blobs where info_id = ?");
            stmt.setString(1, fileId);
            stmt.execute();

            ResultSet resultSet = stmt.getResultSet();
            if(!resultSet.next()) {
                throw new FileNotFoundException(fileId);
            }
            return resultSet.getBinaryStream(1);
        });
    }
}
