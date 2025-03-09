
import java.util.LinkedList;

public class Pila<T> {
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
