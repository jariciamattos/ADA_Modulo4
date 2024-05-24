package tech.ada.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.banco.model.ClientePF;

import java.util.Optional;
import java.util.UUID;


public interface ClientePFRepository extends JpaRepository<ClientePF, Long> {
    public  Optional<ClientePF> findByUuid(UUID uuid);
    public  Optional<ClientePF> findByCpf(String cpf);

}
