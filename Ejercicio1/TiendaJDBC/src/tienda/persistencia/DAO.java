
package tienda.persistencia;

import java.sql.*;

public abstract class DAO {
    
    protected Connection conexion = null;
    protected Statement sentencia = null;
    protected ResultSet resultado = null;
    private final String USER ="root";
    private final String PASSWORD ="root";
    private final String DATABASE= "tienda";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    protected void Conectar() throws ClassNotFoundException, SQLException{
        try {
            Class.forName(DRIVER);
            String url="jdbc:mysql://localhost:3306/tienda?useSSL=false";
            conexion = DriverManager.getConnection(url,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
    
    protected void Desconectar() throws Exception{
        try {
            if(conexion!=null){conexion.close();}
            if(sentencia!=null){sentencia.close();}
            if(resultado!=null){resultado.close();}
        } catch (Exception e) {
            throw e;
        }
    }
    
    protected void CUD(String sql) throws Exception{
        try {
            Conectar();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        }finally{
            Desconectar();
        }
    }
    
    protected void R(String sql) throws Exception{
        try {
            Conectar();
            sentencia = conexion.createStatement();
            resultado=sentencia.executeQuery(sql);
        } catch (Exception e) {
            throw e;
        }
    }
}
