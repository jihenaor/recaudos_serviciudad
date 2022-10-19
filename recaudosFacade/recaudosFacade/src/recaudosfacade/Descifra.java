/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recaudosfacade;

import java.math.BigInteger;


public class Descifra {
  
  public String descifrar(String datosCifrados) {
    // Lo primero es crear dos objetos RSA, el primero hara de Cliente y el segundo de Servidor.
    // Cada uno cuenta con sus claves publicas y privadas generadas:
    RsaDesifrar rsaServidor = new RsaDesifrar(1024);
    String frase = "";

    BigInteger cifrado = new BigInteger(datosCifrados);

    // Cuando el Servidor descifra los datos lo hace con su propia clave privada:
    BigInteger descifrado = rsaServidor.decrypt(cifrado);

    // Y por ultimo se imprime por pantalla el BigInteger pasandolo a un array de chars:
    byte[] array = descifrado.toByteArray();

    // Se recorre el array:
    if (array.length > 0) {
      for (int i = 0; i < array.length; i++) {
	frase += (char) array[i];
      }
     
    }
    return frase;
  }
}