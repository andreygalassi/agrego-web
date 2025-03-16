package br.com.agrego.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agrego.model.Usuario;
import br.com.agrego.repository.UsuarioRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepo.findByLogin(username);
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		return User
				.withUsername(usuario.get().getUsername())
				.password(usuario.get().getPassword())
				.roles(usuario.get().getPerfis().stream().map(r -> r.getNome()).toArray(String[]::new))
				.build();
	}
}
