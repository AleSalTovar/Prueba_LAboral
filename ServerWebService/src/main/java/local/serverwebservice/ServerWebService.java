/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package local.serverwebservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author darsa
 */
@WebService(serviceName = "WebService")
@Stateless()
public class ServerWebService {
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "adicion")
    public Integer adicion(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        return a + b;
    }
    
    @WebMethod(operationName = "estudiantesmateria")
    public String estudiantesmateria(@WebParam(name = "materia") String materia) {
        LogicaWebservice logica = new LogicaWebservice();
        return logica.consultarEstudiantes(materia);
    }

    @WebMethod(operationName = "materiasestudiante")
    public String materiasestudiante(@WebParam(name = "estudiante") String estudiatente) {
        LogicaWebservice logica = new LogicaWebservice();
        return logica.consultarMaterias(estudiatente);
    }
}
