package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Customer;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import br.com.luciano.beerstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid  Customer customer) {
        Customer customerSaved = this.customerService.save(customer);

        return ResponseEntity.created(LocationUtil.of(customerSaved.getId())).body(customerSaved);
    }



}
