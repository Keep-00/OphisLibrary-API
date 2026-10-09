package com.ophislibrary.catalog.series.core;

import com.ophislibrary.catalog.shared.CatalogDomainException;
import com.ophislibrary.catalog.shared.CatalogErrorCode;
import jakarta.persistence.Embeddable;

@Embeddable
public record ImagePath(String value) {

    public ImagePath {
        if (value == null || value.isBlank()) {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_INVALID_COVER_PATH);
        }

        value = value.trim();
    }
}