package br.com.luciano.beerstore.handler;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final int statusCode;
    private final List<Error> errors;

    static ErrorResponse of(HttpStatus status, List<Error> errors) {
        return new ErrorResponse(status.value(), errors);
    }

    static ErrorResponse of(HttpStatus status, Error error) {
        return of(status, Collections.singletonList(error));
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @RequiredArgsConstructor
    static class Error {
        private final String code;
        private final String message;
    }

}
