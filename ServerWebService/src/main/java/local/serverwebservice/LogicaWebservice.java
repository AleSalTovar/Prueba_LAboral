/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.serverwebservice;

import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.ModeloEstudianteMateria;
import modelo.ModeloMateriaEstudiante;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author darsa
 */
public class LogicaWebservice {

    public String consultarMaterias(String estudiante) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement("materias");
            doc.appendChild(rootElement);

            ModeloMateriaEstudiante estmat = new ModeloMateriaEstudiante();

            estmat.setWhere("WHERE id_estudiante=" + estudiante);
            if (estmat.consultarRegistro()) {
                // student elements
                while(estmat.siguienteRegistro()) {
                    Element materia = doc.createElement("materia");

                    Element codigoMateria = doc.createElement("codigo");
                    codigoMateria.setTextContent(String.valueOf(estmat.obtenerRegistro().getId_materia()));

                    Element nombreMateria = doc.createElement("nombre");
                    nombreMateria.setTextContent(estmat.obtenerRegistro().getNombre_materia());

                    materia.appendChild(codigoMateria);
                    materia.appendChild(nombreMateria);

                    rootElement.appendChild(materia);
                }
            }

            // Write the content into XML file
            return convertDocumentToString(doc);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error";
        }
    }

    public String consultarEstudiantes(String materia) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement("estudiantes");
            doc.appendChild(rootElement);

            ModeloEstudianteMateria matest = new ModeloEstudianteMateria();

            matest.setWhere("WHERE id_materia=" + materia);
            if (matest.consultarRegistro()) {
                // student elements
                while(matest.siguienteRegistro()) {
                    Element estudiante = doc.createElement("estudiante");

                    Element codigoMateria = doc.createElement("codigo");
                    codigoMateria.setTextContent(String.valueOf(matest.obtenerRegistro().getId_estudiante()));

                    Element nombreMateria = doc.createElement("nombre");
                    nombreMateria.setTextContent(matest.obtenerRegistro().getNombre() + " " +
                            matest.obtenerRegistro().getApellido());

                    estudiante.appendChild(codigoMateria);
                    estudiante.appendChild(nombreMateria);

                    rootElement.appendChild(estudiante);
                }
            }

            // Write the content into XML file
            return convertDocumentToString(doc);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error";
        }
    }

    private String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
