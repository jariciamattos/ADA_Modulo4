package tech.ada.banco.service.operacoes;


import tech.ada.banco.model.ContaInvestimento;

import java.math.BigDecimal;

public class DepositoInvertimento implements OperacaoBancaria<ContaInvestimento>, Rentabilidade {

	@Override
	public void realizarOperacao(ContaInvestimento conta, BigDecimal valor) {
		valor = conta.getSaldo().add(valor);
		var percentual= conta.getCliente().getPercentualRendimento();
		conta.setSaldo(render(valor, percentual));
	}

	@Override
	public BigDecimal render(BigDecimal valor, BigDecimal percentual) {
		return valor.multiply(percentual);
	}


}
