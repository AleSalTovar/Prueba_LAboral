/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import estructura.EstudianteTD;
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
public class ModeloEstudiante {
    private String sWhere;
    private Vector<EstudianteTD> lestudiante;
    private int indice;
    private String error_msg;

    public ModeloEstudiante() {
        sWhere = "";
        lestudiante = new Vector<EstudianteTD>();
        error_msg = "";
        indice = -1;
    }

    public boolean insertarRegistro(EstudianteTD sEstudiante) {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        PreparedStatement stm = null;
        Connection con = conexion.getConexion();
        try {
            sql = "INSERT INTO estudiante VALUES(?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setLong(1, sEstudiante.getId_estudiante());
            stm.setString(2, sEstudiante.getNombre_estudiante());
            stm.setString(3, sEstudiante.getApellido_estudiante());
            stm.setString(4, sEstudiante.getEmail());
            stm.setInt(5, sEstudiante.getPais());
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

    public boolean actualizarRegistro(EstudianteTD sEstudiante) {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        PreparedStatement stm = null;
        Connection con = conexion.getConexion();
        try {
            sql = "LOCK TABLE estudiante WRITE";
            stm = con.prepareStatement(sql);
            stm.execute();
            sql = "UPDATE estudiante SET " + 
                    "nombre=?," + 
                    "apellido=?," +
                    "email=?," +
                    "id_pais=? " +
                    sWhere;
            stm = con.prepareStatement(sql);
            stm.setString(1, sEstudiante.getNombre_estudiante());
            stm.setString(2, sEstudiante.getApellido_estudiante());
            stm.setString(3, sEstudiante.getEmail());
            stm.setInt(4, sEstudiante.getPais());
            stm.executeUpdate();
            sql = "UNLOCK TABLES";
            stm = con.prepareStatement(sql);
            stm.execute();
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
            sql = "DELETE FROM estudiante " + sWhere;
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
        lestudiante = new Vector<EstudianteTD>();
        indice = -1;
        Connection con = conexion.getConexion();
        PreparedStatement stm = null;
        try {
            sql = "SELECT * FROM estudiante " + sWhere;
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                EstudianteTD registro = new EstudianteTD();
                registro.setId_estudiante(rs.getLong(1));
                registro.setNombre_estudiante(rs.getString(2));
                registro.setApellido_estudiante(rs.getString(3));
                registro.setEmail(rs.getString(4));
                registro.setPais(rs.getInt(5));
                lestudiante.add(registro);
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

    public EstudianteTD obtenerRegistro() {
        return lestudiante.get(indice);
    }

    public boolean siguienteRegistro() {
        indice++;
        return indice < lestudiante.size();
    }

    public void setWhere(String where) {
        sWhere = where;
    }
    
    public String getError() {
        return error_msg;
    }
}
