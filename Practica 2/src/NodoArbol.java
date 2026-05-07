import java.util.*;

public class NodoArbol {
    public String valor;
    public List<NodoArbol> hijos = new ArrayList<>();

    public NodoArbol(String valor) {
        this.valor = valor;
    }


    public NodoArbol clonar() {
        NodoArbol copia = new NodoArbol(this.valor);
        for (NodoArbol hijo : this.hijos) {
            copia.hijos.add(hijo.clonar());
        }
        return copia;
    }
}
