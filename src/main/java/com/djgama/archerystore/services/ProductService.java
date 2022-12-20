package com.djgama.archerystore.services;

import com.djgama.archerystore.models.Category;
import com.djgama.archerystore.models.Product;
import com.djgama.archerystore.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	private final ProductRepository productRepo;

	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	public List<Product> findAll() {
		return (List<Product>) productRepo.findAll();
	}

	public Product findOne(Long id) {
		Optional<Product> question = productRepo.findById(id);
		return question.orElse(null);
	}

	public Product persistOne(Product question) {
		return productRepo.save(question);
	}

	public Product updateOne(Product question) {
		return productRepo.save(question);
	}

	public void deleteOne(Long id) {
		productRepo.deleteById(id);
	}

}
