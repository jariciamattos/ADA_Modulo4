package tech.ada.banco.enums;

public enum TipoContaEnum {
    CONTA_CORRENTE(1), INVESTIMENTO(2), POUPANCA(3);

    private final Integer id;

    TipoContaEnum(int i) {
        id = i;
    }
}
