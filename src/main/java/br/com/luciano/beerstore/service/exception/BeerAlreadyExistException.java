package br.com.luciano.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerAlreadyExistException extends BusinessException {

    public BeerAlreadyExistException() {
        super("beers-already-exist", HttpStatus.BAD_REQUEST);
    }

}
