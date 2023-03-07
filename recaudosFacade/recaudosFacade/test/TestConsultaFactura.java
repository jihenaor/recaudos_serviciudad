
import com.serviciudad.pojos.Consulta;
import com.serviciudad.pojos.Respuestafactura;
import com.serviciudad.pojos.Respuestafacturas;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//       Respuestafactura respuestadavivienda;
        Respuestafacturas respuestafacturas;

            //        respuestadavivienda = rf.consultarFactura("808495", "00", "", "");
//        respuestadavivienda = rf.consultarFactura("9579714424", "01", 0L, null);
//        respuestadavivienda = rf.consultarFactura("9579096350", "03", 112950L, "271356293");
//        respuestadavivienda = rf.consultarFactura("9579377620", "02", "50000", 271366668L);
//          respuestadavivienda = rf.consultarFactura("805969", "06", null, null);
        try {
            respuestafacturas = rf.consultarFacturas("9579096350");
            System.out.println(respuestafacturas.getRespuestafacturas().size());
            respuestafacturas.getRespuestafacturas().forEach((t) -> {
                System.out.println("" + t.getFechapago());
            });
        } catch (Exception ex) {
            Logger.getLogger(TestConsultaFactura.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}


