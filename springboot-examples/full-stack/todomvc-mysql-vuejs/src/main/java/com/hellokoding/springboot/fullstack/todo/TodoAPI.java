package com.hellokoding.springboot.fullstack.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/todos")
@Slf4j
public class TodoAPI {
    private final TodoService stockService;

    @Autowired
    public TodoAPI(TodoService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @PostMapping
    public ResponseEntity saveAll(@Valid @RequestBody List<Todo> todos) {
        log.info(todos.toString());
        return ResponseEntity.ok(stockService.saveAll(todos));
    }
}
