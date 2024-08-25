package br.com.fiap.JavaCP1GerenciadorDeTarefas.repositories;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
}
