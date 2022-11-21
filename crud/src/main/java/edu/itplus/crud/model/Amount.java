package edu.itplus.crud.model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;
import java.io.Serializable;

//public interface MyRepository extends JpaRepository<MyEntity, ID> {
//    @Query("SELECT new com.NewPojo(SUM(m.totalDays)) FROM MyEntity m")
//    NewPojo selectTotals();
//}
//
//class NewPojo {
//    Float days;
//    public NewPojo(Float days) {
//        this.days = days;
//    }
//}
//@NamedNativeQuery(
//        name = "edu.itplus.crud.model.Amount",
//        query = "SELECT MONTH(o.order_date),SUM(o.amount) FROM orders o GROUP BY MONTH(o.order_date)",
//        resultSetMapping = "mappinMyNativeQuery")   // must be the same name as in the SqlResultSetMapping declaration
//@SqlResultSetMapping(
//        name = "mapppinNativeQuery",  // same as resultSetMapping above in NativeQuery
//        classes = {
//                @ConstructorResult(
//                        targetClass = edu.itplus.crud.model.Amount.class,
//                        columns = {
//                                @ColumnResult( name = "month", type = Integer.class),
//                                @ColumnResult( name = "amount", type = Integer.class)
//                        }
//                )
//        }

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "amount")
public class Amount implements Serializable {
    @Id
    @Column
    private Long month;
    private int amount;

}
