import java.util.LinkedList;
import java.util.Scanner;

class Misión {
    private String nombre;
    private boolean completada;
    private String fecha;

    public Misión(String nombre, boolean completada, String fecha) {
        this.nombre = nombre;
        this.completada = completada;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isCompletada() {
        return completada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

class Pila<T> {
    private LinkedList<T> elementos;

    public Pila() {
        elementos = new LinkedList<>();
    }

    public void agregar(T elemento) {
        elementos.addFirst(elemento);
    }

    public boolean esVacia() {
        return elementos.isEmpty();
    }

    public T quitar() {
        if (esVacia()) {
            return null;
        }
        return elementos.removeFirst();
    }
}

class DiarioAventurero {
    private Pila<Misión> pilaMisiones;

    public DiarioAventurero() {
        this.pilaMisiones = new Pila<>();
    }

    public void registrarMisión(String nombre, boolean completada, String fecha) {
        Misión misión = new Misión(nombre, completada, fecha);
        pilaMisiones.agregar(misión);
    }

    public void eliminarÚltimaMisión() {
        if (!pilaMisiones.esVacia()) {
            Misión misión = pilaMisiones.quitar();
            if (!misión.isCompletada()) {
                pilaMisiones.agregar(misión);
            }
        }
    }

    public void mostrarMisiones() {
        Pila<Misión> pilaTemporal = new Pila<>();
        while (!pilaMisiones.esVacia()) {
            Misión misión = pilaMisiones.quitar();
            System.out.println("Nombre: " + misión.getNombre() + ", Completada: " + misión.isCompletada() + ", Fecha: " + misión.getFecha());
            pilaTemporal.agregar(misión);
        }
        while (!pilaTemporal.esVacia()) {
            pilaMisiones.agregar(pilaTemporal.quitar());
        }
    }

    public boolean buscarMisión(String nombre) {
        Pila<Misión> pilaTemporal = new Pila<>();
        boolean encontrada = false;
        while (!pilaMisiones.esVacia()) {
            Misión misión = pilaMisiones.quitar();
            if (misión.getNombre().equals(nombre)) {
                encontrada = true;
            }
            pilaTemporal.agregar(misión);
        }
        while (!pilaTemporal.esVacia()) {
            pilaMisiones.agregar(pilaTemporal.quitar());
        }
        return encontrada;
    }

    public void editarMisión(String nombre, boolean completada, String fecha) {
        Pila<Misión> pilaTemporal = new Pila<>();
        while (!pilaMisiones.esVacia()) {
            Misión misión = pilaMisiones.quitar();
            if (misión.getNombre().equals(nombre)) {
                misión.setCompletada(completada);
                misión.setFecha(fecha);
            }
            pilaTemporal.agregar(misión);
        }
        while (!pilaTemporal.esVacia()) {
            pilaMisiones.agregar(pilaTemporal.quitar());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DiarioAventurero diario = new DiarioAventurero();
        boolean salir = false;

        while (!salir) {
            System.out.println("\nDiario Aventurero");
            System.out.println("1. Registrar misión");
            System.out.println("2. Eliminar última misión");
            System.out.println("3. Mostrar misiones");
            System.out.println("4. Buscar misión");
            System.out.println("5. Editar misión");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la misión: ");
                    String nombre = scanner.nextLine();
                    System.out.print("¿Completada? (true/false): ");
                    boolean completada = scanner.nextBoolean();
                    scanner.nextLine(); // Consumir nueva línea
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String fecha = scanner.nextLine();
                    diario.registrarMisión(nombre, completada, fecha);
                    break;
                case 2:
                    diario.eliminarÚltimaMisión();
                    System.out.println("Última misión eliminada si no estaba completada.");
                    break;
                case 3:
                    System.out.println("Misiones:");
                    diario.mostrarMisiones();
                    break;
                case 4:
                    System.out.print("Nombre de la misión a buscar: ");
                    nombre = scanner.nextLine();
                    boolean encontrada = diario.buscarMisión(nombre);
                    if (encontrada) {
                        System.out.println("La misión '" + nombre + "' ya fue completada.");
                    } else {
                        System.out.println("La misión '" + nombre + "' no fue encontrada o no está completada.");
                    }
                    break;
                case 5:
                    System.out.print("Nombre de la misión a editar: ");
                    nombre = scanner.nextLine();
                    System.out.print("¿Completada? (true/false): ");
                    completada = scanner.nextBoolean();
                    scanner.nextLine(); // Consumir nueva línea
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    fecha = scanner.nextLine();
                    diario.editarMisión(nombre, completada, fecha);
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 1 al 6.");
                    break;
            }
        }
        scanner.close();
        System.out.println("¡Gracias por usar el Diario Aventurero!");
    }
}
