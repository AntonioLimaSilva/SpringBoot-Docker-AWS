package br.com.luciano.beerstore.repository;

import br.com.luciano.beerstore.model.User;
import br.com.luciano.beerstore.repository.helper.UserRepositoryQueries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryQueries {

    Optional<User> findByEmailIgnoreCaseAndActiveTrue(String email);

    Page<User> findByUsernameContaining(String username, Pageable pageable);

    Optional<User> findByEmailIgnoreCase(String email);
}
