package tech.ada.banco.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferenciaDTO {

    @NotBlank(message = "UUID conta de origem obrigatória")
    private UUID contaOrigemUuid;

    @NotBlank(message = "UUID conta de destino obrigatória")
    private UUID contaDestinoUuid;

    @Positive(message = "Valor deve ser maior que zero")
    private BigDecimal valorOperacao;
}
