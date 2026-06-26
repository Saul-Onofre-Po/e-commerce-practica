package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.dto.InventoryRequest;
import com.ecommerce.inventory.dto.InventoryResponse;
import com.ecommerce.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{productId}")
    public InventoryResponse getInventoryByProductId(@PathVariable String productId) {
        return inventoryService.getByProductId(productId);
    }

    @PostMapping
    public InventoryResponse createInventory(@RequestBody InventoryRequest request) {
        return inventoryService.createInventory(request);
    }

    @PutMapping("/{productId}")
    public InventoryResponse updateInventory(@PathVariable String productId,
                                             @RequestBody InventoryRequest request) {
        return inventoryService.updateInventory(productId, request);
    }

    @DeleteMapping("/{productId}")
    public void deleteInventory(@PathVariable String productId) {
        inventoryService.deleteInventory(productId);
    }
}