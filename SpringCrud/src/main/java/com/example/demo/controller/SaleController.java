package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Sale;
import com.example.demo.service.SaleService;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Endpoint para crear una nueva venta
    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(sale);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    // Endpoint para obtener una venta por ID
    @GetMapping("/{saleId}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long saleId) {
        Sale sale = saleService.getSaleById(saleId);
        return ResponseEntity.ok(sale);
    }

    // Endpoint para actualizar una venta por ID
    @PutMapping("/{saleId}")
    public ResponseEntity<String> updateSale(@PathVariable Long saleId, @RequestBody Sale updatedSale) {
        saleService.updateSale(saleId, updatedSale);
        return ResponseEntity.ok("Venta actualizada exitosamente");
    }

    // Endpoint para eliminar una venta por ID
    @DeleteMapping("/{saleId}")
    public ResponseEntity<String> deleteSale(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.ok("Venta eliminada exitosamente");
    }
}
