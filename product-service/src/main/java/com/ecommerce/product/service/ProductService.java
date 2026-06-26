package com.ecommerce.product.service;

import com.ecommerce.product.client.InventoryClient;
import com.ecommerce.product.dto.InventoryResponse;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductResponse response = ProductMapper.toResponse(product);

        InventoryResponse inventory =
                inventoryClient.getInventory(product.getProductId());

        response.setStock(inventory.getStock());

        return response;
    }

    public ProductResponse createProduct(ProductRequest request) {

        Product product = ProductMapper.toEntity(request);

        Product saved = productRepository.save(product);

        return ProductMapper.toResponse(saved);
    }

    public ProductResponse updateProduct(String id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());


        Product updated = productRepository.save(product);

        return ProductMapper.toResponse(updated);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

}
