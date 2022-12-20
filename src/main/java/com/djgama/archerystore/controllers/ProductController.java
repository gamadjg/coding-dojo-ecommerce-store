package com.djgama.archerystore.controllers;

import com.djgama.archerystore.models.Product;
import com.djgama.archerystore.services.CategoryService;
import com.djgama.archerystore.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductService productServ;
	private final CategoryService categoryServ;

	public ProductController(ProductService productServ, CategoryService categoryServ) {
		this.productServ = productServ;
		this.categoryServ = categoryServ;
	}

	@GetMapping("/{id}")
	public String showProduct(Model model, @PathVariable("id") Long id){
		Product product = productServ.findOne(id);
		String[] strSplit = product.getDescription().split("/n");
		ArrayList<String> descList = new ArrayList<String>(Arrays.asList(strSplit));
//		descList.forEach((item) -> System.out.println(item));
		model.addAttribute("product", product);
		model.addAttribute("descriptionList", descList);
		return "show-product.jsp";
	}

	@GetMapping("")
	public String allProducts(Model model){
		model.addAttribute("products", productServ.findAll());
		model.addAttribute("categories", categoryServ.findAll());
		return "index.jsp";
	}

	@GetMapping("/bows")
	public String getBows(Model model){
		return null;
	}

	@GetMapping("/arrows")
	public String getArrows(Model model){
		return null;
	}

	@GetMapping("/targets")
	public String getTargets(Model model){
		return null;
	}

}
