/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import cajero.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestorCuenta {

    private File directorioUsuarios;
    private File directorioLog;
    private Document documentoXML;
    private Element perfilUsuario;
    private Element cuentaActual;

    public GestorCuenta() {
        directorioUsuarios = new File(System.getProperty("user.dir") + "/src/usuarios/");
        directorioLog = new File(System.getProperty("user.dir") + "/src/logUsuarios/");
        if (!directorioUsuarios.exists()) {
            directorioUsuarios.mkdir();
        }
    }

    public void crearEvento(Evento evento) {
        try {
            File directorio = new File(directorioLog + "/logs.xml");
            escribirDatosEvento(evento, directorio);
        } catch (IOException ex) {
            Logger.getLogger(GestorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void escribirDatosEvento(Evento evento, File ruta) throws IOException {
        //en caso el fichero no exista
        if (!ruta.exists()) {
            generarDocumento(evento, ruta);

        } else {
            anadirEvento(evento, ruta);
        }
    }
    
    private void anadirEvento(Evento evento, File ruta) {
        Document documento = getDocumento(ruta);
        Node eventos = documento.getElementsByTagName("logs").item(0);
        eventos.appendChild(escribirDatosEvento(evento, documento));
        guardarCambiosEnXML(new File(directorioLog + "/logs.xml"), documento);
    }

    private void generarDocumento(Evento evento, File ruta) {
        Document documento = generarNuevoDocumento();
        Element logs = documento.createElement("logs");
        documento.appendChild(logs);
        logs.appendChild(escribirDatosEvento(evento, documento));
        guardarCambiosEnXML(ruta, documento);

    }

    private Element escribirDatosEvento(Evento evento, Document documento) {
        Element log = documento.createElement("log");

        Element nroCuenta = documento.createElement("nroCuenta");
        nroCuenta.appendChild(documento.createTextNode(evento.getNroCuenta()));
        log.appendChild(nroCuenta);

        Element fecha = documento.createElement("fecha");
        fecha.appendChild(documento.createTextNode(evento.getFecha()));
        log.appendChild(fecha);

        Element descripcion = documento.createElement("descripcion");
        descripcion.appendChild(documento.createTextNode(evento.getDescripcion()));
        log.appendChild(descripcion);

        Element monto = documento.createElement("monto");
        monto.appendChild(documento.createTextNode(evento.getMonto()));
        log.appendChild(monto);

        Element saldo = documento.createElement("saldo");
        saldo.appendChild(documento.createTextNode(evento.getSaldo()));
        log.appendChild(saldo);

        return log;
    }

    public void anadirCuenta(String divisa, String nroCuenta, String montoInicial) {
        Element cuenta = documentoXML.createElement("cuenta");

        Element numeroCuenta = documentoXML.createElement("nroCuenta");
        numeroCuenta.appendChild(documentoXML.createTextNode(nroCuenta));
        cuenta.appendChild(numeroCuenta);

        Element div = documentoXML.createElement("divisa");
        div.appendChild(documentoXML.createTextNode(divisa));
        cuenta.appendChild(div);

        Element monto = documentoXML.createElement("monto");
        monto.appendChild(documentoXML.createTextNode(montoInicial));
        cuenta.appendChild(monto);

        perfilUsuario.appendChild(cuenta);
        guardarCambiosEnXML(new File(directorioUsuarios + "/usuarios.xml"), documentoXML);
    }

    public List<Evento> getEventos() {
        Document documento = getDocumento(new File(directorioLog + "/logs.xml"));
        NodeList logs = documento.getElementsByTagName("log");
        List<Evento> listaLogs = new ArrayList<Evento>();
        for (int i = 0; i < logs.getLength(); i++) {
            Node nodoLog = logs.item(i);
            Element log = (Element) nodoLog;
            String nroCuenta = log.getElementsByTagName("nroCuenta").item(0).getTextContent();
            if (nroCuenta.equals(getNroCuenta())) {
                String fecha = log.getElementsByTagName("fecha").item(0).getTextContent();
                String descripcion = log.getElementsByTagName("descripcion").item(0).getTextContent();
                String monto = log.getElementsByTagName("monto").item(0).getTextContent();
                String saldo = log.getElementsByTagName("saldo").item(0).getTextContent();
                listaLogs.add(new Evento(getNroCuenta(), descripcion, monto, saldo));
            }
        }

        return listaLogs;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void crearCuenta(Usuario usuario) {

        try {
            File directorio = new File(directorioUsuarios + "/usuarios.xml");
            escribirDatosUsuario(usuario, directorio);
        } catch (IOException ex) {
            Logger.getLogger(GestorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void escribirDatosUsuario(Usuario usuario, File ruta) throws IOException {
        //en caso el fichero no exista
        if (!ruta.exists()) {
            generarDocumento(usuario, ruta);

        } else {
            anadirUsuario(usuario, ruta);
        }
    }
    
    private void anadirUsuario(Usuario usuario, File ruta) {
        Document documento = getDocumento(ruta);
        Node usuarios = documento.getElementsByTagName("usuarios").item(0);
        usuarios.appendChild(escribirDatosUsuario(usuario, documento));
        guardarCambiosEnXML(ruta, documento);

    }

    private void generarDocumento(Usuario usuario, File ruta) {
        Document documento = generarNuevoDocumento();
        Element usuarios = documento.createElement("usuarios");
        documento.appendChild(usuarios);
        usuarios.appendChild(escribirDatosUsuario(usuario, documento));
        guardarCambiosEnXML(ruta, documento);

    }

    private Document getDocumento(File archivoXML) {
        Document document = null;
        try {
            if (archivoXML.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                document = builder.parse(archivoXML);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    private Document generarNuevoDocumento() {
        Document nuevoDocumento = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            nuevoDocumento = builder.newDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevoDocumento;
    }

    private Element escribirDatosUsuario(Usuario cuentaUsuario, Document documento) {

        Element usuario = documento.createElement("usuario");
        usuario.setAttribute("id", cuentaUsuario.getNombreUsuario());

        Element nombreTitular = documento.createElement("nombreTitular");
        nombreTitular.appendChild(documento.createTextNode(cuentaUsuario.getNombreTitular()));
        usuario.appendChild(nombreTitular);

        Element contrasena = documento.createElement("contrasena");
        contrasena.appendChild(documento.createTextNode(Seguridad.cifrar(cuentaUsuario.getContrasena())));
        usuario.appendChild(contrasena);

        Element cuenta = documento.createElement("cuenta");
        usuario.appendChild(cuenta);

        Element nroCuenta = documento.createElement("nroCuenta");
        nroCuenta.appendChild(documento.createTextNode(cuentaUsuario.getNumeroCuenta()));
        cuenta.appendChild(nroCuenta);

        Element divisa = documento.createElement("divisa");
        divisa.appendChild(documento.createTextNode(cuentaUsuario.getTipoDivisa()));
        cuenta.appendChild(divisa);

        Element monto = documento.createElement("monto");
        monto.appendChild(documento.createTextNode(String.valueOf(cuentaUsuario.getMonto())));
        cuenta.appendChild(monto);

        return usuario;
    }

    private void guardarCambiosEnXML(File ruta, Document documento) {

        try {
            Source origen = new DOMSource(documento);
            Result resultado = new StreamResult(new OutputStreamWriter(new FileOutputStream(ruta)));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(origen, resultado);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int generarNumeroDeCuenta() {
        SecureRandom sr = null;
        int min = 20000000;
        int max = 30000000;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GestorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sr.nextInt(min, max);
    }

    public boolean existeUsuario(String nombreUsuario) {
        Document documentoXML = getDocumento(new File(directorioUsuarios + "/usuarios.xml"));
        String id = "";
        if (documentoXML != null) {

            NodeList listaCuentas = documentoXML.getElementsByTagName("usuario");
            int numeroNodo = 0;
            while (!id.equals(nombreUsuario) && numeroNodo < listaCuentas.getLength()) {
                Node nodo = listaCuentas.item(numeroNodo);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    id = elemento.getAttribute("id");
                }
                numeroNodo++;
            };
        }

        return id.equals(nombreUsuario);

    }

    public boolean contrasenaCoincide(char[] contrasena) {
        String constrasenaEncriptada = perfilUsuario.getElementsByTagName("contrasena").item(0).getTextContent();
        String contrasenaActual= Seguridad.descifrar(constrasenaEncriptada);
        String nuevaContrasena = String.copyValueOf(contrasena);
        return contrasenasCoinciden(nuevaContrasena, contrasenaActual);
    }

    public boolean contrasenasCoinciden(String nuevaContrasena, String contrasenaActual) {
        return nuevaContrasena.equals(contrasenaActual);
    }

    public String saldoDisponible() {
        return cuentaActual.getElementsByTagName("monto").item(0).getTextContent();
    }

    public void depositar(String divisa, double montoDeposito) {//cuenta propia
        double montoADepositar = getMontoConvertido(divisa, montoDeposito);
        depositar(montoADepositar, getNroCuenta());
    }

    public void depositar(String divisa, double montoDeposito, String nroCuenta) {// a otra cuenta
        double montoADepositar = getMontoConvertido(divisa, montoDeposito, nroCuenta);
        depositar(montoADepositar, nroCuenta);
    }

    private void depositar(double montoADepositar, String nroCuenta) {// solo deposito
        NodeList cuentas = perfilUsuario.getElementsByTagName("cuenta");

        for (int i = 0; i < cuentas.getLength(); i++) {
            Element cuenta = (Element) cuentas.item(i);
            String nroCuentaAEncontrar = cuenta.getElementsByTagName("nroCuenta").item(0).getTextContent();
            if (nroCuentaAEncontrar.equals(nroCuenta)) {
                Node montoNode = cuenta.getElementsByTagName("monto").item(0);
                double montoExistente = Double.parseDouble(montoNode.getTextContent());
                montoExistente += montoADepositar;
                montoNode.setTextContent(String.valueOf(montoExistente)); // Actualiza el nodo monto
            }
        }
        guardarCambiosEnXML(new File(directorioUsuarios + "/usuarios.xml"), documentoXML);

    }

    public void retirar(String divisa, double montoRetiro) {
        double montoARetirar = getMontoConvertido(divisa, montoRetiro);
        retirar(montoARetirar);
    }

    private void retirar(double montoARetirar) {
        NodeList cuentas = perfilUsuario.getElementsByTagName("cuenta");

        for (int i = 0; i < cuentas.getLength(); i++) {
            Element cuenta = (Element) cuentas.item(i);
            String nroCuentaAEncontrar = cuenta.getElementsByTagName("nroCuenta").item(0).getTextContent();
            if (nroCuentaAEncontrar.equals(getNroCuenta())) {
                Node montoNode = cuenta.getElementsByTagName("monto").item(0);
                double montoExistente = Double.parseDouble(montoNode.getTextContent());
                if (montoExistente >= montoARetirar) {
                    montoExistente -= montoARetirar;
                    montoNode.setTextContent(String.valueOf(montoExistente));
                }
            }
        }
        guardarCambiosEnXML(new File(directorioUsuarios + "/usuarios.xml"), documentoXML);
    }

    public void transferir(String divisa, double montoATransferir, String numeroCuentaDestino) {
        retirar(divisa, montoATransferir);
        perfilUsuario = getUsuario(numeroCuentaDestino);
        depositar(divisa, montoATransferir, numeroCuentaDestino);
        guardarCambiosEnXML(new File(directorioUsuarios + "/usuarios.xml"), documentoXML);
        perfilUsuario = getUsuario(getNroCuenta());
    }

    private String getDivisa(String numeroCuenta) {
        NodeList listaUsuarios = documentoXML.getElementsByTagName("usuario");

        String divisa = "";
        String nroCuenta = "";
        for (int i = 0; !nroCuenta.equals(numeroCuenta) && i < listaUsuarios.getLength(); i++) {
            Node nodo = listaUsuarios.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element usuario = (Element) nodo;
                NodeList listaCuentas = usuario.getElementsByTagName("cuenta");
                for (int j = 0; !nroCuenta.equals(numeroCuenta) && j < listaCuentas.getLength(); j++) {
                    Element cuenta = (Element) listaCuentas.item(j);
                    nroCuenta = cuenta.getElementsByTagName("nroCuenta").item(0).getTextContent();
                    divisa = cuenta.getElementsByTagName("divisa").item(0).getTextContent();
                }
            }
        }
        return divisa;
    }

    private Element getUsuario(String numeroCuenta) {//devuelve etiqueta usuario dado nroCuenta.
        NodeList listaUsuarios = documentoXML.getElementsByTagName("usuario");

        Element usuario = null;
        String nroCuenta = "";
        for (int i = 0; !nroCuenta.equals(numeroCuenta) && i < listaUsuarios.getLength(); i++) {
            Node nodo = listaUsuarios.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                usuario = (Element) nodo;
                NodeList listaCuentas = usuario.getElementsByTagName("cuenta");
                for (int j = 0; !nroCuenta.equals(numeroCuenta) && j < listaCuentas.getLength(); j++) {
                    Element cuenta = (Element) listaCuentas.item(j);
                    nroCuenta = cuenta.getElementsByTagName("nroCuenta").item(0).getTextContent();
                }
            }
        }
        return usuario;
    }

    public double getTasaCambio(String divisaOrigen, String divisaDestino) {
        double tasaCambio = 1;
        if (!divisaOrigen.equals(divisaDestino)) {
            if (divisaOrigen.equals("bolivianos") && divisaDestino.equals("dolares")) {
                tasaCambio = 0.14;
            } else if (divisaOrigen.equals("bolivianos") && divisaDestino.equals("euros")) {
                tasaCambio = 0.13;
            } else if (divisaOrigen.equals("dolares") && divisaDestino.equals("bolivianos")) {
                tasaCambio = 6.96;
            } else if (divisaOrigen.equals("dolares") && divisaDestino.equals("euros")) {
                tasaCambio = 0.93;
            } else if (divisaOrigen.equals("euros") && divisaDestino.equals("dolares")) {
                tasaCambio = 1.08;
            } else if (divisaOrigen.equals("euros") && divisaDestino.equals("bolivianos")) {
                tasaCambio = 7.44;
            }
        }
        return tasaCambio;
    }

    public boolean cambiarContrasena(char[] nuevaCont, char[] confirmacionCont) {
        boolean contrasenasCoinciden = contrasenasCoinciden(String.copyValueOf(nuevaCont), String.copyValueOf(confirmacionCont));
        if (contrasenasCoinciden) {
            perfilUsuario.getElementsByTagName("contrasena").item(0).setTextContent(Seguridad.cifrar(String.copyValueOf(nuevaCont)));
            guardarCambiosEnXML(new File(directorioUsuarios + "/usuarios.xml"), documentoXML);
        }
        return contrasenasCoinciden;
    }

    public void extraerCuentaUsuario(String nombreUsuario) {
        documentoXML = getDocumento(new File(directorioUsuarios + "/usuarios.xml"));
        Element usuario = null;
        NodeList listaUsuarios = documentoXML.getElementsByTagName("usuario");
        int numeroNodo = 0;
        String id = "";
        while (!id.equals(nombreUsuario) && numeroNodo < listaUsuarios.getLength()) {
            Node nodo = listaUsuarios.item(numeroNodo);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                usuario = (Element) nodo;
                id = usuario.getAttribute("id");
            }
            numeroNodo++;
        }
        perfilUsuario = usuario;
    }

    public Cuenta[] getCuentas() {
        NodeList cuentas = perfilUsuario.getElementsByTagName("cuenta");
        Cuenta[] listaCuentas = new Cuenta[cuentas.getLength()];
        for (int i = 0; i < cuentas.getLength(); i++) {
            Node nodoCuenta = cuentas.item(i);
            Element cuenta = (Element) nodoCuenta;
            String nroCuenta = cuenta.getElementsByTagName("nroCuenta").item(0).getTextContent();
            String divisa = cuenta.getElementsByTagName("divisa").item(0).getTextContent();
            String monto = cuenta.getElementsByTagName("monto").item(0).getTextContent();
            listaCuentas[i] = new Cuenta(nroCuenta, divisa, monto);
        }
        return listaCuentas;
    }

    public void establecerCuenta(String numeroCuenta) {
        NodeList cuentas = perfilUsuario.getElementsByTagName("cuenta");
        Element cuenta = null;
        String nroCuenta = "";
        for (int i = 0; !nroCuenta.equals(numeroCuenta) && i < cuentas.getLength(); i++) {
            Node nodoCuenta = cuentas.item(i);
            if (nodoCuenta.getNodeType() == Node.ELEMENT_NODE) {
                cuenta = (Element) nodoCuenta;
                nroCuenta = cuenta.getElementsByTagName("nroCuenta").item(0).getTextContent();
            }
        }
        cuentaActual = cuenta;
    }

    public String getMonto() {
        return cuentaActual.getElementsByTagName("monto").item(0).getTextContent();
    }

    public String getDivisa() {
        return cuentaActual.getElementsByTagName("divisa").item(0).getTextContent();
    }

    public String getNroCuenta() {
        return cuentaActual.getElementsByTagName("nroCuenta").item(0).getTextContent();

    }

    public double getMontoConvertido(String divisa, double monto) {
        double tasaCambio = getTasaCambio(divisa, getDivisa(getNroCuenta()));
        double montoConvertido = tasaCambio * monto;
        return montoConvertido;
    }

    public double getMontoConvertido(String divisa, double monto, String nroCuenta) {
        double tasaCambio = getTasaCambio(divisa, getDivisa(nroCuenta));
        double montoConvertido = tasaCambio * monto;
        return montoConvertido;
    }

    public String titularCuenta() {
        return perfilUsuario.getElementsByTagName("nombreTitular").item(0).getTextContent();
    }

    public static void main(String args[]) {
        GestorCuenta gestorCuenta = new GestorCuenta();
        gestorCuenta.extraerCuentaUsuario("daniel87");
        gestorCuenta.establecerCuenta("80789");
        System.out.println(gestorCuenta.getMonto());
        System.out.println(gestorCuenta.getDivisa());
        gestorCuenta.retirar("bolivianos", 100);
        System.out.println(gestorCuenta.getMonto());
        gestorCuenta.depositar("dolares", 100);
        System.out.println(gestorCuenta.getMonto());
        /*
        gestorCuenta.retirar("dolares", 1, "23452345");
        List<Evento> eventos = gestorCuenta.getEventos("23452345");
        String[] propiedades = new String[5];
        for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            propiedades[0] = evento.getNroCuenta();
            propiedades[1] = evento.getFecha();
            propiedades[2] = evento.getDescripcion();
            propiedades[3] = evento.getMonto();
            propiedades[4] = evento.getSaldo();
        }
        System.out.println(propiedades[1]);
        
        char[] nuevaContrasena = {'1', '0', '6'};
        char[] contrasenaAConfirmar = {'1', '0', '6'};
        gestorCuenta.cambiarContrasena(nuevaContrasena, contrasenaAConfirmar);
        gestorCuenta.anadirCuenta("bolivianos", "7777777");
         */

    }

}
