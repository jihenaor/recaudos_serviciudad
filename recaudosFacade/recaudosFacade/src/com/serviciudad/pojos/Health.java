package com.serviciudad.pojos;

public class Health {
    public String estadoServidorRecaudos;

    public String estadoBaseDatosSicev;
    
    public String error;

    public Health(String estadoServidorExterno, String estadoBaseDatosSicev, String error) {
        this.estadoServidorRecaudos = estadoServidorExterno;
        this.estadoBaseDatosSicev = estadoBaseDatosSicev;
        this.error = error;
    }

    public String getEstadoServidorRecaudos() {
        return estadoServidorRecaudos;
    }

    public String getEstadoBaseDatosSicev() {
        return estadoBaseDatosSicev;
    }
}
