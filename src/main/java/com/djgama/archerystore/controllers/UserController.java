package com.djgama.archerystore.controllers;

import com.djgama.archerystore.models.LoginUser;
import com.djgama.archerystore.models.User;
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

	public UserController(UserService userServ) {
		this.userServ = userServ;
	}

	@GetMapping("/register")
	public String register(Model model){
		model.addAttribute("newUser", new User());
		return "register.jsp";
	}

	@PostMapping("/register")
	public String processRegister(@Valid @ModelAttribute("newUSer") User newUser, BindingResult result, HttpSession session, Model model){
		// Check email
		if(userServ.getUser(newUser.getEmail()) != null) {
			result.rejectValue("email", "unique", "Email already exists.");
		}

		// Check passwords
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "match", "Passwords do not match.");
		}

		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "loginReg.jsp";
		}

		User user = userServ.registerUser(newUser);
		session.setAttribute("user_id", user.getId());
		return "redirect: /";
	}
}
