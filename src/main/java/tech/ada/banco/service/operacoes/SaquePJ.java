package tech.ada.banco.service.operacoes;

import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.model.Conta;
import java.math.BigDecimal;


public class SaquePJ extends Saque {
	
	static final BigDecimal taxaServico = new BigDecimal("1.005");
	
	@Override
	public void realizarOperacao(Conta conta, BigDecimal valor) throws SaldoInsuficienteException {

		BigDecimal valorSaque  = valor.multiply(taxaServico);
		super.realizarOperacao(conta, valorSaque); 		
	}

}
