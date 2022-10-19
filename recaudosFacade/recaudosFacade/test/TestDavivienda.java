
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
public class TestDavivienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       RecaudosFacade rf = RecaudosFacade.getInstance();

       Consulta consulta = new Consulta();
        
       consulta.setIdFactura("");
       Respuestafactura respuestadavivienda = new Respuestafactura();
        
        respuestadavivienda = rf.consultarFactura("808495", "00");
    }
}


