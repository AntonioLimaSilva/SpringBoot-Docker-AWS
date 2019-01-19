package br.com.luciano.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistException extends BusinessException {

    public CustomerAlreadyExistException() {
        super("customer-already-exist", HttpStatus.BAD_REQUEST);
    }
}
