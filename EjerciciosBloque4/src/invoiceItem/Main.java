package invoiceItem;

public class Main {
    public static void main(String[] args) {
        InvoiceItem item = new InvoiceItem("10", "Laptop", 2, 1500);

        System.out.println(item);

        System.out.println("Total: " + item.getTotal());

        // cambiamos la cantidad y precio
        item.setQty(3);
        item.setUnitPrice(1400);

        System.out.println(item);
        System.out.println("Nuevo total: " + item.getTotal());


    }
}
