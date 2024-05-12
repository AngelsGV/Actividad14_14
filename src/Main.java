// Utilizando la BD Empleados, crea un programa que muestre un listado con el nombre y la edad de los empleados,
// cuya edad se encuentra comprendida entre unos valores mínimo y máximo que introducirá el usuario.


//Muy parecida a la actividad anterior pero ahora nos pide que desde teclado introduzcamos un minimo y un máximo
//Y que estos delimiten los empleados que se han de coger de la tabla.

import java.util.Scanner;
import java.sql.*;
//importamos
public class Main {
    public static void main(String[] args) {

            //Declaramos las variables
            Connection con ;
            Statement sentencia;
            ResultSet rs;
            String sql;

            String url = "jdbc:mysql://localhost/Empresa";

            try{
                con = DriverManager.getConnection(url, "Pepe","12345"); //Cosas para que la conexión sea ok
                sentencia = con.createStatement();//creamos la conexión

                //Vamos a solicitar el max y min
                System.out.println("Introduzca la edad minima del empleado: ");
                int min = new Scanner(System.in).nextInt();
                System.out.println("Introduzca la edad máxima del empleado: ");
                int max = new Scanner(System.in).nextInt();

                //Momento sql
                sql = "SELECT nombre, edad FROM Empleados WHERE edad >= " + min
                        + " AND edad <= " + max;
                //Me ha llegado a fallar por no poner "bien" los espacios dentro de la sentencia...
                //Después del =
                //Mucho cuidado al escribirlo.


                rs = sentencia.executeQuery(sql);//para sacar los resultados
                System.out.println("Lista de empleados: ");

                int i = 1;//Defino este valor para que el resultado mostrado en pantalla sea más visual
                // y para saber cuantos empleados hay en esa tabla.
                while (rs.next()){
                    //Mientras haya más filas en la tabla:

                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    System.out.println("Empleado "+ i + ": " + nombre + ". Edad: " + edad);
                    i ++;
                }
                con.close(); //cerramos la conexión
            }catch (SQLException EX){
                System.out.println(EX);//saber porque falla si falla.
            }
        }
    }