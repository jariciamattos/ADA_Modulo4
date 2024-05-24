package tech.ada.banco.dto;

import lombok.*;
import tech.ada.banco.enums.TipoContaEnum;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContaDTO {

    private UUID clienteUuid;

    private UUID Uuid;

    @Positive(message = "Saldo deve ser maior que zero")
    private BigDecimal saldo;

    // private Cliente cliente;

}
