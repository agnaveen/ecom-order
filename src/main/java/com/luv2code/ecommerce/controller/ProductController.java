package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.products.ProductsRepository;
import com.luv2code.ecommerce.entity.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductsRepository productRepository;

    public ProductController(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/v2/")
    public Page<Product> getProductsByCategoryEmbedded(
            @RequestParam(required = false) String category,
            Pageable pageable) {
        // If a category is provided, return products for that category
        if (category != null && !category.isEmpty()) {
            return productRepository.findByCategory(category, pageable);
        } else {
            // If no category is provided, return all products
            return productRepository.findAll(pageable);
        }

    }

    @GetMapping("/")
    public PagedModel<Product> getProductsByCategory(
            @RequestParam(required = false) String category,
            Pageable pageable) {
        Page<Product> productsPage = null;
        // If a category is provided, return products for that category
        if (category != null && !category.isEmpty()) {
            productsPage= productRepository.findByCategory(category, pageable);
        } else {
            // If no category is provided, return all products
            productsPage = productRepository.findAll(pageable);
        }

        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(
                pageable.getPageSize(),
                productsPage.getNumber(),
                productsPage.getTotalElements());

        PagedModel<Product> resources = PagedModel.of(productsPage.getContent(), pageMetadata);

        // Link to the full list endpoint
        resources.add(linkTo(methodOn(ProductController.class).getProductsByCategory(category, pageable)).withSelfRel());

        return resources;
    }
}

