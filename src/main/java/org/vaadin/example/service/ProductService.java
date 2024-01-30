package org.vaadin.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.dto.Category;
import org.vaadin.example.dto.Product;
import org.vaadin.example.dto.Response;

import java.util.List;

@Service
@Slf4j
public class ProductService {


    RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> listOfProductByCategory() {

        List<Product> productList = null;
        Response<List<Product>> response = restTemplate.getForObject("http://localhost:8081/Product/allProduct", Response.class);
        log.info(String.valueOf(response.getResponseData() instanceof List<Product>));
        log.info(response.getResponseData().toString());

        ObjectMapper mapper = new ObjectMapper();
        List<Product> modelList = mapper.convertValue(response.getResponseData(), new TypeReference<List<Product>>() {
        });
        productList = modelList;

        return productList;
    }
}
