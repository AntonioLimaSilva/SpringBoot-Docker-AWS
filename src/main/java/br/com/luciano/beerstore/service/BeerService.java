package br.com.luciano.beerstore.service;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.repository.BeerRepository;
import br.com.luciano.beerstore.service.exception.BeerAlreadyExistException;
import br.com.luciano.beerstore.service.exception.BeerNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
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

    public Beer merge(Integer id, Beer beer) {
        Optional<Beer> beerOptional = this.beerRepository.findById(id);

        if(!beerOptional.isPresent()) {
            throw new BeerNotExistException("Não foi possível fazer a edição!");
        }

        Beer beerSaved = beerOptional.get();

        BeanUtils.copyProperties(beer, beerSaved, "id");

        return this.beerRepository.save(beerSaved);
    }
}
