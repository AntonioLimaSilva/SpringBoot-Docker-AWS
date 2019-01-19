package br.com.luciano.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerNotExistException extends BusinessException {

    public BeerNotExistException() {
        super("beers-not-exist", HttpStatus.BAD_REQUEST);
    }

}
