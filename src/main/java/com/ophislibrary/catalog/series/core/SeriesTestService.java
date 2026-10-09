package com.ophislibrary.catalog.series.core;

import com.ophislibrary.catalog.SeriesId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SeriesTestService {

    private static final Logger log = LoggerFactory.getLogger(SeriesTestService.class);
    private final SeriesRepository repository;

    public SeriesTestService(SeriesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Series createSampleSeries() {
        Title title = new Title("Solo Leveling");
        Synopsis synopsis = new Synopsis("Em um mundo onde caçadores enfrentam monstros...");
        ImagePath cover = new ImagePath("/images/covers/sololeveling.jpg");

        Series series = Series.create(title, synopsis, cover, FormatType.MANHWA);
        return repository.save(series);
    }

    // Transações separadas para forçar o fechamento do Persistence Context (Cache L1)
    // e obrigar o JPA a consultar o Cache L2 do Hibernate nas buscas subsequentes.
    @Transactional(readOnly = true)
    public Series findById(UUID uuid) {
        SeriesId seriesId = new SeriesId(uuid);

        log.info("---> Primeiramente buscando do repositório (Transação 1)...");
        return repository.findById(seriesId)
                .orElseThrow(() -> new RuntimeException("Series não encontrada"));
    }

    @Transactional(readOnly = true)
    public Series testL2CacheHit(UUID uuid) {
        SeriesId seriesId = new SeriesId(uuid);

        log.info("---> Buscando pela 1ª vez na Transação A (deve ir ao Banco de Dados)...");
        Series series1 = repository.findById(seriesId).orElseThrow();

        log.info("---> Buscando pela 2ª vez na Transação B (deve vir do Cache L2 sem SQL)...");
        Series series2 = repository.findById(seriesId).orElseThrow();

        return series2;
    }
}
