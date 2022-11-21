package edu.itplus.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "category_name", length = 100,
    columnDefinition = "varchar(255) not null")
    private String name;

    @Column(length = 200)
    private String image;

//    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
//    private Set<Product> products;
}
