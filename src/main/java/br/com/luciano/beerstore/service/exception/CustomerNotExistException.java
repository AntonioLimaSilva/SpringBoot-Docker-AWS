package br.com.luciano.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotExistException extends BusinessException {

    public CustomerNotExistException() {
        super("customer-not-exist", HttpStatus.BAD_REQUEST);
    }

}
