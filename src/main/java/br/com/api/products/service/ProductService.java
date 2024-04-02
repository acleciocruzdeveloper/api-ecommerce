package br.com.api.products.service;

import br.com.api.products.domain.Products;
import br.com.api.products.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;

    public ResponseEntity<List<Products>> getAllProducts() {
        log.info("::FIND_ALL_PRODUCTS::");
        List<Products> products = productsRepository.findAll();
        return ResponseEntity.ok(products);
    }

    public Products createProduct(Products data) {
        data.setCreatedAt(LocalDateTime.now());
        log.info("::PRODUCT_CREATED::{}", data.getCreatedAt());
        return productsRepository.save(data);
    }

    public Products updateProduct(long id, Products data) {
        if (productsRepository.existsById(id)) {
            data.setId(id);
            productsRepository.save(data);
        }
        return null;

    }
}
