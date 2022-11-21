package edu.itplus.crud.service.impl;

import edu.itplus.crud.domain.Customer;
import edu.itplus.crud.repository.CustomerRepository;
import edu.itplus.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Override
//    public List<Customer> login(String email, String password){
//        List<Customer> lis
//        if (optExist.isPresent() && bCryptPasswordEncoder.matches(password,optExist.get().getPassword())){
//            optExist.get().setPassword("");
//            return optExist.get();
//        }
//        return null;
//    }

//    @Override
//    public List<Customer> findByEmail(String email) {
//        return customerRepository.findByEmailContaining(email);
//    }
//
//    @Override
//    public List<Customer> findByPassword(String password) {
//        return customerRepository.findByPassword(password);
//    }


    @Override
    public List<Customer> findByNameContaining(String name) {
        return customerRepository.findByNameContaining(name);
    }



    @Override
    public <S extends Customer> S save(S entity) {
        if (entity.getId() != null) {

            Optional<Customer> optExist = findById(entity.getId());

            if (optExist.isPresent()) {
                if (StringUtils.isEmpty(entity.getPassword())) {
                    entity.setPassword(optExist.get().getPassword());
                } else {
                    entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
                }
            } else {
                entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
            }
        }else {
            entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        }

        return customerRepository.save(entity);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return customerRepository.findAll(sort);
    }

    @Override
    public List<Customer> findAllById(Iterable<Long> longs) {
        return customerRepository.findAllById(longs);
    }

    @Override
    public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
        return customerRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        customerRepository.flush();
    }

    @Override
    public <S extends Customer> S saveAndFlush(S entity) {
        return customerRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
        return customerRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Customer> entities) {
        customerRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Customer> entities) {
        customerRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        customerRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        customerRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Customer getOne(Long aLong) {
        return customerRepository.getOne(aLong);
    }

    @Override
    @Deprecated
    public Customer getById(Long aLong) {
        return customerRepository.getById(aLong);
    }

    @Override
    public Customer getReferenceById(Long aLong) {
        return customerRepository.getReferenceById(aLong);
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example) {
        return customerRepository.findAll(example);
    }

    @Override
    public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
        return customerRepository.findAll(example, sort);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    @Override
    public Optional<Customer> findById(Long aLong) {
        return customerRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return customerRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        customerRepository.deleteById(aLong);
    }

    @Override
    public void delete(Customer entity) {
        customerRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        customerRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {
        customerRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    @Override
    public <S extends Customer> Optional<S> findOne(Example<S> example) {
        return customerRepository.findOne(example);
    }

    @Override
    public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return customerRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Customer> long count(Example<S> example) {
        return customerRepository.count(example);
    }

    @Override
    public <S extends Customer> boolean exists(Example<S> example) {
        return customerRepository.exists(example);
    }

    @Override
    public <S extends Customer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return customerRepository.findBy(example, queryFunction);
    }
}
