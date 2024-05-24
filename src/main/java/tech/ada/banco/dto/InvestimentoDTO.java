package tech.ada.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvestimentoDTO {

    private UUID clienteUuid;

    @Positive(message = "Valor deve ser maior que zero")
    private BigDecimal valor;
}
