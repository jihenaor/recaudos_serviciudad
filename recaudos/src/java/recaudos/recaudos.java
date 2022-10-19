package recaudos;

import com.serviciudad.pojos.Respuestafactura;
import recaudosfacade.Ambiente;
import recaudosfacade.Respuesta;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import recaudosfacade.RecaudosFacade;
import recaudosfacade.RespuestaListado;

@WebService(serviceName = "recaudos")
public class recaudos {
     private static String CODIGOAPORTAR = "88";
    /**
     * This is a sample web service operation
     *
     * @param numerofactura
     * @param cuenta
     * @param valor
     * @param fecha
     * @param tipo(
     * @param token
     * @return
     */
     
    @WebMethod(operationName = "cargarpago")
    public Respuesta cargarpago(@WebParam(name = "numerofactura") Integer numerofactura,
            @WebParam(name = "cuenta") Long cuenta,
            @WebParam(name = "valor") Long valor,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "token") String token) {

        RecaudosFacade rf = RecaudosFacade.getInstance();
        Respuesta respuesta;

        if (numerofactura == null) {
            return new Respuesta(Ambiente.REGISTROFALLO, "Numero de factura es nulo", (long)numerofactura);
        }

        respuesta = rf.registrarRecaudo((long)numerofactura, cuenta, valor, fecha, "R", token, tipo, CODIGOAPORTAR, "x");

        return respuesta;
    }
    
    @WebMethod(operationName = "cargarpagobanco")
    public Respuesta cargarpagobanco(@WebParam(name = "numerofactura") Integer numerofactura,
            @WebParam(name = "cuenta") Long cuenta,
            @WebParam(name = "valor") Long valor,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "banco") String banco,
            @WebParam(name = "token") String token) {

        RecaudosFacade rf = RecaudosFacade.getInstance();
        Respuesta respuesta;

//        System.out.println("Cargar pago");
//        System.out.println("Fact: " + numerofactura);
//        System.out.println("Cuenta: " + cuenta);
//        System.out.println("Valor: " + valor);

        if (numerofactura == null) {
            return new Respuesta(Ambiente.REGISTROFALLO, "Numero de factura es nulo", (long)numerofactura);
        }
        respuesta = rf.registrarRecaudo((long)numerofactura, cuenta, valor, fecha, "R", token, tipo, banco, "");

        return respuesta;
    }
/*
    @WebMethod(operationName = "anularpago")
    public Respuesta anularpago(@WebParam(name = "numerofactura") Integer numerofactura,
            @WebParam(name = "cuenta") Long cuenta,
            @WebParam(name = "valor") Long valor,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "token") String token) {

        RecaudosFacade rf = RecaudosFacade.getInstance();
        
        return rf.anularpago(numerofactura, cuenta, valor, fecha, tipo, token, CODIGOAPORTAR);
    }
    
    @WebMethod(operationName = "anularpagobanco")
    public Respuesta anularpago(@WebParam(name = "numerofactura") Integer numerofactura,
            @WebParam(name = "cuenta") Long cuenta,
            @WebParam(name = "valor") Long valor,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "tipo") String tipo,
            @WebParam(name = "banco") String banco,
            @WebParam(name = "token") String token) {

        RecaudosFacade rf = RecaudosFacade.getInstance();
        
        return rf.anularpago(numerofactura, cuenta, valor, fecha, tipo, token, banco);
    }
  */  
    @WebMethod(operationName = "consultarmovimiento")
    public RespuestaListado consultarmovimiento(@WebParam(name = "fecha") String fecha) {
        RecaudosFacade rf = RecaudosFacade.getInstance();
        
        return rf.consultarmovimiento(fecha);
    }

    /**
     * Web service operation
     * @param idfactura
     * @param cuenta
     * @param tipofact
     * @param banco
     * @param token
     * @return 
     */
    @WebMethod(operationName = "consultafactura")
    public Respuestafactura consultafactura(
            @WebParam(name = "idfactura") String idfactura,
            @WebParam(name = "cuenta") String cuenta,
            @WebParam(name = "tipofact") Integer tipofact,
            @WebParam(name = "banco") Integer banco,
            @WebParam(name = "token") String token) {
         RecaudosFacade rf = RecaudosFacade.getInstance();

        if (idfactura == null){
            return null;
        }
        
       return rf.consultarFactura(idfactura, "00");
    }

    /**
     * Web service operation
     * @param cuenta
     * @return 
     */
    @WebMethod(operationName = "consultacuenta")
    public Respuestafactura consultacuenta(
            @WebParam(name = "cuenta") String cuenta) {
         RecaudosFacade rf = RecaudosFacade.getInstance();

        if (cuenta == null){
            return null;
        }
        if (cuenta.equals("810056") || 
                cuenta.equals("823533") || 
                cuenta.equals("827122") || cuenta.equals("834575") || cuenta.equals("838617") || 
                cuenta.equals("9578241246") || cuenta.equals("9579153700") || 
                cuenta.equals("9579371232") || cuenta.equals("9579701212") ) {
                     
            Respuestafactura respuestafactura = new Respuestafactura();
                respuestafactura.setIdfactura("123456");

                respuestafactura.setTotalfactura(Long.parseLong("30000"));

                respuestafactura.setCodRespuesta(0);
                respuestafactura.setDescripcion("0");
                respuestafactura.setCuenta(cuenta);
                respuestafactura.setFechapago("2022-08-30");
                respuestafactura.setAplicado("N");
            
                return respuestafactura;
        } else {
            return rf.consultarFactura(cuenta, "00");
        }
    }
}
