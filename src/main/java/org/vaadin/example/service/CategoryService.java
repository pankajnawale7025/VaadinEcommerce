package org.vaadin.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.dto.Category;
import org.vaadin.example.dto.Response;

import java.util.List;

@Service
@Slf4j
public class CategoryService {
    public final RestTemplate restTemplate;

    @Autowired
    public CategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<Category> listOfProductByCategory() {

        List<Category> categoryList = null;
        Response<List<Category>> response = restTemplate.getForObject("http://localhost:8081/category/listOfCategory", Response.class);
        log.info(String.valueOf(response.getResponseData() instanceof List<Category>));
        log.info(response.getResponseData().toString());

        ObjectMapper mapper = new ObjectMapper();
        List<Category> modelList =
                mapper.convertValue(response.getResponseData(), new TypeReference<List<Category>>() {
                });
        categoryList = modelList;


        return categoryList;
    }
}