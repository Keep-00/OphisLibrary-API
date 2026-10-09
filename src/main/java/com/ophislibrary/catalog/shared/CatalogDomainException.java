package com.ophislibrary.catalog.shared;

import java.util.Objects;

public class CatalogDomainException extends RuntimeException {

    private final CatalogErrorCode errorCode;

    public CatalogDomainException(CatalogErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = Objects.requireNonNull(errorCode, "CatalogErrorCode cannot be null");
    }

    public CatalogDomainException(CatalogErrorCode errorCode, String customDetail) {
        super(customDetail != null && !customDetail.isBlank() ? customDetail : errorCode.getDefaultMessage());
        this.errorCode = Objects.requireNonNull(errorCode, "CatalogErrorCode cannot be null");
    }

    public CatalogErrorCode getErrorCode() {
        return errorCode;
    }
}