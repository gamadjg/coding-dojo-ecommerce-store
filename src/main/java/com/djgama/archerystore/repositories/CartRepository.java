package com.djgama.archerystore.repositories;

import com.djgama.archerystore.models.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
	Cart findByUser_Id(Long id);
	List<Cart> findAll();
}
