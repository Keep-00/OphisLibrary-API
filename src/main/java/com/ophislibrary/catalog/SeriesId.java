package com.ophislibrary.catalog;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public record SeriesId(UUID value) implements Serializable {}
