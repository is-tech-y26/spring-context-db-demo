package ru.makarevich;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;

@Component
public class UserService {
    @Autowired
    private EntityManagerFactory emf;

    public void save(String name) {
        inTransaction(em -> {
            var user = new User();
            user.setName(name);
            em.persist(user);
        });
    }

    public List<User> getAll() {
        return emf.createEntityManager()
                .createQuery("FROM User")
                .getResultList();
    }

    private void inTransaction(Consumer<EntityManager> work) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            work.accept(entityManager);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
}
