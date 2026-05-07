import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaGramatica extends JFrame {
    private JTextArea txtReglas, txtPasos;
    private JTextField txtExp;
    private JRadioButton rbIzq;
    private PanelArbol panelArbolDerivacion;
    private PanelArbol panelAST;

    public VentanaGramatica() {
        setTitle("PRACTICE III - Alexandra - EAFIT [Con AST]");
        setSize(1400, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        txtReglas = new JTextArea("E -> E+T | E-T | T\nT -> T*F | T/F | F\nF -> (E) | id | num");
        txtPasos = new JTextArea();
        txtPasos.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtExp = new JTextField("4-(w-x)/y", 20);
        rbIzq = new JRadioButton("Izquierda", true);
        JRadioButton rbDer = new JRadioButton("Derecha");
        ButtonGroup bg = new ButtonGroup(); bg.add(rbIzq); bg.add(rbDer);
        JButton btn = new JButton("GENERAR");

        JPanel pSup = new JPanel();
        pSup.add(new JLabel("Expresión:")); pSup.add(txtExp); pSup.add(rbIzq); pSup.add(rbDer); pSup.add(btn);


        panelArbolDerivacion = new PanelArbol();
        panelArbolDerivacion.setBackground(new Color(250, 250, 250));
        panelArbolDerivacion.setBorder(BorderFactory.createTitledBorder("Árbol de Derivación (Parse Tree)"));

        panelAST = new PanelArbol();
        panelAST.setBackground(Color.WHITE);
        panelAST.setBorder(BorderFactory.createTitledBorder("Árbol de Sintaxis Abstracta (AST)"));


        JSplitPane splitArboles = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelArbolDerivacion, panelAST);
        splitArboles.setDividerLocation(400);


        JSplitPane splitPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(txtPasos), splitArboles);
        splitPrincipal.setDividerLocation(300);

        add(pSup, BorderLayout.NORTH);
        add(new JScrollPane(txtReglas), BorderLayout.WEST);
        add(splitPrincipal, BorderLayout.CENTER);

        btn.addActionListener(e -> {
            panelArbolDerivacion.setRaiz(null);
            panelAST.setRaiz(null);
            txtPasos.setText("");

            MotorGramatica motor = new MotorGramatica(txtReglas.getText());
            List<String> historial = new ArrayList<>(List.of("E"));
            String objetivo = txtExp.getText().replace(" ", "");

            NodoArbol res = motor.derivar("E", objetivo, rbIzq.isSelected(), historial);

            if (res != null) {
                txtPasos.setText(String.join("\n", historial));
                panelArbolDerivacion.setRaiz(res); // Muestra el árbol normal


                NodoArbol ast = motor.generarAST(res);
                panelAST.setRaiz(ast);

            } else {
                txtPasos.setText("Error: No se pudo derivar.");
            }
        });
    }
}
