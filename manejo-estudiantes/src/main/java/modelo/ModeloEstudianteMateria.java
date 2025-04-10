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

    public boolean insertarRegistro(EstudianteMateriaTD sEstudiante_Materia) {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        PreparedStatement stm = null;
        Connection con = conexion.getConexion();
        try {
            sql = "INSERT INTO estudiante_materia VALUES(?,?)";
            stm = con.prepareStatement(sql);
            stm.setLong(1, sEstudiante_Materia.getId_estudiante());
            stm.setInt(2, sEstudiante_Materia.getId_materia());
            stm.executeUpdate();
            stm.close();
        } catch (SQLException E) {
            error_msg = conexion.validarError(stm.toString(), E.getMessage());
            E.printStackTrace();
            resultado = false;
        } catch (Exception E) {
            error_msg = "Error\n" + E.getMessage();
            E.printStackTrace();
            resultado = false;
        }
        try {
            con.close();
        } catch (Exception E) {
            error_msg = "Error\n" + E.getMessage();
            E.printStackTrace();
        }
        return resultado;
    }

    public boolean eliminarRegistro() {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        Connection con = conexion.getConexion();
        PreparedStatement stm = null;
        try {
            sql = "DELETE FROM estudiante_materia " + sWhere;
            stm = con.prepareStatement(sql);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException E) {
            error_msg = conexion.validarError(stm.toString(), E.getMessage());
            E.printStackTrace();
            resultado = false;
        } catch (Exception E) {
            error_msg = "Error\n" + E.getMessage();
            E.printStackTrace();
            resultado = false;
        }
        try {
            con.close();
        } catch (Exception E) {
            error_msg = "Error\n" + E.getMessage();
            E.printStackTrace();
        }
        return resultado;
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
            sql = "SELECT * FROM estudiante_materia " + sWhere;
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                EstudianteMateriaTD registro = new EstudianteMateriaTD();
                registro.setId_materia(rs.getInt(1));
                registro.setId_estudiante(rs.getLong(2));
                lestudiante_materia.add(registro);
            }
            rs.close();
            stm.close();
        } catch (SQLException E) {
            error_msg = conexion.validarError(stm.toString(), E.getMessage());
            E.printStackTrace();
            resultado = false;
        } catch (Exception E) {
            error_msg = "Error\n" + E.getMessage();
            E.printStackTrace();
            resultado = false;
        }
        try {
            con.close();
        } catch (Exception E) {
            error_msg = "Error\n" + E.getMessage();
            E.printStackTrace();
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
