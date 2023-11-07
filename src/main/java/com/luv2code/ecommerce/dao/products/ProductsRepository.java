package com.luv2code.ecommerce.dao.products;

import com.luv2code.ecommerce.entity.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<Product, String> {
    Page<Product> findByCategory(String category, Pageable pageable);
}
