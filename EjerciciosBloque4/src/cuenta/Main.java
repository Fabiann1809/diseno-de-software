package cuenta;

public class Main {
    public static void main(String[] args) {
        Cuenta c1 = new Cuenta("Alex");
        Cuenta c2 = new Cuenta("Maria", 500);

        System.out.println("Estado inicial:");
        System.out.println(c1);
        System.out.println(c2);

        // ingresar cantidad
        c1.ingresar(100);
        System.out.println("\nDespués de ingresar 100 el saldo de la cuenta de " + c1.getTitular()+ " es de: " + c1.getCantidad());

        // retirar
        c2.retirar(100);
        System.out.println("\nDespués de retirar 100 de la cuenta de " +  c2.getTitular() + ", su nuevo saldo es:" + c2.getCantidad());

        c2.retirar(1000);
        System.out.println(c2);
    }
}
