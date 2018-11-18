package br.com.luciano.beerstore.service.exception;

public class BeerNotExistException extends RuntimeException {
    public BeerNotExistException(String message) {
        super(message);
    }
}
