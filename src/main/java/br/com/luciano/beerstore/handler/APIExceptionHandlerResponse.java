package br.com.luciano.beerstore.handler;

import br.com.luciano.beerstore.service.exception.BeerNotExistException;
import br.com.luciano.beerstore.service.exception.BusinessException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static br.com.luciano.beerstore.handler.ErrorResponse.Error;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class APIExceptionHandlerResponse {

    private static final String NO_MESSAGE_AVAILABLE = "No message available";
    final static private Logger LOG = LoggerFactory.getLogger(APIExceptionHandlerResponse.class);

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
        final List<Error> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .map(code -> toError(code, locale))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(ErrorResponse.of(HttpStatus.BAD_REQUEST, errors));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException ex, Locale locale) {

        final String errorCode = "generic-1";

        return ResponseEntity.badRequest().body(ErrorResponse.of(HttpStatus.BAD_REQUEST, toError(errorCode, locale, ex.getValue())));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, Locale locale) {
        final String errorCode = ex.getCode();

        return ResponseEntity.badRequest().body(ErrorResponse.of(HttpStatus.BAD_REQUEST, toError(errorCode, locale)));
    }

    @ExceptionHandler(BeerNotExistException.class)
    public ResponseEntity<ErrorResponse> handleBeerNotExistException(BeerNotExistException ex, Locale locale) {
        final String errorCode = "beer-id-not-exist";

        return ResponseEntity.badRequest().body(ErrorResponse.of(HttpStatus.BAD_REQUEST, toError(errorCode, locale)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, Locale locale) {
        final String errorCode = "error-1";
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(ErrorResponse.of(status, toError(errorCode, locale)));
    }

    private Error toError(String code, Locale locale, Object... value) {
        String message;
        try {
            message = this.messageSource.getMessage(code, value, locale);
        } catch(NoSuchMessageException ex) {
            LOG.error("Could not find any message for {} code under {} locale", code, locale);
            message = NO_MESSAGE_AVAILABLE;
        }

        return new Error(code, message);
    }

}
