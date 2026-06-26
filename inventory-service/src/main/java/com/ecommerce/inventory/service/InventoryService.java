package com.ecommerce.inventory.service;

import com.ecommerce.inventory.dto.InventoryRequest;
import com.ecommerce.inventory.dto.InventoryResponse;
import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.mapper.InventoryMapper;
import com.ecommerce.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(InventoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    public InventoryResponse getByProductId(String productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        return InventoryMapper.toResponse(inventory);
    }

    public InventoryResponse createInventory(InventoryRequest request) {

        Inventory inventory = InventoryMapper.toEntity(request);

        Inventory saved = inventoryRepository.save(inventory);

        return InventoryMapper.toResponse(saved);
    }

    public InventoryResponse updateInventory(String productId, InventoryRequest request) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        inventory.setStock(request.getStock());

        Inventory updated = inventoryRepository.save(inventory);

        return InventoryMapper.toResponse(updated);
    }

    public void deleteInventory(String productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        inventoryRepository.delete(inventory);
    }

}