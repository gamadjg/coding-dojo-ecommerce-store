package com.djgama.archerystore.services;

import com.djgama.archerystore.models.Cart;
import com.djgama.archerystore.models.LoginUser;
import com.djgama.archerystore.models.User;
import com.djgama.archerystore.repositories.CartRepository;
import com.djgama.archerystore.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepo;
	private final CartService cartServ;

	public UserService(UserRepository userRepo, CartService cartServ) {
		this.userRepo = userRepo;
		this.cartServ = cartServ;
	}

	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
		user.setPassword(hashed);
		User newUser = userRepo.save(user);
		Cart cart = new Cart();
		cart.setName("shopping");
		cart.setUser(getUser(newUser.getId()));
		cartServ.persistOne(cart);
		return newUser;
	}

	public User loginUser(LoginUser newLogin, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}
		User existingUser = getUser(newLogin.getEmail());
		if (existingUser == null) {
			result.rejectValue("email", "unique", "Email does not exist.");
		}

		if (!BCrypt.checkpw(newLogin.getPassword(), existingUser.getPassword())) {
			result.rejectValue("password", "match", "Password Incorrect.");
			return null;
		}
		return existingUser;
	}

	public User getUser(String email) {
		Optional<User> potentialUser = userRepo.findByEmail(email);
		return potentialUser.orElse(null);
	}

	public User getUser(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		return potentialUser.orElse(null);
	}
}
