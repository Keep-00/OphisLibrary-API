package com.ophislibrary.catalog.shared.web;

import com.ophislibrary.catalog.shared.CatalogDomainException;
import com.ophislibrary.catalog.shared.CatalogErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CatalogDomainException.class)
    public ProblemDetail handleCatalogDomainException(CatalogDomainException ex) {
        CatalogErrorCode errorCode = ex.getErrorCode();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                errorCode.getHttpStatus(),
                ex.getMessage()
        );

        // title assume o nome do erro no enum ou código amigável sem duplicar a chave extra
        problemDetail.setTitle(errorCode.name());
        problemDetail.setType(URI.create("https://api.ophislibrary.com/errors/" + errorCode.getCode().toLowerCase()));
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "One or more fields in the request payload are invalid."
        );

        Map<String, String> invalidParams = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value",
                        (first, second) -> first
                ));

        problemDetail.setTitle("INVALID_REQUEST_PAYLOAD");
        problemDetail.setType(URI.create("https://api.ophislibrary.com/errors/invalid-request-payload"));
        problemDetail.setProperty("invalidParams", invalidParams);
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected internal error occurred. Please try again later."
        );

        problemDetail.setTitle("INTERNAL_SERVER_ERROR");
        problemDetail.setType(URI.create("https://api.ophislibrary.com/errors/internal-server-error"));
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}