
package tienda.servicios;

import java.util.ArrayList;
import java.util.Scanner;
import tienda.persistencia.TiendaDAO;
import tienda.entidades.Producto;
public class ProductoService {
   Scanner leer = new Scanner(System.in);
   private TiendaDAO prod;
    public ProductoService() throws Exception {
        this.prod = new TiendaDAO();
    }
    
    public void menu() throws Exception {
        int opcion=9;
        while (opcion!=0){
            System.out.println("BIENVENIDO AL MENU Seleccione una opcion");
            System.out.println("1.Lista el nombre de todos los productos que hay en la tabla producto. ");
            System.out.println("2.Lista los nombres y los precios de todos los productos de la tabla producto. ");
            System.out.println("3.Listar aquellos productos que su precio esté entre 120 y 202. ");
            System.out.println("4.Buscar y listar todos los Portátiles de la tabla producto. ");
            System.out.println("5.Listar el nombre y el precio del producto más barato. ");
            System.out.println("6.Ingresar un producto a la base de datos. ");
            System.out.println("7.Ingresar un fabricante a la base de datos. ");
            System.out.println("8.Editar un producto con datos a elección. ");
            System.out.println("0.Salir. ");
            opcion = leer.nextInt();
        switch (opcion) {
            case 1:
                buscar(1);
                break;
            case 2:
                buscar(2);
                break;
            case 3:
                buscar(3);
                break;
            case 4:
               buscar(4);
               break;
            case 5:
                buscar(5);
                break;
            case 6:
                agregar();
                break;
            case 7:
                System.out.println("Funcionalidad en construccion");;
                break;
            case 8:
            editar();
            break;
            case 0:
                System.out.println("Muchas gracias");
                break;
            default:
                System.out.println("Valor incorrecto");
                break;
        }
        }   
    }
    
    private void buscar(int num) throws Exception{
        try {
            ArrayList<Producto> productos = (ArrayList<Producto>) prod.buscar(num);
            mostrarInfo(productos);
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void mostrarInfo(ArrayList<Producto> productos){
        for(Producto p: productos){
            System.out.println(p);
        } 
    }
    
    public void agregar() throws Exception{
        try {
           leer.nextLine();
        System.out.println("Ingrese el nombre del producto");
        String nombre = leer.next();
        System.out.println("Ingrese el precio del producto");
        Double precio = leer.nextDouble();
        System.out.println("Ingrese el codigo del fabricante(1-9)");
        int codigo = leer.nextInt();
        Producto producto = new Producto(nombre,precio,codigo);
        prod.guardarProducto(producto);
        } catch (Exception e) {
            throw e;
        }  
    }
    
    private void editar() throws Exception{
        try {
            System.out.println("Ingrese el codigo del producto a editar");
            int codigo = leer.nextInt();
            leer.nextLine();
            System.out.println("ingrese el nombre");
            String nombre = leer. nextLine();
            System.out.println("ingrese el precio");
            Double precio = leer. nextDouble();
            System.out.println("ingrese el codigo del fabricante");
            int codFabr = leer. nextInt();
            Producto productoActual = new Producto(codigo, nombre, precio, codFabr);
            prod.modificar(productoActual);
        } catch (Exception e) {
            throw e;
        }
    }
    
   
}
