package tech.ada.banco.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@DiscriminatorValue("2")
@Entity
@Getter
@Setter
public class ContaInvestimento extends Conta {

    @Override
    public ContaInvestimento clonar() {
        ContaInvestimento novaConta = new ContaInvestimento();
        novaConta.setId(this.getId());
        novaConta.setUuid(this.getUuid());
        novaConta.setDataCriacao(this.getDataCriacao());
        novaConta.setCliente(this.getCliente());
        novaConta.setSaldo(this.getSaldo());
        return novaConta;
    }

}
