package com.tweetapp.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter

@NoArgsConstructor
@Data
public class ErrorResponse {

    private HttpStatus status;

    private LocalDateTime timestamp;

    private String message;

    public ErrorResponse(HttpStatus status, LocalDateTime timestamp, String message) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }
}
