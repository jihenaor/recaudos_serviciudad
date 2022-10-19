package com.serviciudad.pojos;

public class Factura {
    
    private String IdFactura;
    private String Concepto;
    
    private Long TotalFactura;

    private Long saldo;
    
    private Long TotalIVA;
    private String FechaVencimiento;

    private Integer Orden;
    
    private String TipoIdCliente;
    
    private String IdCliente;
    
    private String Nombre;
    
    private String Apellido;
    
    private String Email;
    
    private String Telefono;
    
    private String CampoAdicional1;
    
    private String CampoAdicional2;
    
    private String CampoAdicional3;

    public Factura() {
        saldo = (long)0;
    }

    public String getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(String IdFactura) {
        this.IdFactura = IdFactura;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String Concepto) {
        this.Concepto = Concepto;
    }

    public Long getTotalFactura() {
        return TotalFactura;
    }

    public void setTotalFactura(Long TotalFactura) {
        this.TotalFactura = TotalFactura;
    }

    public Long getTotalIVA() {
        return TotalIVA;
    }

    public void setTotalIVA(Long TotalIVA) {
        this.TotalIVA = TotalIVA;
    }
/*
    public Long getSaldo() {
        return Saldo;
    }

    public void setSaldo(Long Saldo) {
        this.Saldo = Saldo;
    }
    */

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public Integer getOrden() {
        return Orden;
    }

    public void setOrden(Integer Orden) {
        this.Orden = Orden;
    }

    public String getTipoIdCliente() {
        return TipoIdCliente;
    }

    public void setTipoIdCliente(String TipoIdCliente) {
        this.TipoIdCliente = TipoIdCliente;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCampoAdicional1() {
        return CampoAdicional1;
    }

    public void setCampoAdicional1(String CampoAdicional1) {
        this.CampoAdicional1 = CampoAdicional1;
    }

    public String getCampoAdicional2() {
        return CampoAdicional2;
    }

    public void setCampoAdicional2(String CampoAdicional2) {
        this.CampoAdicional2 = CampoAdicional2;
    }

    public String getCampoAdicional3() {
        return CampoAdicional3;
    }

    public void setCampoAdicional3(String CampoAdicional3) {
        this.CampoAdicional3 = CampoAdicional3;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

}
