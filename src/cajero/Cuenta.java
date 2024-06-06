/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

/**
 *
 * @author Daniel
 */
public class Cuenta {

    private String nroCuenta;
    private String divisa;
    private String monto;

    public Cuenta(String nroCuenta, String divisa, String monto) {
        this.nroCuenta = nroCuenta;
        this.divisa = divisa;
        this.monto = monto;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public String getDivisa() {
        return divisa;
    }

    public String getMonto() {
        return monto;
    }
    
    public String toString(){
        return nroCuenta + "("+ getDivisa()+ ")";
    }
}
