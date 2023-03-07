package recaudosrest;

public class FacturaTipoRequest {
    private String codsuscrip;

    private String tipoFactura;

    private String numerofactura;
    
    private Long valor;
    
    public String getCodsuscrip() {
        return codsuscrip;
    }

    public void setCodsuscrip(String codsuscrip) {
        this.codsuscrip = codsuscrip;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
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
