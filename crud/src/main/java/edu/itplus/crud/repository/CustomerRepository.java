package edu.itplus.crud.repository;

import edu.itplus.crud.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByEmailContaining(String email);
    List<Customer> findByNameContaining(String name);
    List<Customer> findByEmail(String email);
    List<Customer> findByPassword(String password);

}
