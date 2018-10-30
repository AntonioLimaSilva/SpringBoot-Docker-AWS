package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.repository.BeerRepository;
import br.com.luciano.beerstore.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
public class BeerResource {

    private final BeerService beerService;

    @PostMapping
    public Beer create(@RequestBody @Valid Beer beer) {
        return this.beerService.save(beer);
    }

}
