package net.acdcjunior.prp.web.infrastructure;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.sql.Blob;

@Component
public class UploadUtil {

    @PersistenceContext
    private EntityManager em;

    public Blob createBlob(MultipartFile file) {
        try {
            return Hibernate.getLobCreator(em.unwrap(Session.class)).createBlob(file.getInputStream(), file.getSize());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}