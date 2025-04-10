package utilidades;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0.1
 */

public class ConexionBD{
  Connection con = null;

    public ConexionBD() {

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ConexionDB");
            con = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }

    public Connection getConexion() {
        try {
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConexion() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
  public static String validarError(String sql,String error){
    String resultado = "";
    if (error.toUpperCase().indexOf("CANNOT ADD OR UPDATE A CHILD ROW") != -1){
	resultado = "Esta ingresando un valor no permitido";
    }
    else if (error.toUpperCase().indexOf("FOREIGN KEY") != -1){
      resultado = "Este codigo se encuentra asociado y no puede ser Eliminado.";
    }
    else if (error.toUpperCase().indexOf("DUPLICATE") != -1){
      resultado = "Este codigo ya se encuentra registrado en el Sistema.";
    }
    else{
      resultado = "Error SQL " + sql + "\n" + error;
    }
    return resultado;
  }
}
