package com.djgama.archerystore.controllers;

import com.djgama.archerystore.models.Category;
import com.djgama.archerystore.models.LoginUser;
import com.djgama.archerystore.models.Product;
import com.djgama.archerystore.models.User;
import com.djgama.archerystore.services.CategoryService;
import com.djgama.archerystore.services.ProductService;
import com.djgama.archerystore.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductService productServ;
	private final CategoryService categoryServ;
	private final UserService userServ;

	public ProductController(ProductService productServ, CategoryService categoryServ, UserService userServ) {
		this.productServ = productServ;
		this.categoryServ = categoryServ;
		this.userServ = userServ;
	}
	@GetMapping("")
	public String allProducts(Model model, HttpSession session){
		User user = new User();
		if(session.getAttribute("user_id") != null) {
			user = userServ.getUser((Long)session.getAttribute("user_id"));
		}
		System.out.println(user.getName());
		model.addAttribute("user", user);
		model.addAttribute("products", productServ.findAll());
		model.addAttribute("categories", categoryServ.findAll());
		return "index.jsp";
	}

	@GetMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable("id") Long id){
		Product product = productServ.findOne(id);
		String[] strSplit = product.getDescription().split("/n");
		ArrayList<String> descList = new ArrayList<String>(Arrays.asList(strSplit));
		model.addAttribute("product", product);
		model.addAttribute("descriptionList", descList);
		model.addAttribute("newLogin", new LoginUser());
		return "showProduct.jsp";
	}


	@GetMapping("/{category}")
	public String getCategory(@PathVariable("category") String categoryName, Model model){
		Category category = categoryServ.findOnebyName(categoryName);
		model.addAttribute("products", productServ.findByCategory(category));
		return "showCategory.jsp";
	}
}
