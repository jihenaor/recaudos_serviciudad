/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serviciudad.pojos;

/**
 *
 * @author Administrador
 */

public class Consulta {
    private String IdComercio;

    private String Password;

    private String IdFactura;

    private String IdCliente;

    private String ClaveConsulta;

    private String ForzarParamConsulta;

    public String getIdComercio() {
        return IdComercio;
    }

    public void setIdComercio(String IdComercio) {
        this.IdComercio = IdComercio;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(String IdFactura) {
        this.IdFactura = IdFactura;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getClaveConsulta() {
        return ClaveConsulta;
    }

    public void setClaveConsulta(String ClaveConsulta) {
        this.ClaveConsulta = ClaveConsulta;
    }

    public String getForzarParamConsulta() {
        return ForzarParamConsulta;
    }

    public void setForzarParamConsulta(String ForzarParamConsulta) {
        this.ForzarParamConsulta = ForzarParamConsulta;
    }
}