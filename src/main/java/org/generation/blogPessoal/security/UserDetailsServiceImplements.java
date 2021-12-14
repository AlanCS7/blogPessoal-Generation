package org.generation.blogPessoal.security;

import java.util.Optional;

import org.generation.blogPessoal.model.UserModel;
import org.generation.blogPessoal.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserModel> optional = repository.findByEmail(username);

		if (optional.isPresent()) {
			return new UserDetailsImplements(optional.get());
		} else {
			throw new UsernameNotFoundException("Usuário não existe!");
		}

	}

}
