package tech.ada.banco.dto;

import lombok.*;

@AllArgsConstructor
@Getter
public class RetornoDto {

    private  String mensagem;
    private  ContaDTO conta;

    public static RetornoDtoBuilder builder() {
        return new RetornoDtoBuilder();
    }

    public static class RetornoDtoBuilder {

        private String mensagem;
        private ContaDTO conta;

        public RetornoDtoBuilder mensagem(String mensagem) {
            this.mensagem = mensagem;
            return this;
        }

        public RetornoDtoBuilder conta(ContaDTO conta) {
            this.conta = conta;
            return this;
        }

        public RetornoDto build() {
            return new RetornoDto(mensagem, conta);
        }
    }
}

