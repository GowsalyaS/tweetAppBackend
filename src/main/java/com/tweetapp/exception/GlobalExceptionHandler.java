package com.tweetapp.exception;

import com.tweetapp.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
             MethodArgumentNotValidException ex
    ) {

        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(),ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<ErrorResponse> handleAll(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(),ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(),ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TweetNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleTweetNotFoundException(TweetNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(),ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
