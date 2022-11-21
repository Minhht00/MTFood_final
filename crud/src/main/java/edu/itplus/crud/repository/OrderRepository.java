package edu.itplus.crud.repository;


import edu.itplus.crud.domain.Order;
import edu.itplus.crud.model.Amount;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//    EntityManager em = this.em;

    List<Order> findByOrderId(Long id);
//    SELECT MONTH(o.order_date),SUM(o.amount) FROM orders o GROUP BY MONTH(o.order_date)
//    @Query(value = " SELECT new edu.itplus.crud.model.Amount(MONTH(o.order_date),SUM(o.amount)) FROM orders o GROUP BY MONTH(o.order_date)",
//            nativeQuery = true)
//    @Query(value = " SELECT MONTH(o.order_date),SUM(o.amount) FROM orders o GROUP BY MONTH(o.order_date)",
//            nativeQuery = true)
//    SELECT SUM(amount) as doanh_thu FROM orders GROUP BY MONTH(order_date)
//        Query q = em.createNativeQuery("SELECT a.id, a.version, a.firstname, a.lastname FROM Author a", Author.class);
//    List<Author> authors = (List<Author>) q.getResultList();
//    @Query(value="SELECT MONTH(o.order_date) as month,SUM(o.amount) as amount FROM orders o GROUP BY MONTH(o.order_date)",nativeQuery=true)
//    List<Object> getStatistic();
}



//    SELECT  MONTH(o.order_date),SUM(o.amount) as doanh_thu FROM orders o GROUP BY MONTH(o.order_date)

