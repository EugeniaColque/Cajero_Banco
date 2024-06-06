package cajero;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GestorIO {

    public String inString() {

        String entrada = null;
        try {
            entrada = b.readLine();
        } catch (Exception e) {
            this.salir();
        }

        return entrada;
    }

    public int inInt() {
        int entrada = 0;
        try {
            entrada = Integer.parseInt(this.inString());
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    public void out(String salida) {
        System.out.println(salida);
    }

    private void salir() {
        System.out.println("ERROR de entrada/salida");
        System.exit(0);
    }

    private static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

}
