package br.com.luciano.beerstore.service;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.repository.BeerRepository;
import br.com.luciano.beerstore.service.exception.BeerAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {

    private BeerRepository beerRepository;

    public BeerService(@Autowired  BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer save(final Beer beer) {
        Optional<Beer> beerOptional = this.beerRepository.findByNameAndType(beer.getName(), beer.getType());

        if(beerOptional.isPresent())
            throw new BeerAlreadyExistException();

        return beerRepository.save(beer);
    }

}
