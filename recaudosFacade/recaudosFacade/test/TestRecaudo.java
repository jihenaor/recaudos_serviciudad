
import recaudosfacade.RecaudosFacade;
import recaudosfacade.Respuesta;

/**
 *
 * @author Administrador
 */
public class TestRecaudo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RecaudosFacade rf = RecaudosFacade.getInstance();
                
        Respuesta respuesta;

        Long numerofactura = (long)11083;
        Long cuenta = (long)805668;
        Long valor = (long)50000;
        String fecha = "2023-01-20 06:00:47.319";
        String tipo = "06";
        String token = "1";
        String banco = null;
        System.out.println("Cargar pago");
        System.out.println("Fact: " + numerofactura);
        System.out.println("Cuenta: " + cuenta);
        System.out.println("Valor: " + valor);

        respuesta = rf.registrarRecaudo(numerofactura, 
                cuenta, 
                valor, fecha, 
                "R", 
                token, 
                tipo, 
                banco,
                "x");
        
        System.out.println("Respuesta:" + respuesta.getCodigoRespuesta());
    }
    
}
