package com.learnjava.api.productservices.service;
import com.learnjava.api.productservices.dto.ProductRequest;
import com.learnjava.api.productservices.dto.ProductResponse;
import com.learnjava.api.productservices.exception.ProductNotFoundException;
import com.learnjava.api.productservices.model.Product;
import com.learnjava.api.productservices.repository.ProductRepository;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    //@Value("https://dummyjson.com/products")
    //private String url;
   // public Object getAllProducts() {

        /*ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        Object products = response.getBody();
        return products;*/

       /* return restTemplate
                .getForObject(url, ProductResponse[].class);*/


   // }

    public void addProduct(ProductRequest productRequest) {
        /*Product product = Product.builder()
                .title(productRequest.getTitle())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .discountPercentage(productRequest.getDiscountPercentage())
                .rating(productRequest.getRating())
                .stock(productRequest.getStock())
                .brand(productRequest.getBrand())
                .category(productRequest.getCategory())
                .thumbnail(productRequest.getThumbnail())
                .images(productRequest.getImages().toArray(new String[0]))
                .build();*/

        Product product = modelMapper.map(productRequest, Product.class);
        productRepository.save(product);
        log.info("Hello");
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> prodList = productRepository.findAll();
        return prodList.stream().map(prod -> mapToProdDTO(prod)).toList();

    }

    @GetMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getProductById(@PathParam("id") int id) throws ProductNotFoundException {
        Optional<Product> data = productRepository.findById(id);
        if(data.isPresent()) {
            return new ResponseEntity<>(mapToProdDTO(data.get()), HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Given product code not found");
        }
    }

    private ProductResponse mapToProdDTO(Product prod) {
        //ProductResponse response;
        return modelMapper.map(prod, ProductResponse.class);
        /*return response= ProductResponse.builder()
                .id(prod.getId())
                .title(prod.getTitle())
                .description(prod.getDescription())
                .price(prod.getPrice())
                .discountPercentage(prod.getDiscountPercentage())
                .rating(prod.getRating())
                .stock(prod.getStock())
                .brand(prod.getBrand())
                .category(prod.getCategory())
                .thumbnail(prod.getThumbnail())
                .images(Arrays.asList(prod.getImages()))
                .build();*/
    }

    public ResponseEntity updateProductById(int id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        if(product != null) {
            product.setBrand(productRequest.getBrand());
            productRepository.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity deleteById(int id) {
        Product product = productRepository.findById(id).get();
        if(product != null) {
            productRepository.delete(product);
            return new ResponseEntity(HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
