package br.com.luciano.beerstore.resource;

import br.com.luciano.beerstore.model.Customer;
import br.com.luciano.beerstore.repository.CustomerRepository;
import br.com.luciano.beerstore.resource.util.LocationUtil;
import br.com.luciano.beerstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid  Customer customer) {
        Customer customerSaved = this.customerService.save(customer);

        return ResponseEntity.created(LocationUtil.of(customerSaved.getId())).body(customerSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Integer id, @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(id, customer));
    }

    @GetMapping
    public Page<Customer> search(@RequestParam(required = false, defaultValue = "%") String name, Pageable pageable) {
        return this.customerRepository.findByNameContaining(name, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.customerRepository.findById(id).orElse(new Customer()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        this.customerRepository.deleteById(id);
    }

}
