package tech.ada.banco.service.deposito;

import tech.ada.banco.dto.ContaDTO;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

public interface Deposito<T extends Conta> {

    void depositar(T conta, BigDecimal valor) ;
    /*
    default void depositar(T conta, BigDecimal valor) throws ValorInvalidoException {
        if(valor.compareTo(BigDecimal.ZERO)<1){
            throw new ValorInvalidoException("Valor menor que zero ou igual a zero");
        }
            conta.setSaldo(conta.getSaldo().add(valor));
    }

     */
}
