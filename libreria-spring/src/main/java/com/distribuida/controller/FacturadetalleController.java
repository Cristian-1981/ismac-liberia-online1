package com.distribuida.controller;

import com.distribuida.model.Facturadetalle;
import com.distribuida.service.FacturadetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturadetalles")
public class FacturadetalleController {

    @Autowired
    private FacturadetalleService facturadetalleService;

    @GetMapping
    public ResponseEntity<List<Facturadetalle>> findAll() {
        return ResponseEntity.ok(facturadetalleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facturadetalle> findOne(@PathVariable int id) {
        Facturadetalle facturadetalle = facturadetalleService.findOne(id);
        if (facturadetalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturadetalle);
    }

    @PostMapping
    public ResponseEntity<Facturadetalle> save(@RequestBody Facturadetalle facturadetalle) {
        return ResponseEntity.ok(facturadetalleService.save(facturadetalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facturadetalle> update(@PathVariable int id, @RequestBody Facturadetalle facturadetalle) {
        Facturadetalle actualizado = facturadetalleService.update(id, facturadetalle);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        facturadetalleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
