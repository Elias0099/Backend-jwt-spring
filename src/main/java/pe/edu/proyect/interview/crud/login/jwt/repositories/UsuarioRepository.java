package pe.edu.proyect.interview.crud.login.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.proyect.interview.crud.login.jwt.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);
}
