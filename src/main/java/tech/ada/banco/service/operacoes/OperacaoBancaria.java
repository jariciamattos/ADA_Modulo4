package tech.ada.banco.service.operacoes;

import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

@FunctionalInterface
public interface OperacaoBancaria<C extends Conta> {
 
	void realizarOperacao(C conta, BigDecimal valor) throws SaldoInsuficienteException;
}
