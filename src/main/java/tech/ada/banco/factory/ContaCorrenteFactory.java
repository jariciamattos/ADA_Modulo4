package tech.ada.banco.factory;

import tech.ada.banco.model.Cliente;

import tech.ada.banco.model.ContaCorrente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ContaCorrenteFactory implements ContaFactoryInterface<ContaCorrente>{

    @Override
    public ContaCorrente nova(BigDecimal saldo, Cliente cliente) {
        var conta = new ContaCorrente();
        conta.setCliente(cliente);
        conta.setUuid(UUID.randomUUID());
        conta.setSaldo(saldo);
        conta.setDataCriacao(LocalDate.now());
        return conta;
    }
}
