
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

        Long numerofactura = 271365363L;
        Long cuenta = 831088L;
        Long valor = (long)50700;
        String fecha = "2023-02-01 06:00:47.319";
        String tipo = "00";
        String token = "1";
        String banco = "88";
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
