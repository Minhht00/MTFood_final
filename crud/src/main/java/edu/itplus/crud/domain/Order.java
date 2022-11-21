package edu.itplus.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    private Long orderId;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Column(nullable = false)
    private Long customerId;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private short status;
    @Column(nullable = false)
    private String address;

//    @ManyToOne
//    @JoinColumn(name = "customerId")
//    private  Customer customer;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private Set<OrderDetail> orderDetail;
}
