package recaudosfacade;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyService {
    private static MyService single_instance = null;

    private static Map<String, Long> valores = new HashMap<>();

    private MyService() {

    }
 
    public static MyService getInstance() {
        if (single_instance == null)
            single_instance = new MyService();
 
        return single_instance;
    }

    public synchronized boolean existeLlave(String key) {
        Long valor = valores.get(key);
        boolean existe = false;

        if (valor == null) {
            valores.put(key, generarFechaActual());
        } else {
            existe = true;
            for(Map.Entry<String, Long> entrada : valores.entrySet()) {
                if (((generarFechaActual() - entrada.getValue()) / 1000) > 5) {
                    remove(key);
                    existe = false;
                }
            }
        }
        return existe;
    }

    private long generarFechaActual() {
        Date fechaActual = new Date();
        return fechaActual.getTime();
    }

    private synchronized void remove(String key) {
        valores.remove(key);
    }
}
