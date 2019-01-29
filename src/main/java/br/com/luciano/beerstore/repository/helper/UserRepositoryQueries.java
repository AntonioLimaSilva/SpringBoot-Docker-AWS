package br.com.luciano.beerstore.repository.helper;

import br.com.luciano.beerstore.model.User;

import java.util.List;

public interface UserRepositoryQueries {

    List<String> findAllRoles(User user);

}
