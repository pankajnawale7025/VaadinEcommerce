package org.vaadin.example.presenter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.example.dto.Response;
import org.vaadin.example.service.CustomerService;

@Service
public class LoginPresentor {

    @Autowired
    CustomerService customerService;


    public Response validateUSer(String emailAddress, String password) {
        Object object = customerService.validateUSer("http://localhost:8081/customer/validateCustomer?emailAddress=" + emailAddress + "&contactNumber=" + password);
        Response response = (Response) object;
        return response;
    }

}
