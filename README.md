[README.md](https://github.com/user-attachments/files/27384400/README.md)
# Practic II: Árboles de Derivación y Gramáticas

Este proyecto consiste en un Analizador Sintáctico 
(Parser) visual que permite realizar derivaciones por
izquierda y derecha basadas en una gramática libre de contexto
(CFG) proporcionada por el usuario. 
El sistema genera un árbol de análisis gráfico dinámico y 
muestra paso a paso la cadena derivada.
---

## Integrantes

| Nombre completo  | Rol |
|------------------|---|
| Simon Lopez Mesa | Desarrollador |

---

## Tecnologías

| Item | Detalle |
|---|---|
| **Lenguaje** | Java 21 |
| **Interfaz gráfica** | Java Swing |
| **Compilador** | OpenJDK 21 |
| **IDE** | IntelliJ IDEA|
| **Paradigma** | Programación Orientada a Objetos (POO) |

---

## Características Principales

**Derivación Flexible:** Soporta derivación por el extremo izquierdo (Leftmost) y extremo derecho (Rightmost).

**Árbol Gráfico:** Visualización en tiempo real con nodos circulares y conexiones jerárquicas.

**Identificación Inteligente:** Reconocimiento automático de terminales genéricos como id (letras) y num (dígitos 0-9).

**Interfaz Dinámica:** Panel de reglas editable para probar diferentes gramáticas sin reiniciar la aplicación.


---

## Uso de la aplicación

1. **Ejecucion** Ejecuta la clase Main.java desde tu IDE preferido.
  
2. **Configuración**: Define tus reglas en el panel izquierdo
   ```
   Ejemplo:
   E → E + T | E - T | T
   T → T * F | T / F | F
   F → ( E ) | id | num
   ```

3. **Entrada**: Escribe la expresión objetivo en el campo superior .
   ```
   Ejemplo:
   4-(w-x)/y
   ```
4. **Generación**: Selecciona
el tipo de derivación y presiona el botón "GENERAR".

---
## Clases principales (POO)

| Clase                   | Descripción |
|-------------------------|--|
| `Main.java`             | Punto de entrada que inicializa la aplicación. |
| `NodoArbol.java`        | Modelo: Estructura de datos para representar la jerarquía del árbol |
| `MotorGramatica.java`   | Controlador: Lógica de búsqueda (backtracking) y motor de derivación|
| `PanelArbol.java`       | Vista: Componente gráfico encargado del renderizado (Drawing) del árbol. |
| `VentanaGramatica.java` | Vista: Interfaz de usuario principal y gestión de eventos. |
