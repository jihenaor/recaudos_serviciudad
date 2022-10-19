package recaudosrest;

public class FacturaTipoPagoRequest {
    private String codsuscrip;

    private String numerofactura;
    
    private String tipoFactura;
    
    private String banco;
        
    private long total;
    
    private String fecha;

    public String getCodsuscrip() {
        return codsuscrip;
    }

    public void setCodsuscrip(String codsuscrip) {
        this.codsuscrip = codsuscrip;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    
}
