package com.djgama.archerystore.services;

import com.djgama.archerystore.models.Cart;
import com.djgama.archerystore.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
	private final CartRepository cartRepo;

	public CartService(CartRepository cartRepo) {
		this.cartRepo = cartRepo;
	}

	public List<Cart> findAll() {
		return cartRepo.findAll();
	}

	public Cart findOne(Long id) {
		Optional<Cart> possibleCart = cartRepo.findById(id);
		return possibleCart.orElse(null);
	}

	public Cart persistOne(Cart cart) {
		return cartRepo.save(cart);
	}

	public Cart updateOne(Cart cart) {
		return cartRepo.save(cart);
	}

	public void deleteOne(Long id) {
		cartRepo.deleteById(id);
	}

	public Cart findByUserId(Long id){
		return cartRepo.findByUser_Id(id);
	}
}
