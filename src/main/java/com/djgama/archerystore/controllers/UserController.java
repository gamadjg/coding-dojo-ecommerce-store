package com.djgama.archerystore.controllers;

import com.djgama.archerystore.models.LoginUser;
import com.djgama.archerystore.models.User;
import com.djgama.archerystore.services.CartService;
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

@Controller
@RequestMapping("/users")
public class UserController{
	private final UserService userServ;
	private final CartService cartServ;


	public UserController(UserService userServ, CartService cartServ) {
		this.userServ = userServ;
		this.cartServ = cartServ;
	}

	@GetMapping("/cart")
	public String shoppingCart(){
		return "showProduct.jsp";
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
