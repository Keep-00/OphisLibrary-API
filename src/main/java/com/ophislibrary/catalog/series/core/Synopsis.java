package com.ophislibrary.catalog.series.core;

import com.ophislibrary.catalog.shared.CatalogDomainException;
import com.ophislibrary.catalog.shared.CatalogErrorCode;
import jakarta.persistence.Embeddable;

@Embeddable
public record Synopsis(String value) {

    public Synopsis {
        if (value == null || value.isBlank()) {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_INVALID_SYNOPSIS);
        }

        String trimmed = value.trim();
        if (trimmed.length() < 10 || trimmed.length() > 2000) {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_INVALID_SYNOPSIS);
        }

        value = trimmed;
    }
}