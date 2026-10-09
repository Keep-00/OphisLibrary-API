package com.ophislibrary.catalog.series.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/test/series")
public class SeriesTestController {

    private final SeriesTestService testService;

    public SeriesTestController(SeriesTestService testService) {
        this.testService = testService;
    }

    // 1. Cria uma entidade de teste
    @PostMapping
    public ResponseEntity<Series> create() {
        return ResponseEntity.ok(testService.createSampleSeries());
    }

    // 2. Testa o Cache L2 chamando duas buscas seguidas em uma requisição
    @GetMapping("/{id}/test-cache")
    public ResponseEntity<Series> testCache(@PathVariable UUID id) {
        return ResponseEntity.ok(testService.testL2CacheHit(id));
    }

    // 3. Endpoint de consulta simples
    @GetMapping("/{id}")
    public ResponseEntity<Series> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(testService.findById(id));
    }
}