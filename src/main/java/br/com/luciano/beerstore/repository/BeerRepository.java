package br.com.luciano.beerstore.repository;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {

    Optional<Beer> findByNameAndType(String name, BeerType type);

}
