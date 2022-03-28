package com.product.productservice.service;

import com.product.productservice.dto.ProductDTO;
import com.product.productservice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataService implements CommandLineRunner {

    @Autowired
    private ProductService service;

    @Override
    public void run(String... args) throws Exception {
        ProductDTO iphone = ProductDTO.builder().description("iphone").price(100.0).build();
        ProductDTO iphone1 = ProductDTO.builder().description("iphone").price(50.0).build();
        ProductDTO iphone2 = ProductDTO.builder().description("iphone").price(85.0).build();
        ProductDTO iphone3 = ProductDTO.builder().description("iphone").price(200.0).build();
        ProductDTO iphone4 = ProductDTO.builder().description("iphone").price(300.0).build();
        Flux.just(iphone,iphone1,iphone2,iphone3,iphone4)
                .flatMap(ip->service.insertProduct(Mono.just(ip))).subscribe(System.out::println);


    }
}
