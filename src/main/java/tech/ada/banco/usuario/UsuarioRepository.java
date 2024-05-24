package tech.ada.banco.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByDocumento(String documento);

    Optional<Usuario> findByUsername(String username);
}
