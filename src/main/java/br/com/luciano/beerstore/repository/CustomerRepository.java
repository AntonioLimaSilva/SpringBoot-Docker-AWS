package br.com.luciano.beerstore.repository;

import br.com.luciano.beerstore.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByNameAndEmail(String name, String email);

    Page<Customer> findByNameContaining(String name, Pageable pageable);
}
