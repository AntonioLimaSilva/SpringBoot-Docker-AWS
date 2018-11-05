package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.repository.BeerRepository;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import br.com.luciano.beerstore.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
public class BeerResource {

    private final BeerService beerService;

    private final BeerRepository beerRepository;

    @PostMapping
    public ResponseEntity<Beer> create(@RequestBody @Valid Beer beer) {
        Beer beerSaved = this.beerService.save(beer);
        return ResponseEntity.created(LocationUtil.of(beerSaved.getId())).body(beerSaved);
    }

    @GetMapping
    public ResponseEntity<List<Beer>> findAll() {
        return ResponseEntity.ok(this.beerRepository.findAll());
    }

}
