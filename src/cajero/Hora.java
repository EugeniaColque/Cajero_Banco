/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Daniel
 */
public class Hora {
    public static String hora(){
        LocalTime now = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String horaFormateada = now.format(formato);
        return horaFormateada;
    }
}
