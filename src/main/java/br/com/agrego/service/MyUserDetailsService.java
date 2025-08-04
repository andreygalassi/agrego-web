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
import jakarta.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepo.findByLogin(username);
		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		Usuario u = usuario.get();
//		System.out.println(u);
//		System.out.println(u.getListaPerfil());
//		u.getListaPerfil().forEach(System.out::println);
//		u.getListaPerfil().forEach(x -> x.getListaAcesso().forEach(System.out::println));
//		u.getListaPerfil().forEach(x -> x.getListaRolesName().forEach(System.out::println));
		return User
				.withUsername(u.getUsername())
				.password(u.getPassword())
				.roles(u.getListaPerfil().stream().flatMap(r -> r.getListaRolesName().stream()).toArray(String[]::new))
				.build();
	}
}
