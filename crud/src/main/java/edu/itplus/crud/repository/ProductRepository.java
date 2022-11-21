package edu.itplus.crud.repository;

import edu.itplus.crud.domain.Category;
import edu.itplus.crud.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryId(long categoryId);
    List<Product> findAll();
    List<Product> findByName(String title);
}
