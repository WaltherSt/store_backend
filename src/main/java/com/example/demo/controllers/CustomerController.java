package com.example.demo.controllers;


import com.example.demo.dtos.customer.CustomerRequest;
import com.example.demo.models.Customer;
import com.example.demo.services.interfaces.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());  // Retorna 404 si no se encuentra el cliente
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Map<String,String>> createCustomer(@ModelAttribute CustomerRequest customerRequest) {

        Map<String,String> res = new HashMap<>();
        Map<String,String> err = new HashMap<>();

        try{
            this.customerService.saveCustomer(customerRequest);
            res.put("Message", "Customer created");
            return  ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
        catch (Exception e){
            err.put("Message", e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);  // Retorna el cliente actualizado con un estado 200 OK
        } else {
            return ResponseEntity.notFound().build();  // Retorna un 404 Not Found si no se encuentra el cliente
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);  // Intenta eliminar el cliente
            return ResponseEntity.noContent().build();  // Retorna 204 No Content si la eliminación fue exitosa
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();  // Retorna 404 Not Found si no se encuentra el cliente
        }
    }
}
