package org.vaadin.example.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.dto.Customer;
import org.vaadin.example.dto.Response;

@Service
@Slf4j
public class CustomerService {

    public final RestTemplate restTemplate;


    @Autowired

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Object validateUSer(String url) {
//        String urlWithParams = url + "?emailAddress=" + emailAddress + "&contactNumber=" + contactNumber;
        System.out.println("urlWith  Params===>" + url);
        System.out.println("hardcodedParams===>" + "http://localhost:8081/customer/validateCustomer?emailAddress=pankajnawale7025@gmail.com&contactNumber=8806444288");
        Response response = restTemplate.getForObject(url, Response.class);
        return response;
    }


    public Response addCustomer(Customer customer) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        String url = "http://localhost:8081/customer/addCustomer";
        // Make the POST request
        Response response = restTemplate.postForObject(url, request, Response.class);
        System.out.println("response in addCustomer is ===>" + response);
        return response;
    }

}
