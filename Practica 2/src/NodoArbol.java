import java.util.*;

public class NodoArbol {
    public String valor;
    public List<NodoArbol> hijos = new ArrayList<>();
    public NodoArbol(String valor) { this.valor = valor; }
}