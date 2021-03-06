package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.model.BeerType;
import br.com.luciano.beerstore.repository.BeerRepository;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import br.com.luciano.beerstore.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
public class BeerResource {

    private final BeerService beerService;

    private final BeerRepository beerRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_BEER')")
    public ResponseEntity<Beer> create(@RequestBody @Valid Beer beer) {
        Beer beerSaved = this.beerService.save(beer);
        return ResponseEntity.created(LocationUtil.of(beerSaved.getId())).body(beerSaved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_BEER')")
    public ResponseEntity<Beer> update(@PathVariable Integer id, @RequestBody Beer beer) {
        return ResponseEntity.ok(this.beerService.merge(id, beer));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_READ_BEER')")
    public ResponseEntity<Page<Beer>> search(Pageable pageable) {
        return ResponseEntity.ok(this.beerRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_READ_BEER')")
    public ResponseEntity<Beer> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.beerRepository.findById(id).orElse(new Beer()));
    }

}
