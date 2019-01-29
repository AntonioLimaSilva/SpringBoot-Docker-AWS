package br.com.luciano.beerstore.repository.helper;

import br.com.luciano.beerstore.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<String> findAllRoles(User user) {
        String query = "select distinct r.name from User u inner join u.grups g inner join g.roles r where u = :user";
        return this.manager.createQuery(query, String.class)
                .setParameter("user", user)
                .getResultList();

    }

}
