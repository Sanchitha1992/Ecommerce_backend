package com.learnjava.api.productservices.controller;

import com.learnjava.api.productservices.dto.ProductRequest;
import com.learnjava.api.productservices.dto.ProductResponse;
import com.learnjava.api.productservices.exception.ProductNotFoundException;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.learnjava.api.productservices.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products/")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") int id)
            throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProductById(@PathVariable("id") int id, @RequestBody ProductRequest productRequest) {
        return productService.updateProductById(id, productRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id) {
        return productService.deleteById(id);
    }
}
