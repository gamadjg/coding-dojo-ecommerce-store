package com.djgama.archerystore.services;

import com.djgama.archerystore.models.Category;
import com.djgama.archerystore.models.Product;
import com.djgama.archerystore.repositories.CategoryRepository;
import com.djgama.archerystore.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	private final ProductRepository productRepo;
	private final CategoryRepository categoryServ;

	public ProductService(ProductRepository productRepo, CategoryRepository categoryServ) {
		this.productRepo = productRepo;
		this.categoryServ = categoryServ;
	}

	public List<Product> findAll() {
		return (List<Product>) productRepo.findAll();
	}

	public List<Product> findByCategory(Category category){
		return productRepo.findAllByCategory_Id(category.getId());
	}

	public Product findOne(Long id) {
		Optional<Product> possibleProduct = productRepo.findById(id);
		if(!possibleProduct.isPresent()){
			return null;
		}else {
			Product product = possibleProduct.get();
			product.setDescription(refactorDescription(product.getDescription()));
			return product;
		}
	}

	public Product persistOne(Product product) {
		return productRepo.save(product);
	}

	public Product updateOne(Product product) {
		return productRepo.save(product);
	}

	public void deleteOne(Long id) {
		productRepo.deleteById(id);
	}

	public String refactorDescription(String originalDesc){
		String newDesc;
		if(originalDesc.charAt(0) == '"'){
			newDesc = originalDesc.substring(1, originalDesc.length()-1);
//			originalDesc = new String(originalDesc.substring(1, originalDesc.length()-1));
		}else{
			newDesc = originalDesc;
		}
		return newDesc
				.replace("\"\"", "\"")
				.replace("!!!", "/n")
				.replace("??", ",")
				.replace("##", ";");
	}
}
