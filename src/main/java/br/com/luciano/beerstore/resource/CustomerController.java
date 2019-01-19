package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Customer;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import br.com.luciano.beerstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Integer id, @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(id, customer));
    }

}
