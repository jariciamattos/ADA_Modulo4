package tech.ada.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.banco.model.ClientePF;
import tech.ada.banco.model.ClientePJ;

import java.util.Optional;
import java.util.UUID;

public interface ClientePJRepository extends JpaRepository<ClientePJ, Long> {
    public  Optional<ClientePJ> findByUuid(UUID uuid);
    public  Optional<ClientePJ> findByCnpj(String cnpj);

}
