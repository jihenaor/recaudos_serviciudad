package com.serviciudad.pojos;

public class Respuestafactura {
    private Integer CodRespuesta;
    private String Descripcion;
    private String idfactura;
    private String cuenta;
    private Integer tipofact;
    private Long totalfactura;
    private String fechapago;
    private String aplicado;
    private String fechaultimopago;
    private String ciclo;
    
    public Integer getCodRespuesta() {
        return CodRespuesta;
    }

    public void setCodRespuesta(Integer CodRespuesta) {
        this.CodRespuesta = CodRespuesta;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(String idfactura) {
        this.idfactura = idfactura;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getTipofact() {
        return tipofact;
    }

    public void setTipofact(Integer tipofact) {
        this.tipofact = tipofact;
    }

    public Long getTotalfactura() {
        return totalfactura;
    }

    public void setTotalfactura(Long totalfactura) {
        this.totalfactura = totalfactura;
    }

    public String getFechapago() {
        return fechapago;
    }

    public void setFechapago(String fechapago) {
        this.fechapago = fechapago;
    }

    public String getAplicado() {
        return aplicado;
    }

    public void setAplicado(String aplicado) {
        this.aplicado = aplicado;
    }

    public String getFechaultimopago() {
        return fechaultimopago;
    }

    public void setFechaultimopago(String fechaultimopago) {
        this.fechaultimopago = fechaultimopago;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
    
}
