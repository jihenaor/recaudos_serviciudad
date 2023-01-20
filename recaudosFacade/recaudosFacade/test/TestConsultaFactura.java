
import com.serviciudad.pojos.Consulta;
import com.serviciudad.pojos.Respuestafactura;
import recaudosfacade.RecaudosFacade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class TestConsultaFactura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       RecaudosFacade rf = RecaudosFacade.getInstance();

       Consulta consulta = new Consulta();
        
       consulta.setIdFactura("");
       Respuestafactura respuestadavivienda;
        
//        respuestadavivienda = rf.consultarFactura("808495", "00", "", "");
//        respuestadavivienda = rf.consultarFactura("803047", "01", "141019", 32443l);
//        respuestadavivienda = rf.consultarFactura("9579377620", "02", "50000", 271366668L);
          respuestadavivienda = rf.consultarFactura("805668", "06", "50000", 11083L);

        System.out.println(respuestadavivienda.getCodRespuesta());
    }
}


