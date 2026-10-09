package com.ophislibrary.catalog.series.core;

import com.ophislibrary.catalog.shared.CatalogDomainException;
import com.ophislibrary.catalog.shared.CatalogErrorCode;
import jakarta.persistence.Embeddable;

@Embeddable
public record Title(String value) {

    public Title {
        if (value == null || value.isBlank()) {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_INVALID_TITLE);
        }

        String trimmed = value.trim();
        if (trimmed.length() < 2 || trimmed.length() > 120) {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_INVALID_TITLE);
        }

        value = trimmed;
    }
}