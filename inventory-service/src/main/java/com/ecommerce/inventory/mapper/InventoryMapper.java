package com.ecommerce.inventory.mapper;

import com.ecommerce.inventory.dto.InventoryRequest;
import com.ecommerce.inventory.dto.InventoryResponse;
import com.ecommerce.inventory.entity.Inventory;

public class InventoryMapper {

    public static Inventory toEntity(InventoryRequest request) {
        return Inventory.builder()
                .productId(request.getProductId())
                .stock(request.getStock())
                .build();
    }

    public static InventoryResponse toResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .stock(inventory.getStock())
                .build();
    }

}