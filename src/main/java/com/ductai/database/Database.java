package com.ductai.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ductai.repository.UserRepository;

@Configuration
public class Database {

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
//				UserEntity user2 = new UserEntity("Ramesh", "Fadatare", "ramesh@gmail.com");
//				UserEntity user3 = new UserEntity("Tom", "Cruise", "tom@gmail.com");
//				UserEntity user4 = new UserEntity("Tony", "Stark", "tony@gmail.com");
//				userRepository.save(user2);
//				userRepository.save(user3);
//				userRepository.save(user4);
			}
		};
	}
}
