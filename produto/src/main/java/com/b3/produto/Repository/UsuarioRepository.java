package com.b3.produto.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.b3.produto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
