package tech.ada.banco.factory;


import tech.ada.banco.enums.TipoOperacaoBancaria;
import tech.ada.banco.model.ClientePJ;
import tech.ada.banco.model.Conta;
import tech.ada.banco.model.ContaInvestimento;
import tech.ada.banco.service.operacoes.*;

public class OperacaoBancariaFactory {

	private static OperacaoBancariaFactory INSTANCE;

	private OperacaoBancariaFactory() {}

	public static OperacaoBancariaFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OperacaoBancariaFactory();
		}
		return INSTANCE;
	}


	public OperacaoBancaria getContaOperacoes(Conta conta, TipoOperacaoBancaria tipo) {
		if (tipo == TipoOperacaoBancaria.Deposito) {
			if (conta instanceof ContaInvestimento) { return new DepositoInvertimento();}
			return new DepositoConta();
		} else if (tipo == TipoOperacaoBancaria.Saque) {
			if (conta.getCliente() instanceof ClientePJ) {
				return new SaquePJ();
			}
			return new Saque();
		}
		return null;
	}

}
