package tech.ada.banco.service.operacoes;

import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

public class DepositoConta implements OperacaoBancaria<Conta> {
	
	@Override
	public void realizarOperacao(Conta conta, BigDecimal valor) {
			conta.setSaldo(conta.getSaldo().add(valor));
	}

}
