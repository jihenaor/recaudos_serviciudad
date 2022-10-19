/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recaudosfacade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class RespuestaListado {
    private Integer codigoRespuesta;
    
    private String comentario;
    
    private List<Recaudo> recaudos;

    public RespuestaListado() {
        recaudos = new ArrayList<>();
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

    public List<Recaudo> getRecaudos() {
        return recaudos;
    }

    public void setRecaudos(List<Recaudo> recaudos) {
        this.recaudos = recaudos;
    }
    
    
}
