package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.CategoryProduct;

@Repository
public interface ICategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
 

}
