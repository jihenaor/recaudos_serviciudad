package recaudosrest;

import com.serviciudad.pojos.FacturaPagaResponse;
import com.serviciudad.pojos.Health;
import com.serviciudad.pojos.Movimientorecaudador;
import com.serviciudad.pojos.Respuestafactura;
import com.serviciudad.pojos.Respuestafacturas;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public Health getHealt(@PathParam("msg") String msg) {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        try {
            rf.consultarFacturas("808082");
        } catch (Exception ex) {
            return new Health("OK", "OK", ex.getMessage());
        }
        return new Health("OK", "OK", "");
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuestafactura consultarFactura(FacturaRequest facturaRequest) {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        return rf.consultarFactura(facturaRequest.getCodsuscrip(), "00", 0L, "0");
    }
    
    @POST
    @Path("/consultafacturas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuestafacturas consultarFacturas(FacturaRequest facturaRequest) throws Exception {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        try {
            return rf.consultarFacturas(facturaRequest.getCodsuscrip());
        } catch (Exception ex) {
            Logger.getLogger(ApiResource.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    @POST
    @Path("/consultafacturatipo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuestafactura consultarFacturaTipo(FacturaTipoRequest facturaTipoRequest) {
        RecaudosFacade rf = RecaudosFacade.getInstance();

        if (facturaTipoRequest.getCodsuscrip().equals("123")) {
            Respuestafactura respuestafactura = new Respuestafactura();
         
            if (!facturaTipoRequest.getNumerofactura().equals("271205563")
                    || facturaTipoRequest.getValor() != 20000
                    || Integer.parseInt(facturaTipoRequest.getTipoFactura()) != 4){
                            respuestafactura.setCodRespuesta(1);
                respuestafactura.setDescripcion("No se encontraron facturas" +
                                        " codsuscrip: " + facturaTipoRequest.getCodsuscrip() +                                             
                                        ". tipoFactura: " + facturaTipoRequest.getTipoFactura() +
                                        ". Valor " + facturaTipoRequest.getValor() +
                                        ". numeroFactura: " + facturaTipoRequest.getNumerofactura());
            } else {
                respuestafactura.setIdfactura("271205563");
                respuestafactura.setTipofact(4);    // Anticipo
                respuestafactura.setTotalfactura(Long.parseLong("20000"));

                respuestafactura.setCodRespuesta(0);
                respuestafactura.setDescripcion("0");
                respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
                respuestafactura.setFechapago("2023-03-30");
                respuestafactura.setAplicado("N");
                respuestafactura.setCiclo("64");
                respuestafactura.setFechaultimopago("");
            }
            return respuestafactura;
        }
        
        if (facturaTipoRequest.getCodsuscrip().equals("841102")) {
            Respuestafactura respuestafactura = new Respuestafactura();

            if (!facturaTipoRequest.getNumerofactura().equals("271201273")
                    || facturaTipoRequest.getValor() != 100819
                    || Integer.parseInt(facturaTipoRequest.getTipoFactura()) != 1){
                            respuestafactura.setCodRespuesta(1);
                respuestafactura.setDescripcion("No se encontraron facturas" +
                                        " codsuscrip: " + facturaTipoRequest.getCodsuscrip() +                                             
                                        ". tipoFactura: " + facturaTipoRequest.getTipoFactura() +
                                        ". Valor " + facturaTipoRequest.getValor() +
                                        ". numeroFactura: " + facturaTipoRequest.getNumerofactura());
            } else {
                respuestafactura.setIdfactura("271201273");
                respuestafactura.setTipofact(1);    // Cuota de financiacion
                respuestafactura.setTotalfactura(Long.parseLong("100819"));

                respuestafactura.setCodRespuesta(0);
                respuestafactura.setDescripcion("0");
                respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
                respuestafactura.setFechapago("2023-03-30");
                respuestafactura.setAplicado("N");
                respuestafactura.setCiclo("62");
                respuestafactura.setFechaultimopago("");
            }
            return respuestafactura;
        }
        
        if (facturaTipoRequest.getCodsuscrip().equals("819555")) {
            Respuestafactura respuestafactura = new Respuestafactura();

            if (!facturaTipoRequest.getNumerofactura().equals("271227495")
                    || facturaTipoRequest.getValor() != 58450
                    || Integer.parseInt(facturaTipoRequest.getTipoFactura()) != 0){
                            respuestafactura.setCodRespuesta(1);
                respuestafactura.setDescripcion("No se encontraron facturas" +
                                        " codsuscrip: " + facturaTipoRequest.getCodsuscrip() +                                             
                                        ". tipoFactura: " + facturaTipoRequest.getTipoFactura() +
                                        ". Valor " + facturaTipoRequest.getValor() +
                                        ". numeroFactura: " + facturaTipoRequest.getNumerofactura());
            } else {
                respuestafactura.setIdfactura("271227495");
                respuestafactura.setTipofact(0);    // Factura normal
                respuestafactura.setTotalfactura(Long.parseLong("58450"));

                respuestafactura.setCodRespuesta(0);
                respuestafactura.setDescripcion("0");
                respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
                respuestafactura.setFechapago("2023-03-30");
                respuestafactura.setAplicado("N");
                respuestafactura.setCiclo("64");
                respuestafactura.setFechaultimopago("");
            }
            return respuestafactura;
        }

        if (facturaTipoRequest.getCodsuscrip().equals("858356")) {
            Respuestafactura respuestafactura = new Respuestafactura();
            if (!facturaTipoRequest.getNumerofactura().equals("271229788")
                    || facturaTipoRequest.getValor() != 30000
                    || Integer.parseInt(facturaTipoRequest.getTipoFactura()) != 3){
                            respuestafactura.setCodRespuesta(1);
                respuestafactura.setDescripcion("No se encontraron facturas" +
                                        " codsuscrip: " + facturaTipoRequest.getCodsuscrip() +                                             
                                        ". tipoFactura: " + facturaTipoRequest.getTipoFactura() +
                                        ". Valor " + facturaTipoRequest.getValor() +
                                        ". numeroFactura: " + facturaTipoRequest.getNumerofactura());
            } else {
                respuestafactura.setIdfactura("271229788");
                respuestafactura.setTipofact(3);    // Pago parcial
                respuestafactura.setTotalfactura(Long.parseLong("30000"));

                respuestafactura.setCodRespuesta(0);
                respuestafactura.setDescripcion("0");
                respuestafactura.setCuenta(facturaTipoRequest.getCodsuscrip());
                respuestafactura.setFechapago("2023-03-30");
                respuestafactura.setAplicado("N");
                respuestafactura.setCiclo("64");
                respuestafactura.setFechaultimopago("");
            }
            return respuestafactura;
        }

        Respuestafactura respuestafactura = rf.consultarFactura(facturaTipoRequest.getCodsuscrip(), 
                facturaTipoRequest.getTipoFactura(), 
                facturaTipoRequest.getValor() == null ? 0L : facturaTipoRequest.getValor(),
                facturaTipoRequest.getNumerofactura());
        
        return respuestafactura;
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
        
        if (facturaTipoPagoRequest.getTotal() == 0) {
            return new Respuesta(Ambiente.REGISTROFALLO, "El valor de la factura es requerido", 0l);
        }

        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyyMMddhhmmss");
        String stringDate= DateFor.format(date);
        
        respuesta = rf.registrarRecaudo(
                Long.valueOf(facturaTipoPagoRequest.getNumerofactura()), 
                Long.valueOf(facturaTipoPagoRequest.getCodsuscrip()), 
                facturaTipoPagoRequest.getTotal(), 
                facturaTipoPagoRequest.getFecha(), 
                "R",
                "xx",
                facturaTipoPagoRequest.getTipoFactura(),
                facturaTipoPagoRequest.getBanco(), 
                stringDate);
       
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
