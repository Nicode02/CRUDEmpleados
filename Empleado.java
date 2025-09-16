public class Empleado {
    int id;
    String nombre;
    String cargo;
    double salario;

    Empleado(int id, String nombre, String cargo, double salario){
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return
                id + "," + nombre + "," + cargo + "," + salario;

    }
}
