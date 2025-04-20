/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author ambar
 */
public class SistemaVentaReserva {
    
    static String[] asientos = {"VIP", "Platea Alta", "Platea Baja", "Palco", "General"};
    static int[] stockEntradas = {5,5,5,5,5};
    static int[] precios = {0,0,0,0,0};
    static int[] cantidades = {0,0,0,0,0};

    static final int VIP = 30000;
    static final int PLATEA_ALTA = 18000;
    static final int PLATEA_BAJA = 15000;
    static final int PALCO = 13000;
    static final int GENERAL = 20000;
    
    static int reservaIndex;
    static int cantidadReserva;
    static double descuento;
    static int precioEntradaTotal = 0;
    static int totalDescuento = 0;

    static int cantidadVentas = 0;
    static int totalTodasVentas = 0;




    static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        try { 
        int opcionMenu;
        
        System.out.println("Bienvenido al Teatro Moro");

        do {
            mostrarMenu();
            opcionMenu = scanner.nextInt();

            switch (opcionMenu) {
                case 1:
                    System.out.println("Usted selecciono hacer una reserva de entrada");
                    reservarEntradas(scanner);
                    
                    break;
                case 2:
                    System.out.println("Usted selecciono comprar entradas");
                    comprarEntrada();
                    break;
                case 3:
                    modificarVenta();
                    break;
                case 4:
                    imprimirBoleta();
                    break;
                case 5:
                    System.out.println("Gracias por visitar Teatro Moro");
                    break;
                default:
                    System.out.println("Opcion invalida. Ingrese otra opcion.");
            }
        } while (opcionMenu != 5);

        }catch (InputMismatchException e) {
                System.out.println("Error: seleccione una opcion valida" );
            } finally {
                System.out.println("Fin del programa.");
                scanner.close();
            }
    }
    
    public static void mostrarMenu() {
        
        System.out.println("\nPor favor elija una de las siguientes opciones:");
        System.out.println("1. Reservar entradas");
        System.out.println("2. Comprar entradas");
        System.out.println("3. Modificar venta");
        System.out.println("4. Imprimir boleta");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opcion: ");

    }
    
    public static void mostrarStockAsientos(){

        int totalAsientos = 0;
        for (int s : stockEntradas) {
            totalAsientos += s;
        }
        System.out.print("Stock total disponible: "+totalAsientos+" asientos");
    }
    
    static void reservarEntradas(Scanner scanner){
        
        if  (cantidadReserva != 0) {
            System.out.println("Usted ya tiene una reserva en curso, proceda a comprar.");       
        } else {
            mostrarStockAsientos();
            System.out.println("\nAsientos disponibles:");   

            for (int i = 0; i < asientos.length; i++) {
                System.out.println((i + 1)+". "+asientos[i]+ " - cantidad disponible "+stockEntradas[i]);
            }

            System.out.println("Seleccione la entrada que desea reservar (1-5): ");
            reservaIndex = scanner.nextInt() -1;

            if (reservaIndex < 0 || reservaIndex >= asientos.length) {
                System.out.println("Entrada Invalida"); 
                return;
            }

            System.out.println("Cuantas entradas desea reservar?"); 
            cantidadReserva = scanner.nextInt();

            if (cantidadReserva <= stockEntradas[reservaIndex]) {
                stockEntradas[reservaIndex] -= cantidadReserva;
                System.out.println("Usted reservo "+cantidadReserva+" entrada/s para "+asientos[reservaIndex]+ " exitosamente.");
            }else{
                System.out.println("No hay stock para esa cantidad"); 
            }
        }
              
    }
    
    static void seleccionarDescuento(Scanner scanner){
        
        System.out.println("Seleccione una de las siguientes opciones: 1. Publico general, 2. Estudiante, 3. Tercera edad ");
        int tipoCliente = scanner.nextInt();
        
        switch(tipoCliente) {
            case 1:
                System.out.println("Usted selecciono Publico general"); 
                descuento = 1; 
                
                break;
            case 2:
                System.out.println("Usted selecciono Estudiante"); 
                descuento = 0.9;
                
                break;
            case 3:
                System.out.println("Usted selecciono Tercera edad"); 
                descuento = 0.85;
                
                break;
            default:
                System.out.println("La opcion ingresada no es valida");
                break;
        }
                                      
    }

    static void calcularDescuento(int precioEntradaTotal, double descuento) {
        
        totalDescuento = (int) (precioEntradaTotal * descuento);
        System.out.println("El total a pagar es: "+totalDescuento);
    }
    
    static void comprarEntrada(){
        
        if (cantidadReserva <= 0) {
            System.out.println("\nUsted no tiene ninguna reserva");         
        } else {
            System.out.println("\nUsted tiene actualmente en reserva: "+cantidadReserva+ " asientos de "+asientos[reservaIndex]);
            
            switch (asientos[reservaIndex]) {

                case "VIP":
                    System.out.println("\nUsted reservo VIP");
                    precioEntradaTotal = VIP*cantidadReserva;
                    seleccionarDescuento(scanner);
                    calcularDescuento(precioEntradaTotal, descuento);
                    cantidades[cantidadVentas] = cantidadReserva;
                    precios[cantidadVentas] = totalDescuento;
                    cantidadReserva = 0;
                    cantidadVentas++;

                    break;

                case "Platea Alta":
                    System.out.println("\nUsted reservo platea alta");
                    precioEntradaTotal = PLATEA_ALTA*cantidadReserva;
                    seleccionarDescuento(scanner);
                    calcularDescuento(precioEntradaTotal, descuento);
                    cantidades[cantidadVentas] = cantidadReserva;
                    precios[cantidadVentas] = totalDescuento;
                    cantidadReserva = 0;
                    cantidadVentas++;

                    break;

                case "Platea Baja":
                    System.out.println("\nUsted reservo platea baja");
                    precioEntradaTotal = PLATEA_BAJA*cantidadReserva;
                    seleccionarDescuento(scanner);
                    calcularDescuento(precioEntradaTotal, descuento);
                    cantidades[cantidadVentas] = cantidadReserva;
                    precios[cantidadVentas] = totalDescuento;
                    cantidadReserva = 0;
                    cantidadVentas++;

                    break;

                case "Palco":
                    System.out.println("\nUsted reservo palco");
                    precioEntradaTotal = PALCO*cantidadReserva;
                    seleccionarDescuento(scanner);
                    calcularDescuento(precioEntradaTotal, descuento);
                    cantidades[cantidadVentas] = cantidadReserva;
                    precios[cantidadVentas] = totalDescuento;
                    cantidadReserva = 0;
                    cantidadVentas++;

                    break;

                case "General":
                    System.out.println("\nUsted reservo general");
                    precioEntradaTotal = GENERAL*cantidadReserva;
                    seleccionarDescuento(scanner);
                    calcularDescuento(precioEntradaTotal, descuento);
                    cantidades[cantidadVentas] = cantidadReserva;
                    precios[cantidadVentas] = totalDescuento;
                    cantidadReserva = 0;
                    cantidadVentas++;

                    break;
                    
                default:
                    System.out.println("\nDato ingresado no valido");
                    break;
            }
            totalTodasVentas += totalDescuento;
            System.out.println("\nEl total de todas sus entradas es: "+totalTodasVentas);
        }
        
    }

    static void modificarVenta(){

        int nuevoPrecio;

        if(cantidadVentas <= 0) {
            System.out.println("No hay ventas para modificar"); 
        } else {
            System.out.println("Usted selecciono -> Modificar venta");
            
            mostrarEntradasVendidas();

            System.out.println("Ingrese el numero de la venta que desea modificar: ");
            int ventaModificar = scanner.nextInt() - 1;
            
            if (ventaModificar < 0 || ventaModificar >= cantidadVentas) {
                System.out.println("Cantidad ingresada es invalida"); 
            } else {
                System.out.println("Ingrese la nueva cantidad de entradas: ");
                int nuevaCantidad = scanner.nextInt();

                stockEntradas[reservaIndex] += cantidades[ventaModificar];           
                                
                if (nuevaCantidad <= stockEntradas[reservaIndex]) {
                    
                    stockEntradas[reservaIndex] -= nuevaCantidad;

                    int valorNeto = precios[ventaModificar] / cantidades[ventaModificar];
                   
                    nuevoPrecio = valorNeto * nuevaCantidad;
                    precios[ventaModificar] = nuevoPrecio;
                    System.out.println(nuevoPrecio);

                    System.out.println("Total a pagar: "+ nuevoPrecio);

                    cantidades[ventaModificar] = nuevaCantidad;
                    System.out.println("Cantidad de entradas modificadas: "+cantidades[ventaModificar]);

                    totalTodasVentas = nuevoPrecio;
                        
                    } else {
                    System.out.println("No hay stock para esa cantidad"); 
                }
            }

        }

    }

    static void mostrarEntradasVendidas(){
        for (int i = 0; i < cantidadVentas; i++) {
            System.out.println((i + 1)+". Usted ha comprado "+cantidades[i]+" entradas para "+asientos[i]+" exitosamente.");
            System.out.println("Total entrada/s es: "+ precios[i]);  
        }
    }

    static void imprimirBoleta(){
        if(cantidadVentas <= 0) {
            System.out.println("No hay ventas para imprimir"); 
        } else {    
            System.out.println("Usted selecciono -> Imprimir boleta");
            System.out.println("Detalle de entradas compradas: ");

            mostrarEntradasVendidas();
                
        }

            int totalFinal = precios[0] + precios[1] + precios[2] + precios[3] + precios[4];
            System.out.println("El total de todas sus entradas es: "+totalFinal);
            
        }

}
