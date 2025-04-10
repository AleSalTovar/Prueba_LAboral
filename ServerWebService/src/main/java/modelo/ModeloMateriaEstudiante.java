/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import estructura.MateriaEstudianteTD;
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
public class ModeloMateriaEstudiante {
    private String sWhere;
    private Vector<MateriaEstudianteTD> lmateria_estudiante;
    private int indice;
    private String error_msg;

    public ModeloMateriaEstudiante() {
        sWhere = "";
        lmateria_estudiante = new Vector<MateriaEstudianteTD>();
        error_msg = "";
        indice = -1;
    }

    public boolean consultarRegistro() {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        lmateria_estudiante = new Vector<MateriaEstudianteTD>();
        indice = -1;
        Connection con = conexion.getConexion();
        PreparedStatement stm = null;
        try {
            sql = "SELECT * FROM materiasestudiante " + sWhere;
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MateriaEstudianteTD registro = new MateriaEstudianteTD();
                registro.setId_materia(rs.getInt(1));
                registro.setNombre_materia(rs.getString(2));
                registro.setId_estudiante(rs.getLong(3));
                lmateria_estudiante.add(registro);
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

    public MateriaEstudianteTD obtenerRegistro() {
        return lmateria_estudiante.get(indice);
    }

    public boolean siguienteRegistro() {
        indice++;
        return indice < lmateria_estudiante.size();
    }

    public void setWhere(String where) {
        sWhere = where;
    }
    
    public String getError() {
        return error_msg;
    }
}
