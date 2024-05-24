package tech.ada.banco.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@DiscriminatorValue("2")
public class ClientePJ extends Cliente{

    @Column(name="documento", unique = true)
    private final String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public BigDecimal getPercentualRendimento(){
        return new BigDecimal("1.02");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientePJ clientePJ = (ClientePJ) o;
        return Objects.equals(cnpj, clientePJ.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
