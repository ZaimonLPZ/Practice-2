import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaGramatica extends JFrame {
    private JTextArea txtReglas, txtPasos;
    private JTextField txtExp;
    private JRadioButton rbIzq;
    private PanelArbol panelArbol;

    public VentanaGramatica() {
        setTitle("PRACTICE III - Alexandra - EAFIT [POO Version]");
        setSize(1300, 800);
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

        panelArbol = new PanelArbol();
        panelArbol.setBackground(Color.WHITE);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(txtPasos), panelArbol);
        split.setDividerLocation(350);

        add(pSup, BorderLayout.NORTH);
        add(new JScrollPane(txtReglas), BorderLayout.WEST);
        add(split, BorderLayout.CENTER);

        btn.addActionListener(e -> {
            panelArbol.setRaiz(null);
            txtPasos.setText("");
            MotorGramatica motor = new MotorGramatica(txtReglas.getText());
            List<String> historial = new ArrayList<>(List.of("E"));
            String objetivo = txtExp.getText().replace(" ", "");
            NodoArbol res = motor.derivar("E", objetivo, rbIzq.isSelected(), historial);
            if (res != null) {
                txtPasos.setText(String.join("\n", historial));
                panelArbol.setRaiz(res);
            } else {
                txtPasos.setText("Error: No se pudo derivar.");
            }
        });
    }
}