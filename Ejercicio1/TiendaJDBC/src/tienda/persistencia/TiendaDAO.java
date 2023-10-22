
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;


public class TiendaDAO extends DAO{
    
    public void guardarProducto(Producto producto) throws Exception{
        try {
            if(producto==null){
                throw new Exception("Producto invalido");
            }
            String sql = "INSERT INTO producto (nombre,precio,codigo_fabricante) VALUES ('"+producto.getNombre()+"',"+producto.getPrecio()+","
                    +producto.getCodigoFabricante()+");";
            CUD(sql);
            System.out.println("Dato guardado con exito");
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificar(Producto producto) throws Exception{
        try {
            if(producto==null){
                throw new Exception("Producto invalido");
            }
            String sql = "UPDATE producto SET nombre='"+producto.getNombre()+"', precio="+producto.getPrecio()+",codigo_fabricante="+producto.getCodigoFabricante()+" WHERE codigo="+producto.getCodigo()+";";
            CUD(sql);
            System.out.println("Modificacion exitosa");
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection<Producto> buscar(int opcion) throws Exception{
        try{
            ArrayList<Producto> productos = new ArrayList<>();
            if(opcion==1){
                String sql= "SELECT * from producto;";
                R(sql);
                
                Producto producto=null;
                while(resultado.next()){
                    producto=new Producto();
                    producto.setCodigo(resultado.getInt(1));
                    producto.setNombre(resultado.getString(2));
                    producto.setPrecio(resultado.getDouble(3));
                    producto.setCodigoFabricante(resultado.getInt(4));
                    productos.add(producto);                
                }
            }else if (opcion==2){
                String sql= "SELECT nombre,precio from producto;";
                R(sql);
                Producto producto=null;
                while(resultado.next()){
                    producto=new Producto();
                    producto.setNombre(resultado.getString(1));
                    producto.setPrecio(resultado.getDouble(2));
                    productos.add(producto);                
                }
            }else if (opcion==3){
                String sql= "SELECT * from producto where precio> 120 and precio < 202;";
                R(sql);
                Producto producto=null;
                    while(resultado.next()){
                    producto=new Producto();
                    producto.setCodigo(resultado.getInt(1));
                    producto.setNombre(resultado.getString(2));
                    producto.setPrecio(resultado.getDouble(3));
                    producto.setCodigoFabricante(resultado.getInt(4));
                    productos.add(producto);                
                }
            }else if (opcion==4){
                String sql= "SELECT nombre from producto where nombre LIKE '%Portatil%';";
                R(sql);
                Producto producto=null;
                while(resultado.next()){
                    producto=new Producto();
                    producto.setNombre(resultado.getString(1));
                    productos.add(producto);                
                }
            }else if (opcion==5){
                String sql= "SELECT nombre, precio from producto ORDER BY precio ASC LIMIT 1;";
                R(sql);
                Producto producto=null;
                 while(resultado.next()){
                    producto=new Producto();
                    producto.setNombre(resultado.getString(1));
                    producto.setPrecio(resultado.getDouble(2));
                    productos.add(producto);                
                }
            }
            
            Desconectar();
            return productos;
        }catch(Exception e){
            e.printStackTrace();
            Desconectar();
            throw new Exception("Error del sistema");
        }
    }
}
