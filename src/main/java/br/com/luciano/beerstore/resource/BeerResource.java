package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.repository.BeerRepository;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import br.com.luciano.beerstore.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public ResponseEntity<Page<Beer>> search(Pageable pageable) {
        return ResponseEntity.ok(this.beerRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beer> edit(@PathVariable Integer id, @RequestBody Beer beer) {
        return ResponseEntity.ok(this.beerService.merge(id, beer));
    }

}
