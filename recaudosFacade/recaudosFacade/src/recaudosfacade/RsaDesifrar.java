package recaudosfacade;

/*
    Author:     Alberto Gil Tesa
    WebSite:    http://giltesa.com
    License:    CC BY-NC-SA 3.0
                http://goo.gl/CTYnN

    File:       Rsa.java
    Date:       26/02/2013
    
    NOTAS:
        http://es.wikipedia.org/wiki/RSA
 */
import java.math.BigInteger;

public class RsaDesifrar {

  private final BigInteger llaveCompartida; // Clave compartida
  private final BigInteger llavePrivada; // Clave privada (no se comparte)
  private final int D = 0, N = 1;

  /**
   * Constructor que genera las claves publica y privada de tamaño bitlen
   * indicado.
   *
   * @param bitlen
   */
  public RsaDesifrar(int bitlen) {
    String lineaCompartido;
    String lineaPrivado;

    lineaCompartido = "82851642209428477315876002473442200361443332017540328883203140113584234206716040107515277078724647452104346818919681137744447741496960913018748845315280373408741286554571864614973410580157692218789379827148481667246107814503858308895730596718893870268608433083776571992518961791373590294588567979341480580911";

    llaveCompartida = new BigInteger(lineaCompartido);

    lineaPrivado = "55234428139618984877250668315628133574295554678360219255468760075722822804477360071676851385816431634736231212613120758496298494331307275345832563543520236700803748617874484195135180875324570408519651455948715447601474183778684115652914830678823236516918328957451375154949795716865095462544207988097420108667";

    llavePrivada = new BigInteger(lineaPrivado);
  }

  /**
   * Pasa el BigInteger de cifrado a normal usando la clave privada.
   *
   * @param message
   * @return
   */
  public BigInteger decrypt(BigInteger message) {
    return message.modPow(llavePrivada, llaveCompartida);
  }

  /**
   * Pasa el BigInteger de cifrado a normal usando la clave privada recibida.
   *
   * @param message
   * @param key
   * @return
   */
  public BigInteger decrypt(BigInteger message, BigInteger[] key) {
    return message.modPow(key[D], key[N]);
  }

  /**
   * Devuelve la clave privada.
   *
   * @return
   */
  public BigInteger[] getPrivateKey() {
    return new BigInteger[]{llavePrivada, llaveCompartida};
  }
}
