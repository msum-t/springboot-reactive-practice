package com.product.productservice.controller;

import com.product.productservice.dto.ProductDTO;
import com.product.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductContlloer {

    @Autowired
    private ProductService productService;

    @GetMapping("all")
    public Flux<ProductDTO> getAll(){
        return this.productService.getAll();
    }

    @GetMapping("getById/{id}")
    public Mono<ResponseEntity<ProductDTO>> getById(@PathVariable String id){
        return this.productService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDTO> post(@RequestBody Mono<ProductDTO> productDTO){
        return this.productService.insertProduct(productDTO);
    }

    @PutMapping("getById/{id}")
    public Mono<ResponseEntity<ProductDTO>> updateById(@PathVariable String id,@RequestBody Mono<ProductDTO> productDTO){
        return this.productService.updateProduct(id,productDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("deleteById/{id}")
    public Mono<Void> deleteById(@PathVariable String id){
        return this.productService.delele(id);
    }

    @DeleteMapping("deleteAll")
    public Mono<Void> deleteAll(){
        return this.productService.deleleAllProduct();
    }

    @GetMapping("price-range")
    public Flux<ProductDTO> getByPriceRange(@RequestParam Double min,@RequestParam Double max){
        return this.productService.priceRange(min,max);
    }

}
