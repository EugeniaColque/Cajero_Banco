/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Daniel
 */
public class Evento {

    private String nroCuenta;
    private String descripcion;
    private String monto;
    private String saldo;

    public Evento(String nroCuenta, String descripcion, String monto, String saldo) {
        this.nroCuenta = nroCuenta;
        this.descripcion = descripcion;
        this.monto = monto;
        this.saldo = saldo;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMonto() {
        return monto;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getFecha() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaFormateada = fechaActual.format(formato);
        return fechaFormateada;
    }
}
