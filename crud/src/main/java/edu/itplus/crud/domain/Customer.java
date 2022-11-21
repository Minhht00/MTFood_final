package edu.itplus.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class Customer implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int customerId;
//    @Column(columnDefinition = "varchar(255) not null")
//    private String name;
//    @Column(columnDefinition = "varchar(100) not null")
//    private String email;
//    @Column(length = 20, nullable = false)
//    private String password;
//    @Column(length = 20)
//    private String phone;
//    @Temporal(TemporalType.DATE)
//    private Date registeredDate;
//    @Column(nullable = false)
//    private short stattus;
@Id
@Column
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String address;

//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    private Set<Order> order;

}
