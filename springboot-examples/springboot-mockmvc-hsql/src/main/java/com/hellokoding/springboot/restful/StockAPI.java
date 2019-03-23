package com.hellokoding.springboot.restful;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j

@RestController
@RequestMapping("/api/v1/stocks")
public class StockAPI {
    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> findById(@PathVariable Long stockId) {
        Optional<Stock> stockOptional = stockService.findById(stockId);
        if (!stockOptional.isPresent()) {
            log.error("StockId " + stockId + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stockOptional.get());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.save(stock));
    }

    @PatchMapping("/{stockId}")
    public ResponseEntity<Stock> update(@PathVariable Long stockId, @RequestBody Stock updatingStock) {
        Optional<Stock> stockOptional = stockService.findById(stockId);
        if (!stockOptional.isPresent()) {
            log.error("StockId " + stockId + " is not existed");
            ResponseEntity.badRequest().build();
        }

        Stock stock = stockOptional.get();
        if (updatingStock.getName() != null) stock.setName(updatingStock.getName());
        if (updatingStock.getPrice() != null) stock.setPrice(updatingStock.getPrice());

        return ResponseEntity.ok(stockService.save(stock));
    }
}
