package tech.ada.banco.service.operacoes;

import java.math.BigDecimal;

public interface Rentabilidade {

    BigDecimal render(BigDecimal valor, BigDecimal percentual);
    
}
