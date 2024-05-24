package tech.ada.banco.factory;

import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.ContaInvestimento;
import tech.ada.banco.service.operacoes.DepositoInvertimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class InvestimentoFactory implements ContaFactoryInterface<ContaInvestimento>{

    @Override
    public ContaInvestimento nova(BigDecimal saldo, Cliente cliente) {
        var conta = new ContaInvestimento();
        conta.setCliente(cliente);
        conta.setUuid(UUID.randomUUID());
        conta.setDataCriacao(LocalDate.now());
        conta.setSaldo(new BigDecimal("0"));
        DepositoInvertimento di = new DepositoInvertimento();
        di.realizarOperacao(conta, saldo);
        return conta;
    }
}
