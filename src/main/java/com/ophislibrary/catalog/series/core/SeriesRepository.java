package com.ophislibrary.catalog.series.core;

import com.ophislibrary.catalog.SeriesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, SeriesId> {
}
