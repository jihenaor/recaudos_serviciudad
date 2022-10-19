package com.serviciudad.pojos;

public class Movimientorecaudador {
    private String cuenta;
    private String numerofactura;
    private Long valor;

    public Movimientorecaudador(String cuenta, String numerofactura, Long valor) {
        this.cuenta = cuenta;
        this.numerofactura = numerofactura;
        this.valor = valor;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}
