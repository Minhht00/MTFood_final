package edu.itplus.crud.service;

import edu.itplus.crud.domain.Order;
import edu.itplus.crud.model.Amount;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface OrderService {


    //    @Override
//    @Query(value = "SELECT MONTH(o.order_date) as month,SUM(o.amount) as amount FROM orders o GROUP BY MONTH(o.order_date)", nativeQuery = true)
//    public List<Amount> getStatistic() {
//        List<Object> list = orderRepository.getStatistic();
//
//        List<Amount> listAmount = (List<Amount>) (Object) list;
//
//        return listAmount;
//    }

    @Query(value = "SELECT MONTH(o.order_date) as month,SUM(o.amount) as amount FROM orders o WHERE YEAR(o.order_date) = ?1 and status = 1  GROUP BY MONTH(o.order_date)", nativeQuery = true)
    List<Amount> getStatisticByYear(Long month);

    @Query(value="SELECT MONTH(o.order_date) as month,SUM(o.amount) as amount FROM orders o WHERE status = 1 GROUP BY MONTH(o.order_date)",nativeQuery=true)
    List<Amount> getStatistic();

    List<Order> findByOrderId(Long id);

    List<Order> findByCustomerId(Long id);

    List<Order> findAll();

    List<Order> findAll(Sort sort);

    List<Order> findAllById(Iterable<Long> longs);

    <S extends Order> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Order> S saveAndFlush(S entity);

    <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Order> entities);

    void deleteAllInBatch(Iterable<Order> entities);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    @Deprecated
    Order getOne(Long aLong);

    @Deprecated
    Order getById(Long aLong);

    Order getReferenceById(Long aLong);

    <S extends Order> List<S> findAll(Example<S> example);

    <S extends Order> List<S> findAll(Example<S> example, Sort sort);

    Page<Order> findAll(Pageable pageable);

    <S extends Order> S save(S entity);

    Optional<Order> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Order entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Order> entities);

    void deleteAll();

    <S extends Order> Optional<S> findOne(Example<S> example);

    <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Order> long count(Example<S> example);

    <S extends Order> boolean exists(Example<S> example);

    <S extends Order, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
