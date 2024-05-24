package tech.ada.banco.factory;

import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

public interface ContaFactoryInterface<T extends Conta> {

     T nova(BigDecimal saldo, Cliente cliente);
}
