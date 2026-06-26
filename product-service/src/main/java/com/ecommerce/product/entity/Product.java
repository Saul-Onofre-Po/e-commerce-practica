package com.ecommerce.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Entidad que representa un producto almacenado en MongoDB.
 * La anotación @Document indica que esta clase corresponde a una colección
 * llamada "products".
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    /**
     * Identificador único del producto.
     */
    @Id
    private String id;

    /**
     * Nombre del producto.
     */
    private String name;

    /**
     * Descripción del producto.
     */
    private String description;

    /**
     * Precio del producto.
     */
    private BigDecimal price;

}