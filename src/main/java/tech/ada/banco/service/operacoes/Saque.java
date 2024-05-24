package tech.ada.banco.service.operacoes;

import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

public class Saque implements OperacaoBancaria<Conta> {

	@Override
	public void realizarOperacao(Conta conta, BigDecimal valor) throws SaldoInsuficienteException{
		if (conta.getSaldo().compareTo(valor) >= 0) {
			 conta.setSaldo(conta.getSaldo().subtract(valor));
		} else {
			throw new SaldoInsuficienteException();
		}						
		
	}

}
