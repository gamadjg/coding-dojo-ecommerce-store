package com.djgama.archerystore.repositories;

import com.djgama.archerystore.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findAllByCategory_Id(Long id);
	List<Product> findAll();
}
