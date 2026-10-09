package com.ophislibrary.catalog.shared;

import org.springframework.http.HttpStatus;

public enum CatalogErrorCode {

    // 400 Bad Request / 422 Unprocessable Content - Domain Validations
    SERIES_INVALID_ID("SERIES_001", "Series ID cannot be null.", HttpStatus.BAD_REQUEST),
    SERIES_INVALID_TITLE("SERIES_002", "Title must be between 2 and 120 characters.", HttpStatus.UNPROCESSABLE_CONTENT),
    SERIES_INVALID_SYNOPSIS("SERIES_003", "Synopsis must be between 10 and 2000 characters.", HttpStatus.UNPROCESSABLE_CONTENT),
    SERIES_INVALID_COVER_PATH("SERIES_004", "Cover image path cannot be null or empty.", HttpStatus.UNPROCESSABLE_CONTENT),
    SERIES_NULL_PARAMETER("SERIES_005", "Required parameter cannot be null.", HttpStatus.BAD_REQUEST),

    // 409 Conflict - State Transitions & Rules
    SERIES_ALREADY_PUBLISHED("SERIES_100", "Series is already published.", HttpStatus.CONFLICT),
    SERIES_CANNOT_PUBLISH_WITHOUT_COVER("SERIES_101", "Cannot publish a series without a valid cover image.", HttpStatus.CONFLICT),

    // 404 Not Found
    SERIES_NOT_FOUND("SERIES_404", "Series not found with the provided identifier.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;

    CatalogErrorCode(String code, String defaultMessage, HttpStatus httpStatus) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}