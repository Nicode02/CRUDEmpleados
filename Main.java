import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try{
            File archivoEmpleados = new File("empleados.txt");

            if(archivoEmpleados.createNewFile()){
                System.out.println("El archivo se ha guardado en " +archivoEmpleados.getAbsolutePath());
            } else {
                System.out.println("El archivo ya existe en " + archivoEmpleados.getAbsolutePath());
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n--- MENÚ CRUD PRODUCTOS ---");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Actualizar salario empleado");
            System.out.println("4. Eliminar empleado");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese la información del empleado separado por comas\nid,nombre,cargo,salario");
                        String empleado = sc.nextLine();
                        agregarEmpleado(arreglarFormato(empleado));
                        break;

                    case 2:
                        List<Empleado>lista = leerEmpleados();
                        if(lista.isEmpty()){
                            System.out.println("No hay empleados registrados");
                        }else {
                            System.out.println("\n Lista de empleados");
                            for(Empleado emp: lista){
                                System.out.println(emp.id + " | " + emp.nombre + " | " + emp.cargo + " | " + emp.salario);
                            }
                        }
                        break;

                    case 3:
                        actualizarSalario(sc);
                        break;

                    case 4:
                        eliminarEmpleado(sc);
                        break;

                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción no válida");
                }
            }catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
            }
        }
    }

    // Sobrescribe el archivo con la lista actualizada de empleados
    public static void sobrescribirArchivo(List<Empleado> empleados){
        String archivoEmpleados = "empleados.txt";
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivoEmpleados, false), StandardCharsets.UTF_8))){
            for (Empleado emp : empleados) {
                bw.write(emp.toString());
                bw.newLine();
                }
            }catch (IOException e) {
            System.out.println("Error al sobrescribir archivo: " + e.getMessage());
        }
    }

    // Escribir un empleado nuevo validando ID
    public static void agregarEmpleado(Empleado em){
        String archivoEmpleados = "empleados.txt";
        List<Empleado> empleados = leerEmpleados();

        for(Empleado emp: empleados){
            if(emp.id == em.id) {
                System.out.println("El ID " + em.id + " ya existe. No se agregó");
                return;
            }
        }

        try(BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivoEmpleados, true), StandardCharsets.UTF_8))){
            bw.write(em.toString());
            bw.newLine();
            System.out.println("El empleado se ha agregado en " + archivoEmpleados);
        } catch (IOException e){
            System.out.println("Error al agregar el empleado" + e.getMessage());
        }
    }

    // Convierte texto -> objeto Empleado
    public static Empleado arreglarFormato(String linea){
        try{
            String[] p = linea.split(",");
            if(p.length == 4) {
                return new Empleado(Integer.parseInt(p[0]),
                        p[1],
                        p[2],
                        Double.parseDouble(p[3]));
            }
        }catch (Exception e){
            System.out.println("Error al procesar la linea" + e.getMessage());
        }
        return null;
    }

    // Lee todos los empleados del archivo
    public static List<Empleado> leerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        File archivoEmpleados = new File("empleados.txt");
        if(!archivoEmpleados.exists()){
            return empleados;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEmpleados))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Empleado empleado = arreglarFormato(linea);
                if(empleado != null){
                    empleados.add(empleado);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    // Actualizar salario de un empleado
    public static void actualizarSalario(Scanner sc){
        System.out.print("Ingrese el id del empleado a actualizar: ");
        int idActualizar = Integer.parseInt(sc.nextLine());

        List<Empleado> empleados = leerEmpleados();
        Empleado empleadoEncontrado = null;

        for (Empleado emp:empleados){
            if (emp.id == idActualizar){
                empleadoEncontrado = emp;
                break;
            }
        }

        if (empleadoEncontrado != null){
            System.out.print("Ingrese el nuevo salario: ");
            double nuevoSalario = Double.parseDouble(sc.nextLine());
            empleadoEncontrado.salario = nuevoSalario;
            sobrescribirArchivo(empleados);
            System.out.println("Salario actualizado correctamente para el empleado con ID " + idActualizar);
        } else {
            System.out.println("El empleado con id " + idActualizar + " no existe");
        }
    }

    // Eliminar empleado por ID
    public static void eliminarEmpleado(Scanner sc){
        System.out.print("Ingrese el id del empleado a eliminar: ");
        int idEliminar = Integer.parseInt(sc.nextLine());

        List<Empleado> empleados = leerEmpleados();
        boolean eliminado = empleados.removeIf(emp -> emp.id == idEliminar);

        if (eliminado) {
            sobrescribirArchivo(empleados);
            System.out.println("Empleado eliminado correctamente");
        }else {
            System.out.println("El empleado con id " + idEliminar + " no existe");
        }
    }


}