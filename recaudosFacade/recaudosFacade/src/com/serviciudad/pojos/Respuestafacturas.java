package com.serviciudad.pojos;

import java.util.List;

public class Respuestafacturas {
    private List<Respuestafactura> respuestafacturas;

    public List<Respuestafactura> getRespuestafacturas() {
        return respuestafacturas;
    }

    public Respuestafacturas(List<Respuestafactura> respuestafacturas) {
        this.respuestafacturas = respuestafacturas;
    }
}
