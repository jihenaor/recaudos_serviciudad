/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviciudad.pojos;

import java.util.List;

/**
 *
 * @author Administrador
 */
public class Respuestafavivienda {
    private Integer CodRespuesta;
    
    private String Descripcion;

    private List<Factura> facturas;

    private String CodServicioPpal;
    
    private Integer CambiarClave;

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

    public String getCodServicioPpal() {
        return CodServicioPpal;
    }

    public void setCodServicioPpal(String CodServicioPpal) {
        this.CodServicioPpal = CodServicioPpal;
    }

    public Integer getCambiarClave() {
        return CambiarClave;
    }

    public void setCambiarClave(Integer CambiarClave) {
        this.CambiarClave = CambiarClave;
    } 

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
    
}
