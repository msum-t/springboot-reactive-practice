package com.product.productservice.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String description;
    private Double price;

    public ProductDTO() {
    }
}
