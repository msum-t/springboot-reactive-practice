package com.product.productservice.util;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.product.productservice.dto.ProductDTO;
import com.product.productservice.entity.Product;
import org.springframework.beans.BeanUtils;


public class EntityDtoUtil {

   public  static  ProductDTO toDto(Product product){
       ProductDTO productDTO =new ProductDTO();
       BeanUtils.copyProperties(product,productDTO);

       return productDTO;
   }
    public  static  Product toProduct(ProductDTO productDto){
        Product product =new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }

}
