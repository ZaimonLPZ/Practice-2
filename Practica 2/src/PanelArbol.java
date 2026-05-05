import javax.swing.*;
import java.awt.*;

public class PanelArbol extends JPanel {
    private NodoArbol raiz;
    public void setRaiz(NodoArbol r) { this.raiz = r; repaint(); }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            dibujarNodo(g2d, raiz, getWidth() / 2, 40, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics2D g, NodoArbol n, int x, int y, int offset) {
        if (!n.hijos.isEmpty()) {
            int inicioX = x - (n.hijos.size() - 1) * offset / 2;
            for (NodoArbol h : n.hijos) {
                g.setColor(Color.LIGHT_GRAY);
                g.drawLine(x, y, inicioX, y + 60);
                dibujarNodo(g, h, inicioX, y + 60, offset / 2);
                inicioX += offset;
            }
        }
        g.setColor(new Color(173, 216, 230));
        g.fillOval(x - 18, y - 18, 36, 36);
        g.setColor(Color.BLACK);
        g.drawOval(x - 18, y - 18, 36, 36);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(n.valor, x - fm.stringWidth(n.valor)/2, y + 5);
    }
}