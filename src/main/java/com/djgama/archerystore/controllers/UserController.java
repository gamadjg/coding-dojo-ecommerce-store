package com.djgama.archerystore.controllers;

import com.djgama.archerystore.models.Cart;
import com.djgama.archerystore.models.LoginUser;
import com.djgama.archerystore.models.Product;
import com.djgama.archerystore.models.User;
import com.djgama.archerystore.services.CartService;
import com.djgama.archerystore.services.ProductService;
import com.djgama.archerystore.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/users")
public class UserController{
	private final UserService userServ;
	private final CartService cartServ;
	private final ProductService productServ;

	public UserController(UserService userServ, CartService cartServ, ProductService productServ) {
		this.userServ = userServ;
		this.cartServ = cartServ;
		this.productServ = productServ;
	}

	@GetMapping("/cart")
	public String shoppingCart(Model model, HttpSession session){
		User user = userServ.getUser((Long)session.getAttribute("user_id"));
		Cart cart = cartServ.findByUserId((Long)session.getAttribute("user_id"));
		for(Product item: cart.getProducts()){
			System.out.println(item.getName());
		}
		model.addAttribute("user",user);
		model.addAttribute("products", cart.getProducts());
		return "shoppingCart.jsp";
	}

	@GetMapping("/login/register")
	public String loginReg(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "loginReg.jsp";
	}

	@PostMapping("/login/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, HttpSession session, Model model) {
		// Check email
		if(userServ.getUser(newUser.getEmail()) != null) {
			result.rejectValue("email", "unique", "Email already exists.");
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "match", "Passwords do not match.");
		}
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "loginReg.jsp";
		}
		User user = userServ.registerUser(newUser);
		session.setAttribute("user_id", user.getId());
		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, HttpSession session, Model model) {
		User user = userServ.loginUser(newLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "loginReg.jsp";
		}
		session.setAttribute("user_id", user.getId());
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session){
		if(session.getAttribute("user_id") != null) {
			session.removeAttribute("user_id");
		}
		return "redirect:/users/login/register";
	}
}
