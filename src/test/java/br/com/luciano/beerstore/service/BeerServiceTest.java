package br.com.luciano.beerstore.service;

import static org.mockito.Mockito.when;

import br.com.luciano.beerstore.model.Beer;
import br.com.luciano.beerstore.model.BeerType;
import br.com.luciano.beerstore.repository.BeerRepository;
import br.com.luciano.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private BeerRepository beerMocked;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.beerService = new BeerService(beerMocked);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists() {
        Beer beerInDatabase = new Beer();
        beerInDatabase.setName("Heineken");
        beerInDatabase.setType(BeerType.LAGER);
        beerInDatabase.setVolume(new BigDecimal(300));

        when(beerMocked.findByNameAndType("Heineken", BeerType.LAGER))
                .thenReturn(Optional.of(beerInDatabase));

        Beer beer = new Beer();
        beer.setName("Heineken");
        beer.setType(BeerType.LAGER);
        beer.setVolume(new BigDecimal(300));

        beerService.save(beer);
    }

    @Test
    public void should_create_new_beer() {
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal(300));

        Beer newBeerInDatabase = new Beer();
        newBeerInDatabase.setId(10);
        newBeerInDatabase.setName("Heineken");
        newBeerInDatabase.setType(BeerType.LAGER);
        newBeerInDatabase.setVolume(new BigDecimal(300));

        when(beerService.save(newBeer)).thenReturn(newBeerInDatabase);

        Beer beerSalved = beerService.save(newBeer);

        assertThat(beerSalved.getId(), equalTo(10));
        assertThat(beerSalved.getName(), equalTo("Heineken"));
        assertThat(beerSalved.getType(), equalTo(BeerType.LAGER));
    }

}
