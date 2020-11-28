package com.hellokoding.jpa.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void softDelete(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        customer.setDeleted(true);

        for(Card card : customer.getCards()) {
            card.setDeleted(true);
        }

        customerRepository.save(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    public void deleteByIdWithJPQL(Integer id) {
        customerRepository.deleteByIdWithJPQL(id);
    }

    public List<Customer> findTop10() {
        List<Customer> customerIds = customerRepository.findTop10By();

        return customerRepository.findAllByIdIsIn(
            customerIds.stream().map(Customer::getId).collect(Collectors.toList())
        );
    }
}
