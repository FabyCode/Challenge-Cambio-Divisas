import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Scanner lectura = new Scanner(System.in);
        var base = "";
        var tipoMoneda = "";
        var amountInUSD = 0.0;

        while(true) {
            System.out.println("[1] USD -> MXN");
            System.out.println("[2] MXN -> USD");
            System.out.println("[3] Moneda 1 -> Moneda 2");
            System.out.println("[0] Salir");
            System.out.println("Escriba el tipo de conversión que desea hacer: ");
            try {
                var busqueda = lectura.nextLine();
                if (busqueda.equalsIgnoreCase("0")) {
                    break;
                }
                switch (busqueda)
                {
                    case "1":
                        base = "USD";
                        tipoMoneda = "MXN";
                        System.out.println("Escriba la cantidad a cambiar: ");
                        amountInUSD = Double.parseDouble(lectura.nextLine());
                        break;
                    case "2":
                        base = "MXN";
                        tipoMoneda = "USD";
                        System.out.println("Escriba la cantidad a cambiar: ");
                        amountInUSD = Double.parseDouble(lectura.nextLine());
                        break;
                    case "3":
                        System.out.println("Escriba la moneda a cambiar: ");
                        base = lectura.nextLine();
                        System.out.println("Escriba la cantidad a cambiar: ");
                        amountInUSD = Double.parseDouble(lectura.nextLine());
                        System.out.println("Escriba la moneda a la cual cambiar: ");
                        tipoMoneda = lectura.nextLine();
                        break;
                }

                Request request = new Request();
                Moneda moneda = request.convertir(base);

                double conversionRate = moneda.conversion_rates().get(tipoMoneda);

                double amountInMXN = convert(amountInUSD, conversionRate); // Aquí se realiza la conversión llamando a la funcion convert

                System.out.println(amountInUSD + " " + base + " es equivalente a " + amountInMXN + " " + tipoMoneda);

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Terminando...");
                break;
            }
        }
    }

    private static double convert(double amount, double conversionRate) {
        return amount * conversionRate;
    }
}