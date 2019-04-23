package br.com.luciano.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class UserNotExistException extends BusinessException {

    public UserNotExistException() {
        super("user-not-exist", HttpStatus.BAD_REQUEST);
    }
}
