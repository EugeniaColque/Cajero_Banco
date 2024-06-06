/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Daniel
 */
public class pruebaXML {

    public static void main(String args[]) {
        File archivoXML = new File(System.getProperty("user.dir") + "/src/usuarios/usuarios.xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documentoXML = builder.parse(archivoXML);

            NodeList listaCuentas = documentoXML.getElementsByTagName("usuario");
            System.out.println("NroCuentas: " + listaCuentas.getLength());
            int numeroNodo = 0;
            String id = "";
            Element elemento = null;
            while (!id.equals("daniel87")  && numeroNodo < listaCuentas.getLength()) {
                Node nodo = listaCuentas.item(numeroNodo);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    elemento = (Element) nodo;
                    id = elemento.getAttribute("id");
                    System.out.println("Nodo procesado: " + id);
                }
                numeroNodo++;
            };
            System.out.println("Se encontro el usuario: "+ id);
            System.out.println("usuario: "+elemento.getElementsByTagName("divisa").item(0).getTextContent());
            
           
            
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
