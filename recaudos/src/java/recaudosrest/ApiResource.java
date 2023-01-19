package recaudosrest;

import com.serviciudad.pojos.FacturaPagaResponse;
import com.serviciudad.pojos.Movimientorecaudador;
import com.serviciudad.pojos.Respuestafactura;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import recaudosfacade.Ambiente;
import recaudosfacade.RecaudoException;
import recaudosfacade.RecaudosFacade;
import recaudosfacade.Respuesta;

/**
 * REST Web Service
 *
 * @author Administrador
 */
@Path("rec")
public class ApiResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
    }


    @GET
    @Path("/{msg}")
    @Produces(MediaType.APPLICATION_JSON)
    public String msg(@PathParam("msg") String msg) {
        
        return msg;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuestafactura consultarFactura(FacturaRequest facturaRequest) {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        return rf.consultarFactura(facturaRequest.getCodsuscrip(), "00");
    }
    
    @POST
    @Path("/consultafacturatipo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuestafactura consultarFacturaTipo(FacturaTipoRequest facturaTipoRequest) {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        if (facturaTipoRequest.getCodsuscrip().equals("123")) {
            Respuestafactura respuestafactura = new Respuestafactura();

            respuestafactura.setIdfactura("271205563");
            respuestafactura.setTipofact(4);    // Anticipo
            respuestafactura.setTotalfactura(Long.parseLong("20000"));

            respuestafactura.setCodRespuesta(0);
            respuestafactura.setDescripcion("0");
            respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
            respuestafactura.setFechapago("2022-12-30");
            respuestafactura.setAplicado("N");
            respuestafactura.setCiclo("64");
            respuestafactura.setFechaultimopago("");
            return respuestafactura;
        }
        
        if (facturaTipoRequest.getCodsuscrip().equals("841102")) {
            Respuestafactura respuestafactura = new Respuestafactura();

            respuestafactura.setIdfactura("271201273");
            respuestafactura.setTipofact(1);    // Cuota de financiacion
            respuestafactura.setTotalfactura(Long.parseLong("100819"));

            respuestafactura.setCodRespuesta(0);
            respuestafactura.setDescripcion("0");
            respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
            respuestafactura.setFechapago("2022-12-30");
            respuestafactura.setAplicado("N");
            respuestafactura.setCiclo("62");
            respuestafactura.setFechaultimopago("");
            return respuestafactura;
        }
        
        if (facturaTipoRequest.getCodsuscrip().equals("9579715600")) {
            Respuestafactura respuestafactura = new Respuestafactura();

            respuestafactura.setIdfactura("271176060");
            respuestafactura.setTipofact(9);    // Cuota de financiacion cobro coactivo
            respuestafactura.setTotalfactura(Long.parseLong("1732780"));

            respuestafactura.setCodRespuesta(0);
            respuestafactura.setDescripcion("0");
            respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
            respuestafactura.setFechapago("2022-12-30");
            respuestafactura.setAplicado("N");
            respuestafactura.setCiclo("64");
            respuestafactura.setFechaultimopago("");
            return respuestafactura;
        }

        if (facturaTipoRequest.getCodsuscrip().equals("819555")) {
            Respuestafactura respuestafactura = new Respuestafactura();

            respuestafactura.setIdfactura("271227495");
            respuestafactura.setTipofact(0);    // Factura normal
            respuestafactura.setTotalfactura(Long.parseLong("58450"));

            respuestafactura.setCodRespuesta(0);
            respuestafactura.setDescripcion("0");
            respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
            respuestafactura.setFechapago("2022-12-30");
            respuestafactura.setAplicado("N");
            respuestafactura.setCiclo("64");
            respuestafactura.setFechaultimopago("");
            return respuestafactura;
        }
                 
        if (facturaTipoRequest.getCodsuscrip().equals("858356")) {
            Respuestafactura respuestafactura = new Respuestafactura();

            respuestafactura.setIdfactura("271229788");
            respuestafactura.setTipofact(3);    // Pago parcial
            respuestafactura.setTotalfactura(Long.parseLong("30000"));

            respuestafactura.setCodRespuesta(0);
            respuestafactura.setDescripcion("0");
            respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
            respuestafactura.setFechapago("2022-12-30");
            respuestafactura.setAplicado("N");
            respuestafactura.setCiclo("64");
            respuestafactura.setFechaultimopago("");
            return respuestafactura;
        }
                
        return rf.consultarFactura(facturaTipoRequest.getCodsuscrip(), facturaTipoRequest.getTipoFactura());
    }

    @POST
    @Path("/pagarfactura")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta pagarfactura(FacturapagoRequest facturaRequest) {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        Respuesta respuesta;

        if (facturaRequest.getNumerofactura() == null) {
            return new Respuesta(Ambiente.REGISTROFALLO, "Numero de factura es nulo", 0l);
        }

        respuesta = rf.registrarRecaudo(
                Long.valueOf(facturaRequest.getNumerofactura()), 
                Long.valueOf(facturaRequest.getCodsuscrip()), 
                facturaRequest.getTotal(), 
                facturaRequest.getFecha(), 
                "R",
                "xx",
                "0",
                facturaRequest.getBanco(), 
                facturaRequest.getRequestid());

        return respuesta;
    }
    
    @POST
    @Path("/pagarfacturasinregistrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta pagarfacturasinregistrar(FacturapagoRequest facturaRequest) {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        Respuesta respuesta;

        if (facturaRequest.getNumerofactura() == null) {
            return new Respuesta(Ambiente.REGISTROFALLO, "Numero de factura es nulo", 0l);
        }

        if (!rf.existeRecaudoEnTemporal(facturaRequest.getCodsuscrip(), facturaRequest.getNumerofactura(), facturaRequest.getBanco()) &&
                !rf.existeRecaudoSicep(facturaRequest.getCodsuscrip(), "0", facturaRequest.getNumerofactura())) {
            respuesta = rf.registrarRecaudo(
                    Long.valueOf(facturaRequest.getNumerofactura()), 
                    Long.valueOf(facturaRequest.getCodsuscrip()), 
                    facturaRequest.getTotal(), 
                    facturaRequest.getFecha(), 
                    "R",
                    "xx",
                    "0",
                    facturaRequest.getBanco(), 
                    facturaRequest.getRequestid());
        } else {
            return new Respuesta(Ambiente.FACTURAREGISTRADA, "Factura registrada en temporal y/o Recaudo", 0l);
        }
        return respuesta;
    }
    
    @POST
    @Path("/pagarfacturatipo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta pagarfacturatipo(FacturaTipoPagoRequest facturaTipoPagoRequest) {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        Respuesta respuesta;

        if (facturaTipoPagoRequest.getNumerofactura() == null) {
            return new Respuesta(Ambiente.REGISTROFALLO, "Numero de factura es nulo", 0l);
        }

//        Date date = new Date();
//        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String stringDate= DateFor.format(date);
        
        respuesta = rf.registrarRecaudo(
                Long.valueOf(facturaTipoPagoRequest.getNumerofactura()), 
                Long.valueOf(facturaTipoPagoRequest.getCodsuscrip()), 
                facturaTipoPagoRequest.getTotal(), 
                facturaTipoPagoRequest.getFecha(), 
                "R",
                "xx",
                facturaTipoPagoRequest.getTipoFactura(),
                facturaTipoPagoRequest.getBanco(), 
                facturaTipoPagoRequest.getNumerofactura());

        return respuesta;
    }
    
    @GET
    @Path("/consulta/{cuenta}/{factura}/{banco}")
    @Produces(MediaType.APPLICATION_JSON)
    public String consulta(@PathParam("cuenta") String cuenta, 
                           @PathParam("factura") String factura,
                           @PathParam("banco") String banco) {
        RecaudosFacade rf = RecaudosFacade.getInstance();
        
        return rf.existeRecaudoEnTemporal(cuenta, factura, banco) ? "S" : "N";
    }
    
    @GET
    @Path("/consultarecaudosicep/{cuenta}/{tipofactura}/{nrofactura}")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarecaudosicep(@PathParam("cuenta") String cuenta, 
                           @PathParam("tipofactura") String tipofactura,
                           @PathParam("nrofactura") String nrofactura) {
        RecaudosFacade rf = RecaudosFacade.getInstance();
        
        return rf.existeRecaudoSicep(cuenta, tipofactura, nrofactura) ? "S" : "N";
    }

    
    @GET
    @Path("/consultamovimientofecha/{codigorecaudador}/{fecha}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimientorecaudador> consultamovimiento(@PathParam("codigorecaudador") String codigorecaudador, 
                           @PathParam("fecha") String fecha) {
        System.out.println("");
        RecaudosFacade rf = RecaudosFacade.getInstance();
        
        return rf.consultarmovimiento(fecha, codigorecaudador);
    }

    @POST
    @Path("/pagarfacturaoffline")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<FacturaPagaResponse> pagarfacturaofline(FacturapagoofflineRequest facturapagoofflineRequest) throws RecaudoException {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        return rf.procesarArchivoOfline(facturapagoofflineRequest.getTextofacturas(), "88");
    }
}
