package com.djgama.archerystore.seeders;


import com.djgama.archerystore.models.Category;
import com.djgama.archerystore.models.Product;
import com.djgama.archerystore.services.CategoryService;
import com.djgama.archerystore.services.ProductService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class ProductSeeder {
	private final CategoryService categoryServ;
	private final ProductService productServ;

	public ProductSeeder(CategoryService categoryServ, ProductService productServ) {
		this.categoryServ = categoryServ;
		this.productServ = productServ;
	}


	public void seed(){
		if(!productServ.findAll().isEmpty()){
			return;
		}

		try(Scanner scan = new Scanner(new File("src/main/resources/db/products-main-2.txt"))){
			while(scan.hasNext()){
				String[] line = scan.nextLine().split(",");
				Category category = categoryServ.findOnebyName(line[2]);
				if(category == null){
					category = persistCategory(line[2]);
				}
				String name = line[0];
				String price = line[1];
				String desc = line[3];
				String imgLink = line[4];

				System.out.println(name);
				System.out.println(price);
				System.out.println(category.getName());
				System.out.println(desc);
				System.out.println(imgLink);

				persistProduct(line[0], line[1], category, line[3], line[4]);
			}
		}catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
	}

	private Category persistCategory(String name){
		Category newObject = new Category();
		newObject.setName(name);
		return categoryServ.persistOne(newObject);
	}

	private void persistProduct(String name, String price, Category category, String description, String imgLink){
		Product newObject = new Product();
		newObject.setName(name);
		newObject.setCategory(category);
		newObject.setPrice(Double.parseDouble(price));
		newObject.setDescription(description);
		newObject.setImgLink(imgLink);
		productServ.persistOne(newObject);
	}
}
