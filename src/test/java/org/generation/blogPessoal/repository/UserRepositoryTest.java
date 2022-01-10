package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.UserModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class UserRepositoryTest {
	
	private @Autowired UserRepository repository;
	
	@BeforeAll
	void start() {
		
		//Given
		repository.save(new UserModel("Alan Boaz", "alan@email.com", "134652"));
		repository.save(new UserModel("Matheus Boaz", "matheus@email.com", "134652"));
		repository.save(new UserModel("Mariana Boaz", "mariana@email.com", "134652"));
		repository.save(new UserModel("Raphaella Boaz", "raphaella@email.com", "134652"));
		repository.save(new UserModel("Hercules Boaz", "hercules@email.com", "134652"));
		
	}
	
	@Test
	@DisplayName("Search valid email!")
	void searchEmailValidReturnObjectValid() {
		
		//When
		UserModel user = repository.findByEmail("raphaella@email.com").get();
		
		//Then
		assertTrue(user.getNome().equals("Raphaella Boaz"));
	}
	
	@Test
	@DisplayName("Search invalid email!")
	void searchEmailInvalidReturnOptionalEmpty() {
		
		//When
		Optional<UserModel> optional = repository.findByEmail("");
		
		//Then
		assertTrue(optional.isEmpty());
	}
	
	@Test
	@DisplayName("Search name Alan!")
	void searchFromBoazReturnFiveUsers() {
		
		//When
		List<UserModel> list = repository.findAllByNomeContainingIgnoreCase("Alan");
		
		//Then
		assertEquals(5, list.size());
	}
	
	@Test
	@DisplayName("Search name Murilo!")
	void searchFromHerculesReturnOneUser() {
		
		//When
		List<UserModel> list = repository.findAllByNomeContainingIgnoreCase("Murilo");
		
		//Then
		assertEquals(1, list.size());
	}

}
