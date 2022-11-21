package edu.itplus.crud.repository;


import edu.itplus.crud.domain.Order;
import edu.itplus.crud.model.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartRepository extends JpaRepository<Amount,Long> {
//    EntityManager em = this.em;

//    SELECT MONTH(o.order_date),SUM(o.amount) FROM orders o GROUP BY MONTH(o.order_date)
//    @Query(value = " SELECT new edu.itplus.crud.model.Amount(MONTH(o.order_date),SUM(o.amount)) FROM orders o GROUP BY MONTH(o.order_date)",
//            nativeQuery = true)
//    @Query(value = " SELECT MONTH(o.order_date),SUM(o.amount) FROM orders o GROUP BY MONTH(o.order_date)",
//            nativeQuery = true)
//    SELECT SUM(amount) as doanh_thu FROM orders GROUP BY MONTH(order_date)
//        Query q = em.createNativeQuery("SELECT a.id, a.version, a.firstname, a.lastname FROM Author a", Author.class);
//    List<Author> authors = (List<Author>) q.getResultList();
    @Query(value="SELECT MONTH(o.order_date) as month,SUM(o.amount) as amount FROM orders o WHERE status = 1 GROUP BY MONTH(o.order_date)",nativeQuery=true)
    List<Amount> getStatistic();
    @Query(value="SELECT MONTH(o.order_date) as month,SUM(o.amount) as amount FROM orders o WHERE YEAR(o.order_date) = ?1 and status = 1  GROUP BY MONTH(o.order_date)",nativeQuery=true)
    List<Amount> getStatisticByYear(Long month);
}



//    SELECT  MONTH(o.order_date),SUM(o.amount) as doanh_thu FROM orders o GROUP BY MONTH(o.order_date)

