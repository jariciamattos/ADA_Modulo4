package tech.ada.banco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("3")
@Entity
@Getter
@Setter
@AllArgsConstructor
public class ContaPoupanca extends Conta {
    @Override
    public ContaPoupanca clonar() {
        ContaPoupanca novaConta = new ContaPoupanca();
        novaConta.setId(this.getId());
        novaConta.setUuid(this.getUuid());
        novaConta.setDataCriacao(this.getDataCriacao());
        novaConta.setCliente(this.getCliente());
        novaConta.setSaldo(this.getSaldo());
        return novaConta;
    }

}
