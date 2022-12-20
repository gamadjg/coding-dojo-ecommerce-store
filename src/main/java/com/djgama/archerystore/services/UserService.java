package com.djgama.archerystore.services;

import com.djgama.archerystore.models.LoginUser;
import com.djgama.archerystore.models.User;
import com.djgama.archerystore.seeders.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
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
