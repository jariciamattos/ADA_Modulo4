package tech.ada.banco.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@DiscriminatorValue("1")
@Entity
@Getter
@Setter
public class ContaCorrente extends Conta {

    @Override
    public ContaCorrente clonar() {
        ContaCorrente novaConta = new ContaCorrente();
        novaConta.setId(this.getId());
        novaConta.setUuid(this.getUuid());
        novaConta.setDataCriacao(this.getDataCriacao());
        novaConta.setCliente(this.getCliente());
        novaConta.setSaldo(this.getSaldo());
        return novaConta;
    }
}
