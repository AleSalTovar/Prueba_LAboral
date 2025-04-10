/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import estructura.PaisTD;
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
public class ModeloPais {
    private String sWhere;
    private Vector<PaisTD> lpais;
    private int indice;
    private String error_msg;
    
    public ModeloPais() {
        sWhere = "";
        lpais = new Vector<PaisTD>();
        error_msg = "";
        indice = -1;
    }
    
    public boolean consultarRegistro() {
        ConexionBD conexion = new ConexionBD();
        boolean resultado = true;
        String sql = "";
        lpais = new Vector<PaisTD>();
        indice = -1;
        Connection con = conexion.getConexion();
        PreparedStatement stm = null;
        try {
            sql = "SELECT * FROM pais " + sWhere;
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PaisTD registro = new PaisTD();
                registro.setId_pais(rs.getInt(1));
                registro.setNombre_pais(rs.getString(2));
                registro.setDominio_pais(rs.getString(3));
                lpais.add(registro);
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

    public PaisTD obtenerRegistro() {
        return lpais.get(indice);
    }

    public boolean siguienteRegistro() {
        indice++;
        return indice < lpais.size();
    }

    public void setWhere(String where) {
        sWhere = where;
    }
    
    public String getError() {
        return error_msg;
    }
}
