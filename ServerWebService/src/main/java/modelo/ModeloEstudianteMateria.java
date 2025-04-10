/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import estructura.EstudianteMateriaTD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import utilidades.ConexionBD;


/**
 *
 * @author alejo
 */
public class ModeloEstudianteMateria {
    private String sWhere;
    private Vector<EstudianteMateriaTD> lestudiante_materia;
    private int indice;
    private String error_msg;

    public ModeloEstudianteMateria() {
        sWhere = "";
        lestudiante_materia = new Vector<EstudianteMateriaTD>();
        error_msg = "";
        indice = -1;
    }

    public boolean consultarRegistro() {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        lestudiante_materia = new Vector<EstudianteMateriaTD>();
        indice = -1;
        Connection con = conexion.getConexion();
        PreparedStatement stm = null;
        try {
            sql = "SELECT * FROM estudiantesmateria " + sWhere;
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                EstudianteMateriaTD registro = new EstudianteMateriaTD();
                registro.setId_estudiante(rs.getLong(1));
                registro.setNombre(rs.getString(2));
                registro.setApellido(rs.getString(3));
                registro.setId_materia(rs.getInt(4));
                lestudiante_materia.add(registro);
            }
            rs.close();
            stm.close();
        } catch (SQLException E) {
            error_msg = conexion.validarError(stm.toString(), E.getMessage());
            E.printStackTrace();
            //Log.error("Error", E);
            resultado = false;
        } catch (Exception E) {
            error_msg = "Error\n" + E.getMessage();
            E.printStackTrace();
            //Log.error("Error", E);
            resultado = false;
        }
        try {
            con.close();
        } catch (Exception E) {
            error_msg = "Error\n" + E.getMessage();
            E.printStackTrace();
            //Log.error("Error", E);
        }
        return resultado;
    }

    public EstudianteMateriaTD obtenerRegistro() {
        return lestudiante_materia.get(indice);
    }

    public boolean siguienteRegistro() {
        indice++;
        return indice < lestudiante_materia.size();
    }

    public void setWhere(String where) {
        sWhere = where;
    }
    
    public String getError() {
        return error_msg;
    }
}
