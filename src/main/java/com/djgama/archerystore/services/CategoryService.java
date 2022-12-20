package com.djgama.archerystore.services;

import com.djgama.archerystore.models.Category;
import com.djgama.archerystore.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepo;

	public CategoryService(CategoryRepository categoryRepo){
		this.categoryRepo = categoryRepo;
	}

	public List<Category> findAll() {
		return (List<Category>) categoryRepo.findAll();
	}

	public Category findOne(Long id) {
		Optional<Category> category = categoryRepo.findById(id);
		return category.orElse(null);
	}

	public Category persistOne(Category category) {
		return categoryRepo.save(category);
	}

	public Category updateOne(Category category) {
		return categoryRepo.save(category);
	}

	public void deleteOne(Long id) {
		categoryRepo.deleteById(id);
	}

	public Category findOnebyName(String name) {
		return categoryRepo.findByName(name).orElse(null);
	}
}
