package com.djgama.archerystore.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {
	private final ProductSeeder productSeeder;

	public MainSeeder(ProductSeeder productSeeder) {
		this.productSeeder = productSeeder;
	}

	@Override
	public void run(String... args) throws Exception {
		productSeeder.seed();
	}

}
