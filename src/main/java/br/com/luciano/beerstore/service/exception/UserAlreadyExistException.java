package br.com.luciano.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends BusinessException {

    public UserAlreadyExistException() {
        super("user-already-exist", HttpStatus.BAD_REQUEST);
    }
}
