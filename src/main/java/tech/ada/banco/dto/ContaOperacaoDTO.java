package tech.ada.banco.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public class ContaOperacaoDTO {

    @NotBlank(message = "UUID conta obrigatória")
    private UUID contaUuid;

    @Positive(message = "Valor  deve ser maior que zero")
    private BigDecimal valor;
}
