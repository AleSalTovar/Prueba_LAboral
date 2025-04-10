/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import estructura.MateriaTD;
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
public class ModeloMateria {
    private String sWhere;
    private Vector<MateriaTD> lmateria;
    private int indice;
    private String error_msg;

    public ModeloMateria() {
        sWhere = "";
        lmateria = new Vector<MateriaTD>();
        error_msg = "";
        indice = -1;
    }

    public boolean insertarRegistro(MateriaTD sMateria) {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        PreparedStatement stm = null;
        Connection con = conexion.getConexion();
        try {
            sql = "INSERT INTO materia VALUES(?,?)";
            stm = con.prepareStatement(sql);
            stm.setLong(1, sMateria.getId_materia());
            stm.setString(2, sMateria.getNombre_materia());
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

    public boolean actualizarRegistro(MateriaTD sMateria) {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        PreparedStatement stm = null;
        Connection con = conexion.getConexion();
        try {
            sql = "LOCK TABLE materia WRITE";
            stm = con.prepareStatement(sql);
            stm.execute();
            sql = "UPDATE materia SET " + 
                    "nombre_materia=?" +
                    sWhere;
            stm = con.prepareStatement(sql);
            stm.setString(1, sMateria.getNombre_materia());
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
            sql = "DELETE FROM materia " + sWhere;
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
        lmateria = new Vector<MateriaTD>();
        indice = -1;
        Connection con = conexion.getConexion();
        PreparedStatement stm = null;
        try {
            sql = "SELECT * FROM materia " + sWhere;
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MateriaTD registro = new MateriaTD();
                registro.setId_materia(rs.getInt(1));
                registro.setNombre_materia(rs.getString(2));
                lmateria.add(registro);
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

    public MateriaTD obtenerRegistro() {
        return lmateria.get(indice);
    }

    public boolean siguienteRegistro() {
        indice++;
        return indice < lmateria.size();
    }

    public void setWhere(String where) {
        sWhere = where;
    }
    
    public String getError() {
        return error_msg;
    }
}
