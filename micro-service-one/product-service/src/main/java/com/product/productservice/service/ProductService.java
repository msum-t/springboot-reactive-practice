package com.product.productservice.service;

import com.product.productservice.dto.ProductDTO;
import com.product.productservice.repo.ProductRepo;
import com.product.productservice.util.EntityDtoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Flux<ProductDTO> getAll(){
        return this.productRepo.findAll().map( EntityDtoUtil::toDto);
    }

    public Mono<ProductDTO> findById(String id){
        return productRepo.findById(id).map(EntityDtoUtil::toDto);
    }

    public  Mono<ProductDTO> insertProduct(Mono<ProductDTO> productDTOMono){
        return productDTOMono.map(EntityDtoUtil::toProduct).flatMap(productRepo::save).map(EntityDtoUtil::toDto);
    }

    public  Mono<ProductDTO> updateProduct(String id,Mono<ProductDTO> productDTOMono){
        return productRepo.findById(id)
                .flatMap(product -> productDTOMono.map(EntityDtoUtil::toProduct)
                .doOnNext(e->e.setId(id)))
                .flatMap(this.productRepo::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> delele(String id){
        return this
                .productRepo.deleteById(id);
    }

    public Mono<Void> deleleAllProduct(){
        return this
                .productRepo.deleteAll();
    }

    public Flux<ProductDTO> priceRange(Double min, Double max) {
        return this.productRepo.findByPriceBetween(Range.closed(min,max)).map(EntityDtoUtil::toDto);
    }
}
