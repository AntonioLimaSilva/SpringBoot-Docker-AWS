package br.com.luciano.beerstore.service;

import br.com.luciano.beerstore.model.Customer;
import br.com.luciano.beerstore.repository.CustomerRepository;
import br.com.luciano.beerstore.service.exception.CustomerAlreadyExistException;
import br.com.luciano.beerstore.service.exception.CustomerNotExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(@Autowired CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        Optional<Customer> customerOptional = this.customerRepository.findByNameAndEmail(customer.getName(), customer.getEmail());

        if(customerOptional.isPresent()) {
            throw new CustomerAlreadyExistException();
        }

        return this.customerRepository.save(customer);
    }

    public Customer update(Integer id, Customer customer) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);

        if(!customerOptional.isPresent()) {
            throw new CustomerNotExistException();
        }

        Customer customerSave = customerOptional.get();

        BeanUtils.copyProperties(customer, customerSave, "id");

        return this.customerRepository.save(customerSave);
    }
}
