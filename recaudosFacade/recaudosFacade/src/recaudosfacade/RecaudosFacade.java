package recaudosfacade;

//import com.serviciudad.pojos.FacturaPagaResponse;
import com.serviciudad.pojos.FacturaPagaResponse;
import com.serviciudad.pojos.Movimientorecaudador;
import com.serviciudad.pojos.Respuestafactura;
import com.serviciudad.pojos.Respuestafacturas;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecaudosFacade {
    private static RecaudosFacade single_instance = null;
    private static final String nombreDB = "RECAUDO.FDB";
    private final Mysql sql1;

    public String s;
 

    private RecaudosFacade()
    {
        sql1 = new Mysql(nombreDB);
    }
 
    // Static method
    // Static method to create instance of Singleton class
    public static RecaudosFacade getInstance()
    {
        if (single_instance == null)
            single_instance = new RecaudosFacade();
 
        return single_instance;
    }
    
    public Integer generarConsecutivo(Mysql conexion, Boolean error) throws Exception {
        Integer consecutivo = 0;        
        String sql;

        if (error) {
            sql = "Select max(id) from recaudo";

            try {
                ResultSet rs = conexion.ejecutarSQLSelect(sql);
                if (rs.next()) {
                    consecutivo = rs.getInt (1) + 1;
                    conexion.ejecutarSQL("UPDATE consec SET id = " + (consecutivo + 1));
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }            
        } else {
            sql = "Select id from consec";

            try {
                ResultSet rs = conexion.ejecutarSQLSelect(sql);
                if (rs.next()) {
                    consecutivo = rs.getInt (1);
                    conexion.ejecutarSQL("UPDATE consec SET id = id + 1");
                } else {
                    System.out.println("Sin consecutivo");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }            
        }

        return consecutivo;
    }
    
    public Respuesta insertar(Mysql sql1,
            Long numerofactura,
            Long cuenta,
            Long valor,
            String fecha,
            String tipo,
            String token,
            String tipoTransaccion,
            Boolean error,
            String banco,
            String requestid) throws Exception {
//        Integer consecutivo = 0;        
        String sql;
 
        UUID uuid = UUID.randomUUID();
        String llave = uuid.toString();

//        consecutivo = generarConsecutivo(sql1, error);
        
        if (tipoTransaccion == null){
            System.out.println("Tipo es null");
            tipoTransaccion = "0";
            }
        sql = "INSERT INTO recaudo (id, numerofactura, "
                + "cuenta, valor, fecharecaudo, tipotransaccion, "
                + "fecharegistro, tipo, requestid, banco";
        
        sql += ") "
                + "VALUES ('" + llave + "', " 
                + numerofactura + ", "
                + "'" + cuenta + "', " 
                + valor + ","
                + " '" + fecha + "', '" 
                + tipo + "', "
                + "CURRENT_TIMESTAMP, "
                + "'" + (new Integer(tipoTransaccion)).toString() + "',"
                + "'" +  requestid + "',"
                + "'" +  banco + "'";

        sql += ")";
//        System.out.println("" + sql);
        try {
            sql1.ejecutarSQL(sql);
            return new Respuesta(Ambiente.REGISTROEXITOSO, "Factura registrada con exito", numerofactura);
        } catch (Exception e) {
            System.out.println("Lleva duplicada " + sql);
            throw e;
        }
    }
    
    public boolean existeRecaudoEnTemporal(String cuenta, String numeroFactura, String banco) {
        String sql;

        sql = "Select cuenta, fecharegistro"
               + " from recaudo"
               + " where numerofactura = " + numeroFactura
               + " and cuenta = " + cuenta
               + " and banco = '" + banco + "'";

//            System.out.println("Ejecutando "+ sql);
        try {
            ResultSet rs = sql1.ejecutarSQLSelect(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existeRecaudoSicep(String cuenta, String tipofactura, String numeroFactura) {
        ConexionSicesp con = ConexionSicesp.getInstance();

        String sql;

        sql = "Select cod_suscrip"
               + " from recaudos"
               + " where cod_suscrip = '" + cuenta + "'"
               + " and tipo_factura = " + tipofactura
               + " and nro_factura = '" + numeroFactura +  "'"
               + " and aplicado = 'S'" ;

        try {
            ResultSet rs = con.ejecutarSQLSelect(sql);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Optional<Respuesta> validarFacturaTipo(String numerofactura,
            Long cuenta,
            Long valor,
            String tipoTransaccion,
            String banco,
            String fechaPagoReportadoBanco) throws ParseException {
/*
        if (cuenta == (long)123) {
            if (Integer.parseInt(tipoTransaccion) != 4) {
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el tipo de la ultima factura", (long)numerofactura));
            }

            if (valor != 20000) {
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el valor de la ultima factura", (long)numerofactura));
            } else {
                if (numerofactura != 271205563) {
                    return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide numero de la ultima factura", (long)numerofactura));
                } else {
                    return Optional.of(new Respuesta(Ambiente.REGISTROEXITOSO, "Factura registrada con exito", (long) numerofactura));
                }
            }
        }
*/
/*
        if (cuenta == (long)841102) {        
            if (Integer.parseInt(tipoTransaccion) != 1) {
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el tipo", (long)numerofactura));
            }
            
            if (valor != 100819) {
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el valor de la ultima factura", (long)numerofactura));
            } 

            if (numerofactura != 271201273) {
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide numero de la ultima factura", (long)numerofactura));
            }

            return Optional.of(new Respuesta(Ambiente.REGISTROEXITOSO, "Factura registrada con exito", (long) numerofactura));
        }
  */
/*
        if (cuenta.equals(new Long("9579715600"))) {
            if (Integer.parseInt(tipoTransaccion) != 9) {      // Cuota financiacion cobro coactivo
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el tipo", (long)numerofactura));
            }           
            
            if (valor != 1732780) {
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el valor de la ultima factura", (long)numerofactura));
            } else {
                if (numerofactura != 271176060) {
                    return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide numero de la ultima factura", (long)numerofactura));
                } else {
                    return Optional.of(new Respuesta(Ambiente.REGISTROEXITOSO, "Factura registrada con exito", (long) numerofactura));
                }
            }
        }
*/
/*
        if (cuenta.equals(new Long("819555"))) {        
            if (Integer.parseInt(tipoTransaccion) != 0) {      // Factura normal
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el tipo de la ultima factura", (long)numerofactura));
            }
            if (valor != 58450) {
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el valor de la ultima factura", (long)numerofactura));
            } else {
                if (numerofactura != 271227495) {
                    return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide numero de la ultima factura", (long)numerofactura));
                } else {
                    return Optional.of(new Respuesta(Ambiente.REGISTROEXITOSO, "Factura registrada con exito", (long) numerofactura));
                }
            }
        }
*/
/*
        if (cuenta.equals(new Long("858356"))) {
            if (Integer.parseInt(tipoTransaccion) != 3) {      // Pago parcial
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el tipo de la ultima factura", (long)numerofactura));
            }
            if (valor != 30000) {
                return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el valor de la ultima factura", (long)numerofactura));
            } else {
                if (numerofactura != 271229788) {
                    return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide numero de la ultima factura", (long)numerofactura));
                } else {
                    return Optional.of(new Respuesta(Ambiente.REGISTROEXITOSO, "Factura registrada con exito", (long) numerofactura));
                }
            }
        }
  */

//        if (Integer.parseInt(tipoTransaccion) == 0) {
/*
            if (cuenta == (long)810056 || cuenta == (long)823533 || cuenta == (long)827122 || cuenta == (long)834575 || cuenta == (long)838617 || 
                    cuenta == Long.parseLong("9578241246") || cuenta == Long.parseLong("9579153700") || 
                    cuenta == Long.parseLong("9579371232") || cuenta == Long.parseLong("9579701212") ) {


                if (cuenta == Long.parseLong("9579701212") ) {
                    return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "Supera Fecha limite de pago 2022-01-01", (long)numerofactura));
                } else {
                    if (valor != 30000) {
                        return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el valor de la ultima factura", (long)numerofactura));
                    } else {
                        if (numerofactura != 123456) {
                            return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide numero de la ultima factura", (long)numerofactura));
                        } else {
                            return Optional.of(new Respuesta(Ambiente.REGISTROEXITOSO, "Factura registrada con exito", (long) numerofactura));
                        }
                    }   
                }
            } else {
*/
            
        if (Integer.parseInt(tipoTransaccion) == 0) {
                Respuestafactura respuestafactura = consultarFactura(cuenta + "", 
                                                           tipoTransaccion, 
                                                           valor, 
                                                          numerofactura);

                
                if (respuestafactura.getCodRespuesta() == 0) {
                    if (respuestafactura.getFechapago() == null) {
                        System.out.println("La fecha de pago es null. Cuenta: " + cuenta);
                    } else {
                        DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechapago = (sourceFormat.parse(respuestafactura.getFechapago()));
                        
                        Format formatter = new SimpleDateFormat("yyyyMMdd");
                        String fechaPagoString = formatter.format(fechapago);
                        
//                        String fechaActualString = formatter.format(new Date());
                        String fechaPagoReportadoBancoString = fechaPagoReportadoBanco.substring(0, 10).replace("-", "").replace("/", "");

                        if (Integer.parseInt(fechaPagoReportadoBancoString) > Integer.parseInt(fechaPagoString)) {
                            System.out.println("Cuenta: " + numerofactura + " Fecha limite de pago: " + fechaPagoString + " Fecha de pago banco: " + fechaPagoReportadoBancoString);
                            return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "Supera Fecha limite de pago " + respuestafactura.getFechapago(), Long.parseLong(numerofactura)));
                         }

                        if (!Objects.equals(respuestafactura.getTotalfactura(), valor)) {
                            System.out.println("No coincide el valor. Cuenta: " + cuenta + "  Tipo: " + tipoTransaccion + ".  Servi: " + respuestafactura.getTotalfactura() + " Apo: " + valor);

                            return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide el valor de la ultima factura", Long.parseLong(numerofactura)));
                        }
                        if (!Objects.equals(respuestafactura.getIdfactura(), numerofactura + "")) {
                            System.out.println("No coincide el numero de la factura. Cuenta: " + cuenta + "  Tipo: " + tipoTransaccion + ".  Servi: " + respuestafactura.getTotalfactura() + " Apo: " + valor);

                            return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "No coincide numero de la ultima factura", Long.parseLong(numerofactura)));
                        }

                        if (respuestafactura.getAplicado() != null && respuestafactura.getAplicado().equals("S")) {
                            return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, "Factura pagada", Long.parseLong(numerofactura)));
                        }                        
                    }
                } else {
                    return Optional.of(new Respuesta(Ambiente.REGISTROFALLO, respuestafactura.getDescripcion(), Long.parseLong(numerofactura)));
                }
//            }
        }

        return Optional.empty();
    }

    public Respuesta registrarRecaudo(Long numerofactura,
            Long cuenta,
            Long valor,
            String fecha,
            String tipoEstadoTransaccion,  // R Registrando - A anulado
            String token,
            String tipoRecaudo,
            String banco,
            String requestid) {
        String sql;
        
        if (MyService.getInstance().existeLlave(numerofactura + tipoRecaudo)) {
            return new Respuesta(Ambiente.REGISTROFALLO, "La factura esta en proceso de registro", numerofactura);
        }
        
        tipoRecaudo = Integer.valueOf(tipoRecaudo) + "";
        try {
            Optional<Respuesta> respuesta = validarFacturaTipo(numerofactura.toString(), cuenta, valor, tipoRecaudo, banco, fecha);
            if (respuesta.isPresent()) {
                return respuesta.get();
            }            
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (tipoEstadoTransaccion.equals("R")){
            sql = "Select cuenta, fecharegistro"
                    + " from recaudo"
                    + " where numerofactura = " + numerofactura
                    + " and tipotransaccion = '" + tipoEstadoTransaccion + "'"
                    + " and tipo = '" + tipoRecaudo + "'"
                    + " and banco = '" + banco + "'";

            try {
                ResultSet rs = sql1.ejecutarSQLSelect(sql);
                if (rs.next()) {
                    Date fecharegistro = rs.getDate(2);
                    Date fechaActual = new Date();

                    if (tipoRecaudo.equals("0") || (Math.abs(fechaActual.getTime() - fecharegistro.getTime() ) / 1000) < 200) {
                        return new Respuesta(Ambiente.REGISTROFALLO, "Factura " + numerofactura +" ya ha sido registrada en menos de 200 segundos", numerofactura);
                    }

                    sql = "Select cuenta"
                    + " from recaudo"
                    + " where numerofactura = " + numerofactura
                    + " and tipotransaccion = 'A'"//Anulada
                    + " and tipo = '" + tipoRecaudo + "'"
                    + " and banco = '" + banco + "'";

                    ResultSet rs1 = sql1.ejecutarSQLSelect(sql);
                    if (rs1.next()) {
                        sql = "Select count(*)"
                            + " from recaudo"
                            + " where numerofactura = " + numerofactura
                            + " and tipotransaccion = '" + tipoEstadoTransaccion + "'"
                            + " and tipo = '" + tipoRecaudo + "'"
                            + " and banco = '" + banco + "'";

                        ResultSet rs2 = sql1.ejecutarSQLSelect(sql);
                        if (rs2.next()) {
                            if (rs2.getInt(1) > 1){
                                return new Respuesta(Ambiente.REGISTROFALLO, "Factura ya ha sido registrada, anulada y registrada", numerofactura);
                            }
                        }
                    }
                    else{
                        //Se va a verificar que el pago no se haga durante el mismo                         
                        Date fechaactual = new Date();

                        int dias = (int) ((fechaactual.getTime() - fecharegistro.getTime()) / 86400000);
                        if (dias == 0) {
                            System.out.println("Factura ya registrada en el mismo dia  numerofactura = " + numerofactura 
                                    + "  tipotransaccion = " + tipoEstadoTransaccion);
                           return new Respuesta(Ambiente.REGISTROFALLO, "Factura " + numerofactura +" ya ha sido registrada", numerofactura);
                        }
                        else {
                            System.out.println("Factura ya registrada en dias " + dias + " numerofactura = " + numerofactura + "  tipotransaccion = " + tipoEstadoTransaccion);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return new Respuesta(Ambiente.REGISTROFALLOINTERNO, "Fallo interno del sistema al consultar la factura", numerofactura);
            }
        }

        try {
            return insertar(sql1, 
                    numerofactura, 
                    cuenta, 
                    valor, 
                    fecha, 
                    tipoEstadoTransaccion, 
                    token, 
                    tipoRecaudo,
                    false,
                    banco,
                    requestid);
        } catch (Exception e) {

            e.printStackTrace();
            try {
                Date date = Calendar.getInstance().getTime();  
                DateFormat dateFormat = new SimpleDateFormat("yyyymmdd");  
                String strDate = dateFormat.format(date);  

                String textToAppend = "Cuenta: " + cuenta + " " + e.getMessage();

                    //Set true for append mode
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("c:/recaudo/error.txt", true));    

                writer.write(textToAppend);
                writer.close();                            
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
            return new Respuesta(Ambiente.REGISTROFALLOINTERNO, 
                    "Fallo interno del sistema al registrar recaudo", 
                    numerofactura);
        }
    }

    public Respuestafactura consultarFactura(String codsuscrip, 
                                             String tipoFactura, 
                                             Long valorAbono, 
                                             String numeroFactura){
        ConexionSicesp con = ConexionSicesp.getInstance();
        String sql = "";
        Respuestafactura respuestafactura = new Respuestafactura();

        tipoFactura = (Integer.valueOf(tipoFactura)) + "";
        
        if (tipoFactura.equals("0")) {
            sql = "select NRO_FACTURA, "
                    + " VR_FACTURA - VR_PAGADO AS VR_FACTURA, "
                    + "iif(EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, CICLO, VR_PAGADO, VR_FACTURA "
                    + " from(select a.COD_SUSCRIP, NRO_FACTURA, VR_PAGREAL VR_PAGADO_PERIODO, d.FECHA_PAGO, c.EDAD, c.VR_FACTURA, B.CICLO AS CICLO, a.VR_PAGADO "
                    + "        from SUSCRIPTORES a left outer join CICLOS b on a.CICLO = b.CICLO left "
                    + "                outer join FACTURAS c on a.COD_SUSCRIP = c.COD_SUSCRIP " 
                    + "                   and ANO_FACT = ULT_ANO_FACT and MES_FACT = ULT_MES_FACT "
                    + "                       left outer join PERIODOS_FACT d on c.CICLO = d.CICLO " 
                    + "             and c.ANO_FACT = d.ANO_FACT and c.MES_FACT = d.MES_FACT"
                    + " where a.COD_SUSCRIP = " + codsuscrip + ")";
        }
        else {
            // 1 (pago cuota inicial financiación):
            if (tipoFactura.equals("1")) {
//                sql = "select first 1 a.NRO_FACTURA, VR_FACTURA, "
                sql = "select first 1 NRO_FINANC, VR_CUOTA_INI, "
                        + "iif(b.EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, "
                        + "b.CICLO, "
                        + "b.EST_FINANC"
                      + " from FINANCENC a"
                      + " left outer join FACTURAS b on a.COD_SUSCRIP = b.COD_SUSCRIP and a.NRO_FACTURA = b.NRO_FACTURA"
                      + " left outer join CICLOS c on b.CICLO = c.CICLO"
                      + " left outer join PERIODOS_FACT d on b.CICLO = d.CICLO and b.ANO_FACT = d.ANO_FACT and b.MES_FACT = d.MES_FACT"
                      + " where a.COD_SUSCRIP = " + codsuscrip;
                if (numeroFactura != null && numeroFactura.length() > 2){
                    sql += " and NRO_FINANC = " + numeroFactura 
                         + " and VR_CUOTA_INI = " + valorAbono;
                }
                      
                sql += " order by a.NRO_FACTURA desc" ;
            }
            else {
                // Consulta para tipos de factura 2 (abono por reclamo), 3 (pago parcial), 4 (pago anticipado) y 8 (cobro reorganizacion empresarial):

                if (tipoFactura.equals("2") || tipoFactura.equals("3") || tipoFactura.equals("4") || tipoFactura.equals("8")) {
                    
//                    sql = "select first 1 a.NRO_FACTURA, VR_FACTURA, iif(b.EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, b.CICLO"
                    sql = "select first 1 a.NRO_FACTURA, VR_ABONO, iif(b.EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, b.CICLO"
                        + " from PAGPARCENC a "
                        + " left outer join FACTURAS b on a.COD_SUSCRIP = b.COD_SUSCRIP and a.NRO_FACTURA = b.NRO_FACTURA "
                        + " left outer join CICLOS c on b.CICLO = c.CICLO "
                        + " left outer join PERIODOS_FACT d on b.CICLO = d.CICLO and b.ANO_FACT = d.ANO_FACT and b.MES_FACT = d.MES_FACT "
                        + " where a.COD_SUSCRIP = " + codsuscrip 
                                + " and a.TIPO_FACT = '" + tipoFactura + "'";
/*                    if (numeroFactura.length() > 1) {
                        sql += " and a.NRO_FACTURA = " + numeroFactura 
                        + " and VR_ABONO = " + valorAbono;
                    }*/
                    sql += " order by a.NRO_FACTURA desc";
                }
                else {
                    if (tipoFactura.equals("6")) {
                        sql = "select first 1 N_PAGO_FINANC, VR_PAGO, iif(e.EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, b.CICLO, a.ESTADO "
                            + " from PAGOS_FINANC a "
                            + " left outer join SUSCRIPTORES b on a.COD_SUSCRIP = b.COD_SUSCRIP "
                            + " left outer join CICLOS c on b.CICLO = c.CICLO "
                            + " left outer join PERIODOS_FACT d on c.CICLO = d.CICLO and ULT_ANO_FACT = d.ANO_FACT and ULT_MES_FACT = d.MES_FACT "
                            + " left outer join FACTURAS e on a.COD_SUSCRIP = e.COD_SUSCRIP and d.ANO_FACT = e.ANO_FACT and d.MES_FACT = e.MES_FACT "
                            + " where a.COD_SUSCRIP = " + codsuscrip + " and a.ESTADO = 'PE'";
                            if (numeroFactura != null && numeroFactura.length() > 1) {
//                                + " and N_PAGO_FINANC = " + numeroFactura 
//                                + " and VR_PAGO =" + valorAbono;
                            }
                        sql += " order by e.NRO_FACTURA desc";

                    }
                }
            }
        }
        
        try {
            ResultSet rs = con.ejecutarSQLSelect(sql);
            if (rs.next()) {
                respuestafactura.setIdfactura(rs.getLong(1) + "");

                respuestafactura.setCodRespuesta(0);
                respuestafactura.setDescripcion("0");
                respuestafactura.setCuenta(codsuscrip);
                respuestafactura.setTipofact((Integer.valueOf(tipoFactura)));
                DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                Date fechapago = new Date((sourceFormat.parse(rs.getString(3))).getTime() + (1000 * 60 * 60 * 24) * 15);

                respuestafactura.setFechapago(sourceFormat.format(fechapago));
                
                respuestafactura.setCiclo(rs.getString(4));
                respuestafactura.setAplicado("N");

                Long valorFactura = rs.getLong(2);
                respuestafactura.setTotalfactura(valorFactura);
                    
                
                if (respuestafactura.getFechapago() != null && respuestafactura.getFechapago().length() == 10){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Integer fechaPago = Integer.parseInt(respuestafactura.getFechapago().replace("-", ""));
                    Integer fechaActual = Integer.parseInt(dateFormat.format(new Date()));

                    respuestafactura.setFacturavencida(fechaActual > fechaPago ? "S" : "N");
                } else {
                    respuestafactura.setFacturavencida("N");
                }        
                
                if (tipoFactura.equals("0")) {
                    
                    respuestafactura.setValorPagado(rs.getLong(5));
                    respuestafactura.setValorFacturaSinPagos(rs.getLong(6));

                    respuestafactura.setAplicado(valorFactura > 0 ? "N": "S");

                } else {
                    if (tipoFactura.equals("1")) {  // Pago cuota inicial de financiacion
                        String estadoFinanciacion = rs.getString(5);
                        
                        switch (estadoFinanciacion) {
                            case "PP":
                                respuestafactura.setTotalfactura(rs.getLong(2));
                                break;
                            case "VG":
                                respuestafactura.setCodRespuesta(1);
                                respuestafactura.setDescripcion("Ya pagó la cuota inicial");
                                respuestafactura.setAplicado("S");
                            case "A4":
                                respuestafactura.setCodRespuesta(1);
                                respuestafactura.setDescripcion("financiación anulada");                                
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                    else {
                        if (tipoFactura.equals("6")) {  // Abono financiacion
                            String estadoFinanciacion = rs.getString(5);
                    
                            if (estadoFinanciacion.equals("PE")) {

                            } else {
                                respuestafactura.setCodRespuesta(1);

                                if (estadoFinanciacion.equals("VG")) {
                                    respuestafactura.setDescripcion("Ya pagó el abono");                                
                                } else {
                                    if (estadoFinanciacion.equals("AN")) {
                                        respuestafactura.setDescripcion("Abono anulada");                                
                                    }   
                                }
                            }
                        }
                    }
                }
            }
            else {
                respuestafactura.setCodRespuesta(1);
                respuestafactura.setDescripcion("No se encontraron facturas" +
                                        " codsuscrip: " + codsuscrip +                                             
                                        ". tipoFactura: " + tipoFactura +
                                        ". Valor " + valorAbono +
                                        ". numeroFactura: " + numeroFactura);
            }
        } catch (Exception e) {
            e.printStackTrace();
            respuestafactura.setCodRespuesta(9);
            respuestafactura.setDescripcion(e.toString());
        }

        return respuestafactura;
    }
    
    public String consultarPagoUltimaFactura(String codsuscrip) throws SQLException{
        ConexionSicesp con = ConexionSicesp.getInstance();
        String sql;
        String respuesta;
        sql = "select APLICADO APLICADO_DEFINITIVO " +
            " from" +
            " (" +
            " select a.COD_SUSCRIP, NRO_FACTURA from SUSCRIPTORES a" +
            " left outer join CICLOS b on a.CICLO = b.CICLO" +
            " left outer join FACTURAS c on a.COD_SUSCRIP = c.COD_SUSCRIP and ANO_FACT = ULT_ANO_FACT and MES_FACT = ULT_MES_FACT " +
            " where a.COD_SUSCRIP = " + codsuscrip + 
            " ) x left outer join RECAUDOS y on x.COD_SUSCRIP = y.COD_SUSCRIP and x.NRO_FACTURA = y.NRO_FACTURA ";

        ResultSet rs = con.ejecutarSQLSelect(sql);
        try {
            if (rs.next()) {
                respuesta = rs.getString(1);
            }
            else {
                respuesta = "N";
            }
        } catch(Exception e) {
            System.out.println("Consultando ultima factura " + codsuscrip);
            e.printStackTrace();
            throw e;
        }
        return respuesta == null ? "N" : respuesta;
    }

    public Respuesta anularpago(Integer numerofactura,
            Long cuenta,
            Long valor,
            String fecha,
            String tipo,
            String token,
            String banco) {

//        Recaudo_facade recaudo_facade = new Recaudo_facade();
        Mysql sql1 = new Mysql("RECAUDO.FDB");
        String sql;

        Respuesta respuesta;

        sql = "Select cuenta from recaudo where numerofactura = " + numerofactura
                + " and tipotransaccion = 'R'";//Recaudo

        try {
            ResultSet rs = sql1.ejecutarSQLSelect(sql);
            if (!rs.next()) {
                return new Respuesta(Ambiente.FACTURANOREGISTRADA, "numero de factura no existe : " + numerofactura, 
                        (long)numerofactura);
            } else {
                try {
                    respuesta = registrarRecaudo((long)numerofactura, 
                            cuenta, valor, fecha, "A", token, 
                            tipo, banco, "x");
                    
                    sql = "Update recaudo set tipo = '1' where numerofactura = " + numerofactura
                            + " and tipotransaccion = 'R'";//Recaudo
                    System.out.println("" + sql);
                    try {
                        sql1.ejecutarSQL(sql);
                    } catch (Exception e) {
                        System.out.println("Error actualizando");
                        e.printStackTrace();
                    }
                    
                    
                    return new Respuesta(Ambiente.ANULACIONEXITOSO, "ANULACION EXITOSA", (long)numerofactura);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Respuesta(Ambiente.REGISTROFALLOINTERNO, 
                            "Fallo interno del sistema al anular la factura", 
                            (long)numerofactura);
                }                
            }
        } catch (SQLException ex) {
            return new Respuesta(Ambiente.REGISTROFALLOINTERNO, "Fallo interno del sistema al insertar la factura", 
                    (long)numerofactura);
        } catch (Exception e) {
            e.printStackTrace();
            return new Respuesta(Ambiente.REGISTROFALLOINTERNO, "Fallo interno del sistema al insertar la factura", 
                    (long)numerofactura);
        }
    }

    public RespuestaListado consultarmovimiento(String fecha) {
        String sql;
        RespuestaListado rl = new RespuestaListado();
        ResultSet rs;
        int contador = 0;
    
        sql = "SELECT numerofactura FROM RECAUDO where fecharecaudo like '" + fecha + "%';";
        
        try {
            rs = sql1.ejecutarSQLSelect(sql);
        } catch (Exception e) {
            rl.setCodigoRespuesta(0);
            rl.setComentario("Error internos");
            return rl;
        }
        
        try {
            while (rs.next()){
                Recaudo r = new Recaudo();
                
                r.setNumeroFactura(rs.getInt(1));
                
                rl.getRecaudos().add(r);
                contador++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecaudosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        rl.setCodigoRespuesta(1);
        rl.setComentario("Registros: " + contador);
        return rl;
    }

    public List<Movimientorecaudador> consultarmovimiento(String fechaRecaudo, String codigoRecaudado) {
        String sql;
        ResultSet rs;
    
        List<Movimientorecaudador> movimientorecaudadors = new ArrayList<>();
        sql = "select cuenta, numerofactura, valor " +
            " from recaudo " +
            " where  extract(year from fecharecaudo) ||substring(100+extract(month from fecharecaudo) from 2 for 2)||substring(100+extract(day from fecharecaudo) from 2 for 2) = '" + fechaRecaudo + "'" +
            " and banco = '" + codigoRecaudado + "'";
        
        rs = sql1.ejecutarSQLSelect(sql);
        
        DecimalFormat df = new DecimalFormat("0");

        
        try {
            while (rs.next()){
                Movimientorecaudador r = new Movimientorecaudador(df.format(rs.getDouble(1)), rs.getString(2), rs.getLong(3));
                
                movimientorecaudadors.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecaudosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return movimientorecaudadors;
    }

    public List<FacturaPagaResponse> procesarArchivoOfline(String datos, String banco) throws RecaudoException {
        String tipo;
        String cuenta = "";
        String valor;
        String numerofactura = "";
        String separador=",";
        String fecha = "";
        List<FacturaPagaResponse> facturaPagaResponses = new ArrayList<>();
        
        StringTokenizer tokens = new StringTokenizer(datos, separador);
        if (tokens.countTokens() <= 1) {
            throw new RecaudoException("No existen recaudos que procesar ");
        }
	while(tokens.hasMoreTokens()){
            cuenta = "";
            valor = "";
            numerofactura = "";
        
            String linea = tokens.nextToken();
            tipo = linea.substring(0, 2);
            if (tipo.equals("00") || tipo.equals("03")) {
                //002710621120000814634000037750A
                if (linea.length() == 31) {
                    numerofactura = linea.substring(2, 11);
                    cuenta = linea.substring(11, 21);
                    valor = linea.substring(21, 30);
                } else {
                    throw new RecaudoException("Error de longitud de linea");
                }

            } else if (tipo.equals("06") || tipo.equals("01")) {
                numerofactura = linea.substring(2, 7);
                cuenta = linea.substring(7, 17);
                valor = linea.substring(17, 26);
            } else if (tipo.equals("20")) {
                fecha = linea.substring(0, 4) + "-" + linea.substring(4, 6) + "-" + linea.substring(6, 8) + " 07:00:00";
            } else {
                throw new RecaudoException("No existe tipo de recaudo: " + tipo);
            }
            if (cuenta.length() > 0){
                facturaPagaResponses.add(new FacturaPagaResponse(cuenta , numerofactura, tipo, valor, banco));
            }
        }
        
        if (facturaPagaResponses.size() > 0) {
            registrarPagoFactutasOfline(facturaPagaResponses, fecha);
        }
        return facturaPagaResponses;
    }
    
    private List<FacturaPagaResponse> registrarPagoFactutasOfline(List<FacturaPagaResponse> facturaPagaResponses, String fecha) {

        facturaPagaResponses.forEach((t) -> {
            Respuesta respuesta = registrarRecaudo(Long.parseLong(t.getNumeroFactura()),
                                Long.parseLong(t.getCuenta()),
                                Long.parseLong(t.getValor()),
                                fecha,
                                "R",
                                "",
                                t.getTipo(),
                                t.getBanco(),
                                "REC-TESO");
            t.setCodigoRespuesta(respuesta.getCodigoRespuesta());
            t.setRespuesta(respuesta.getComentario());
        });

        
        return facturaPagaResponses;
    }
    
    public Respuestafacturas consultarFacturas(String codsuscrip) throws Exception{
        List<Respuestafactura> respuestafacturas = new ArrayList<>();
        ConexionSicesp con = ConexionSicesp.getInstance();
        String sql;
        DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
           
        String fechaActualDate = sourceFormat.format(new Date());
        
        sql = "select NRO_FACTURA, "
                + " VR_FACTURA - VR_PAGADO AS VR_FACTURA, "
                + "iif(EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, 0 AS TIPO "
                + " from(select a.COD_SUSCRIP, NRO_FACTURA, VR_PAGREAL VR_PAGADO_PERIODO, d.FECHA_PAGO, c.EDAD, c.VR_FACTURA, B.CICLO AS CICLO, a.VR_PAGADO "
                + "        from SUSCRIPTORES a left outer join CICLOS b on a.CICLO = b.CICLO left "
                + "                outer join FACTURAS c on a.COD_SUSCRIP = c.COD_SUSCRIP " 
                + "                   and ANO_FACT = ULT_ANO_FACT and MES_FACT = ULT_MES_FACT "
                + "                       left outer join PERIODOS_FACT d on c.CICLO = d.CICLO " 
                + "             and c.ANO_FACT = d.ANO_FACT and c.MES_FACT = d.MES_FACT"
                + " where a.COD_SUSCRIP = " + codsuscrip + ") where VR_FACTURA - VR_PAGADO > 0";
        sql += " union all select  NRO_FINANC, VR_CUOTA_INI, "
                        + "iif(b.EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, "
                        + "1 AS TIPO"
                      + " from FINANCENC a"
                      + " left outer join FACTURAS b on a.COD_SUSCRIP = b.COD_SUSCRIP and a.NRO_FACTURA = b.NRO_FACTURA"
                      + " left outer join CICLOS c on b.CICLO = c.CICLO"
                      + " left outer join PERIODOS_FACT d on b.CICLO = d.CICLO and b.ANO_FACT = d.ANO_FACT and b.MES_FACT = d.MES_FACT"
                      + " where a.COD_SUSCRIP = " + codsuscrip
                      + " and b.EST_FINANC = 'PP'";
        sql += " union all select a.NRO_FACTURA, VR_ABONO, iif(b.EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, a.TIPO_FACT"
                        + " from PAGPARCENC a "
                        + " left outer join FACTURAS b on a.COD_SUSCRIP = b.COD_SUSCRIP and a.NRO_FACTURA = b.NRO_FACTURA "
                        + " left outer join CICLOS c on b.CICLO = c.CICLO "
                        + " left outer join PERIODOS_FACT d on b.CICLO = d.CICLO and b.ANO_FACT = d.ANO_FACT and b.MES_FACT = d.MES_FACT "
                        + " where a.COD_SUSCRIP = " + codsuscrip 
                        + " and a.TIPO_FACT in (2, 3, 4, 8)"
                        + " and iif(b.EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) >= '" + fechaActualDate + "'";
        sql += " union all select N_PAGO_FINANC, VR_PAGO, iif(e.EDAD <= 1,FECHA_PAGO+10,FECHA_PAGO) as FECHA_PAGO_CODBARRAS, 6 AS TIPO "
                            + " from PAGOS_FINANC a "
                            + " left outer join SUSCRIPTORES b on a.COD_SUSCRIP = b.COD_SUSCRIP "
                            + " left outer join CICLOS c on b.CICLO = c.CICLO "
                            + " left outer join PERIODOS_FACT d on c.CICLO = d.CICLO and ULT_ANO_FACT = d.ANO_FACT and ULT_MES_FACT = d.MES_FACT "
                            + " left outer join FACTURAS e on a.COD_SUSCRIP = e.COD_SUSCRIP and d.ANO_FACT = e.ANO_FACT and d.MES_FACT = e.MES_FACT "
                            + " where a.COD_SUSCRIP = " + codsuscrip + " and a.ESTADO = 'PE'";

        try {
            ResultSet rs = con.ejecutarSQLSelect(sql);
            while (rs.next()) {
                Respuestafactura respuestafactura = new Respuestafactura();

                respuestafactura.setIdfactura(rs.getLong(1) + "");
                respuestafactura.setTotalfactura(rs.getLong(2));
                respuestafactura.setCodRespuesta(0);
                respuestafactura.setDescripcion("0");
                respuestafactura.setCuenta(codsuscrip);
                Date fechapago = new Date((sourceFormat.parse(rs.getString(3))).getTime() + (1000 * 60 * 60 * 24) * 15);
                respuestafactura.setTipofact((Integer.valueOf(rs.getInt(4))));
                
                respuestafactura.setFechapago(sourceFormat.format(fechapago));
                
                respuestafactura.setAplicado("N");

                if (respuestafactura.getFechapago() != null && respuestafactura.getFechapago().length() == 10){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Integer fechaPago = Integer.parseInt(respuestafactura.getFechapago().replace("-", ""));
                    Integer fechaActual = Integer.parseInt(dateFormat.format(new Date()));

                    respuestafactura.setFacturavencida(fechaActual > fechaPago ? "S" : "N");
                } else {
                    respuestafactura.setFacturavencida("N");
                }       
                respuestafacturas.add(respuestafactura);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return new Respuestafacturas(respuestafacturas);
    }
    


    public static void main(String[] args) {
        
        RecaudosFacade facade = new RecaudosFacade();
            
        //System.out.println("Existe recaudo temporal:" + facade.existeRecaudoEnTemporal("840186", "271049216", "99"));
        //System.out.println("Existe recaudo sicep:" + facade.existeRecaudoSicep("840186", "0", "271049216"));
       
        Respuestafactura r = facade.consultarFactura("831578", "01", 0L, "");

//        facade.registrarRecaudo(271055883l, 12000l, 1000l, "2020-01-01", "00", "0", "0", "0", "0");
//        System.out.println(facade.consultarmovimiento("20220926", "88").size());
/*
facade.procesarArchivoOfline(
"002710621120000814634000037750A," +
"002710776950000823283000056900A," +
"002710894170000818262000166950A," +
"002711016580000020374000049750A," +
"002711050610000807248000064450A," +
"2022100588011-00005", "88");
*/
        System.out.println("r");
    }
}
