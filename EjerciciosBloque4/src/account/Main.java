package account;

public class Main {
    public static void main(String[] args) {
        // Crear dos cuentas
        Account cuenta1 = new Account("123", "Gastos", 80);
        Account cuenta2 = new Account("321", "compras");

        // Probar getters
        System.out.println("ID de la cuenta1: " + cuenta1.getID());
        System.out.println("Nombre de la de cuenta1: " + cuenta1.getName());
        System.out.println("Balance de la cuenta1 es de: " + cuenta1.getBalance());

        // Probar credit() y debit()
        cuenta1.credit(100);
        System.out.println("Después del crédito: " + cuenta1);

        cuenta1.debit(50);
        System.out.println("Después del débito: " + cuenta1);

        //Proba fallo
        cuenta1.debit(500);
        System.out.println("Después de débito fallido: " + cuenta1);

        // Probar transferTo()
        System.out.println("\nEstado inicial antes de transferencia:");
        System.out.println(cuenta1);
        System.out.println(cuenta2);

        cuenta1.transferTo(cuenta2, 100);

        System.out.println("\nEstado final después de transferencia:");
        System.out.println(cuenta1);
        System.out.println(cuenta2);
    }

}
