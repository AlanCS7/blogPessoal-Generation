package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

	Optional<UserModel> findByToken(String token);

	Optional<UserModel> findByEmail(String email);

	List<UserModel> findAllByNomeContainingIgnoreCase(String name);
}
