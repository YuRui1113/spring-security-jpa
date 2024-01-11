package com.taylor.springsecurityjpa;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.taylor.springsecurityjpa.config.security.SecurityConfig;
import com.taylor.springsecurityjpa.orm.entities.Role;
import com.taylor.springsecurityjpa.orm.entities.User;
import com.taylor.springsecurityjpa.orm.repositories.RoleRepository;
import com.taylor.springsecurityjpa.orm.repositories.UserRepository;

@SpringBootApplication
public class SpringSecurityJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringSecurityJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJpaApplication.class, args);

		System.out.println("startup");
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
		return (args) -> {
			// fetch all roles
			List<Role> roles = roleRepo.findAll();
			if (roles.size() == 0) {
				// No role
				log.info("Create two roles: USER, ADMIN");
				roleRepo.save(new Role(SecurityConfig.AUTHORITY_ADMIN));
				roleRepo.save(new Role(SecurityConfig.AUTHORITY_USER));
			}

			// fetch all users
			List<User> users = userRepo.findAll();
			if (users.size() == 0) {
				// No user
				log.info("Create three users: user1, user2, admin");

				Role roleAdmin = roleRepo.findByName(SecurityConfig.AUTHORITY_ADMIN).get();
				Role roleUser = roleRepo.findByName(SecurityConfig.AUTHORITY_USER).get();

				createUser(userRepo, passwordEncoder, "user1", "password",
						true, Arrays.asList(roleUser));

				createUser(userRepo, passwordEncoder, "user2", "password",
						true, Arrays.asList(roleUser, roleAdmin));

				createUser(userRepo, passwordEncoder, "admin", "password",
						true, Arrays.asList(roleAdmin));
			}

			log.info("");
		};
	}

	private User createUser(UserRepository userRepo, PasswordEncoder passwordEncoder, String name, String password,
			boolean isActive, List<Role> roles) {
		User user = new User(name, passwordEncoder.encode(password), isActive, roles);
		return userRepo.save(user);
	}
}