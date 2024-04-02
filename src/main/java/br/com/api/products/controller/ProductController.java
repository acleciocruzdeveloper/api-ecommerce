package br.com.api.products.controller;

import br.com.api.products.domain.Products;
import br.com.api.products.enumerates.EProductTopic;
import br.com.api.products.service.ProductService;
import br.com.api.products.service.PublisherTopicProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/backend")
public class ProductController {

    private ProductService productService;
    private PublisherTopicProductService publisher;

    public ProductController(ProductService productService, PublisherTopicProductService publisher) {
        this.productService = productService;
        this.publisher = publisher;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Products>> findAll() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<Products> createProduct(@Valid @RequestBody Products data) {
        Products product = productService.createProduct(data);
        publisher.publisherProductEvent(product, EProductTopic.PRODUCT_CREATED, "admin");
        return ResponseEntity.ok(product);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable("id") long id, @RequestBody Products data) {
        Products products = productService.updateProduct(id, data);
        publisher.publisherProductEvent(products, EProductTopic.PRODUCT_UPDATE, "admin");
        return ResponseEntity.ok(products);
    }
}
