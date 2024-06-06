/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author Daniel
 */
public class Usuario {

    private String nombreUsuario;
    private String nombreTitular;
    private String contrasena;
    private String nroCuenta;
    private String divisa;
    private String monto;
    
   

    public Usuario(String nombreUsuario, String nombreTitular, String contrasena, String nroCuenta, String divisa, String montoInicial){
        
        this.nombreUsuario = nombreUsuario;
        this.nombreTitular = nombreTitular;
        this.contrasena = contrasena;
        this.nroCuenta = nroCuenta;
        this.divisa = divisa;
        this.monto = montoInicial;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public String getNumeroCuenta() {
        return nroCuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTipoDivisa() {
        return divisa.toLowerCase();
    }
    
    public String getMonto() {
        return monto;
    }
    
    private String procesarContrasena(char[] contrasena){
        String contrasenaCadena = "";
        for(int i = 0; i < contrasena.length; i++){
            contrasenaCadena += "" + contrasena[i];
        }
        return contrasenaCadena;
    }

    
}
