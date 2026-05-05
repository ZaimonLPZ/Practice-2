import java.util.*;

public class MotorGramatica {
    private Map<String, List<String[]>> reglas = new HashMap<>();
    private Set<String> noTerminales = new HashSet<>();

    public MotorGramatica(String texto) {
        for (String linea : texto.split("\n")) {
            if (linea.contains("->")) {
                String[] partes = linea.split("->");
                String nt = partes[0].trim();
                noTerminales.add(nt);
                for (String opc : partes[1].split("\\|")) {
                    reglas.computeIfAbsent(nt, k -> new ArrayList<>())
                            .add(opc.trim().split("(?<=[-+*/()])|(?=[-+*/()])| "));
                }
            }
        }
    }

    public NodoArbol derivar(String inicial, String objetivo, boolean izq, List<String> historial) {
        NodoArbol raiz = new NodoArbol(inicial);
        if (buscar(raiz, objetivo, izq, historial)) return raiz;
        return null;
    }

    private boolean buscar(NodoArbol nodo, String objetivo, boolean izq, List<String> historial) {
        String actual = obtenerCadena(nodo);
        if (actual.equals(objetivo)) return true;
        if (actual.length() > objetivo.length() + 5) return false;

        NodoArbol objetivoNodo = encontrarSiguienteNT(nodo, izq);
        if (objetivoNodo == null) return false;

        String nt = objetivoNodo.valor;
        if (reglas.containsKey(nt)) {
            for (String[] produccion : reglas.get(nt)) {
                List<NodoArbol> nuevosHijos = new ArrayList<>();
                char charObjetivo = obtenerCaracterEnPosicion(nodo, objetivo, izq);

                for (String s : produccion) {
                    String simb = s.trim();
                    if (simb.isEmpty()) continue;
                    if (simb.equals("id") && Character.isLetter(charObjetivo)) {
                        nuevosHijos.add(new NodoArbol(String.valueOf(charObjetivo)));
                    } else if (simb.equals("num") && Character.isDigit(charObjetivo)) {
                        nuevosHijos.add(new NodoArbol(String.valueOf(charObjetivo)));
                    } else {
                        nuevosHijos.add(new NodoArbol(simb));
                    }
                }
                objetivoNodo.hijos = nuevosHijos;
                historial.add("⇒ " + obtenerCadena(nodo));
                if (buscar(nodo, objetivo, izq, historial)) return true;
                objetivoNodo.hijos = new ArrayList<>();
                historial.remove(historial.size() - 1);
            }
        }
        return false;
    }

    private char obtenerCaracterEnPosicion(NodoArbol raiz, String objetivo, boolean izq) {
        String actual = obtenerCadena(raiz);
        if (izq) {
            for (int i = 0; i < Math.min(actual.length(), objetivo.length()); i++) {
                if (noTerminales.contains(String.valueOf(actual.charAt(i)))) return objetivo.charAt(i);
            }
        } else {
            for (int i = actual.length() - 1; i >= 0; i--) {
                if (noTerminales.contains(String.valueOf(actual.charAt(i)))) {
                    int offset = actual.length() - 1 - i;
                    int posObj = objetivo.length() - 1 - offset;
                    return (posObj >= 0 && posObj < objetivo.length()) ? objetivo.charAt(posObj) : ' ';
                }
            }
        }
        return ' ';
    }

    private String obtenerCadena(NodoArbol nodo) {
        if (nodo.hijos.isEmpty()) return nodo.valor;
        StringBuilder sb = new StringBuilder();
        for (NodoArbol h : nodo.hijos) sb.append(obtenerCadena(h));
        return sb.toString();
    }

    private NodoArbol encontrarSiguienteNT(NodoArbol nodo, boolean izq) {
        if (noTerminales.contains(nodo.valor) && nodo.hijos.isEmpty()) return nodo;
        if (izq) {
            for (NodoArbol h : nodo.hijos) {
                NodoArbol res = encontrarSiguienteNT(h, izq);
                if (res != null) return res;
            }
        } else {
            for (int i = nodo.hijos.size() - 1; i >= 0; i--) {
                NodoArbol res = encontrarSiguienteNT(nodo.hijos.get(i), izq);
                if (res != null) return res;
            }
        }
        return null;
    }
}