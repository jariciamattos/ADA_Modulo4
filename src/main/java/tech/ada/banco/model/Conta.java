package tech.ada.banco.model;

import lombok.*;
import tech.ada.banco.enums.TipoContaEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta" , discriminatorType = DiscriminatorType.INTEGER)
@ToString
@NoArgsConstructor
public abstract class Conta implements Clonar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(columnDefinition = "UUID", nullable = false)
    private UUID uuid;

    @Column
    private BigDecimal saldo;

    @Column
    private LocalDate dataCriacao;

    @Column(name="tipo_conta", insertable = false, updatable = false)
    private TipoContaEnum tipoConta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
