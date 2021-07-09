package com.example.security.security_jpa;

import com.example.security.security_jpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;

@SpringBootApplication
public class SecurityJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SecurityJpaApplication.class, args);
	}

	@Autowired
	PersonRepository personRepository;



	@Value("${person.authority.admin}")
	String PERSON_ADMIN_AUTHORITY;

	@Value("${person.authority.user}")
	String PERSON_USER_AUTHORITY;


	@Override
	public void run(String... args) throws Exception {

		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();


		Person p1= Person.builder()
				.authorities(PERSON_USER_AUTHORITY)
				.username("saurav")
				.password(encoder.encode("saurav123"))
				.build();
		Person p2= Person.builder()
				.authorities(PERSON_USER_AUTHORITY+","+PERSON_ADMIN_AUTHORITY)
				.username("aman")
				.password(encoder.encode("aman123"))
				.build();
		personRepository.save(p1);
		personRepository.save(p2);
	}
}
