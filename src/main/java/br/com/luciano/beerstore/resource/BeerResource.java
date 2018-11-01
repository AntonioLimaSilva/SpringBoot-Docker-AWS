package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import br.com.luciano.beerstore.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Beer> create(@RequestBody @Valid Beer beer) {
        Beer beerSaved = this.beerService.save(beer);
        return ResponseEntity.created(LocationUtil.of(beerSaved.getId())).body(beerSaved);
    }

}
