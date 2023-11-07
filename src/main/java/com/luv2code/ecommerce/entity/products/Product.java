package com.luv2code.ecommerce.entity.products;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private String id;
    private String category;
    private String type; // This can be 'Mobile', 'Laptop', 'Camera', 'Book', 'Chair', 'Table', 'Sofa', etc.
    private String title; // Used for books
    private String author; // Used for books
    private String isbn; // Used for books
    private String genre; // Used for books
    private String publicationDate; // Used for books
    private String brand; // Used for electronics
    private String model; // Used for electronics
    private Map<String, String> specifications; // Used for electronics and furniture
    private Map<String, String> dimensions; // Used for furniture
    private String material; // Used for furniture
    private String color; // Used for furniture
    private double price;
    private String image; // URL to the image
}
