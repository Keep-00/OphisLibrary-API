package com.ophislibrary.catalog.series.core;

import com.ophislibrary.catalog.SeriesId;
import com.ophislibrary.catalog.shared.CatalogDomainException;
import com.ophislibrary.catalog.shared.CatalogErrorCode;
import com.ophislibrary.catalog.shared.SharedIdGenerator;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Series extends AbstractAggregateRoot<Series>{

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id", nullable = false, updatable = false))
    private SeriesId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "title", nullable = false, length = 120))
    private Title title;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "synopsis", nullable = false, length = 2000))
    private Synopsis synopsis;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "cover_path", nullable = false))
    private ImagePath coverPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "format_type", nullable = false, length = 20)
    private FormatType formatType;

    // --- Infraestrutura: Concorrência e Auditoria ---

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Series() {}

    private Series(SeriesId id, Title title, Synopsis synopsis, ImagePath coverPath, Status status, FormatType formatType) {
        this.id = Objects.requireNonNull(id, () -> {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_INVALID_ID);
        });
        this.title = Objects.requireNonNull(title, () -> {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_NULL_PARAMETER, "Title cannot be null.");
        });
        this.synopsis = Objects.requireNonNull(synopsis, () -> {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_NULL_PARAMETER, "Synopsis cannot be null.");
        });
        this.coverPath = Objects.requireNonNull(coverPath, () -> {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_NULL_PARAMETER, "Cover path cannot be null.");
        });
        this.status = Objects.requireNonNull(status, () -> {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_NULL_PARAMETER, "Status cannot be null.");
        });
        this.formatType = Objects.requireNonNull(formatType, () -> {
            throw new CatalogDomainException(CatalogErrorCode.SERIES_NULL_PARAMETER, "FormatType cannot be null.");
        });
    }

    // Factory Method for new instances (starts in DRAFT status)
    public static Series create(Title title, Synopsis synopsis, ImagePath coverPath, FormatType formatType) {
        SeriesId newId = new SeriesId(SharedIdGenerator.generateV7());
        return new Series(newId, title, synopsis, coverPath, Status.DRAFT, formatType);
    }

    public SeriesId getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Synopsis getSynopsis() {
        return synopsis;
    }

    public ImagePath getCoverPath() {
        return coverPath;
    }

    public Status getStatus() {
        return status;
    }

    public FormatType getFormatType() {
        return formatType;
    }

    public Long getVersion() {
        return version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return Objects.equals(id, series.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}