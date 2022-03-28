package com.product.productservice.repo;

import com.product.productservice.dto.ProductDTO;
import com.product.productservice.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepo extends ReactiveMongoRepository<Product,String> {

    Flux<Product> findByPriceBetween(Range<Double> range);
}
