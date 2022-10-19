/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recaudosfacade;

/**
 *
 * @author Administrador
 */
public class Respuesta {
    private Integer codigoRespuesta;
    
    private String comentario;
    
    private Long numerofactura;

    public Respuesta(Integer codigoRespuesta, String comentario, Long numerofactura) {
        this.codigoRespuesta = codigoRespuesta;
        this.comentario = comentario;
        this.numerofactura = numerofactura;
    }
    
    public Integer getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Integer codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(Long numerofactura) {
        this.numerofactura = numerofactura;
    }
    
    
}
