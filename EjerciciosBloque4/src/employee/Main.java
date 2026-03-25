package employee;

public class Main {
    public static void main(String[] args) {
        // Empleado

        Employee empleado1 = new Employee(18, "Fabian", "Chipu", 50000);

        System.out.println("El id  del empleado es: " + empleado1.getId());
        System.out.println("El nombre completo del empleado es: " + empleado1.getName());
        System.out.println("El salario del empleado es: " + empleado1.getSalary());
        System.out.println("El salario anual del empleado es: " + empleado1.getAnnualSalary());

        // Nuevo salario
        empleado1.setSalary(100000);
        System.out.println("El nuevo salario del empleado es: " + empleado1.getSalary());

        // aumentar salrio
        empleado1.raiseSalary(10);
        System.out.println("El salario del empleado mas el 10% es: " + empleado1.getSalary());

    }
}
