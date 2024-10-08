package vn.eledevo.vksbe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.eledevo.vksbe.dto.request.CustomerRequest;
import vn.eledevo.vksbe.dto.response.ApiResponse;
import vn.eledevo.vksbe.dto.response.CustomerResponse;
import vn.eledevo.vksbe.entity.Customer;
import vn.eledevo.vksbe.exception.ValidationException;
import vn.eledevo.vksbe.service.customer.CustomerService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getCustomer(
            @RequestBody Customer textSearch,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "3") int limitPage
            ){
        return ResponseEntity.ok(customerService.getCustomer(textSearch, sortField, sortDirection, currentPage, limitPage));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Customer>> addCustomer(@RequestBody CustomerRequest customerRequest) throws ValidationException {
        return ResponseEntity.ok(customerService.addCustomer(customerRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) throws ValidationException {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse<Customer>> deleteCustomer(@PathVariable Long id) throws ValidationException {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
