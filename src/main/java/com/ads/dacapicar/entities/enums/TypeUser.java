package com.ads.dacapicar.entities.enums;

public enum TypeUser {

    NONE(0, "ROLE_NONE"),
    PRIVILEGE(1, "ROLE_PRIVILEGE");

    private final Integer cod;
    private final String description;

    TypeUser(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static TypeUser toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        // Laço para buscar o código correspondente
        for (TypeUser x : TypeUser.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        // Lançar exceção caso o código não seja encontrado
        throw new IllegalArgumentException("Tipo de usuário inválido: " + cod);
    }
}
