package vista;

import controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que genera la interfaz gráfica.
 */
public class VentanaPrincipal extends JFrame {

    private Controlador controlador;
    private JTextArea areaTexto;
    private JButton botonEjecutar;

    public VentanaPrincipal(Controlador controlador) {
        this.controlador = controlador;

        // Configuración básica de la ventana
        setTitle("Smart TecnoHouse - Panel de Control");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar programa al darle a la X
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(new BorderLayout());

        // Area de texto central
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        // Pedimos al controlador los datos iniciales según los valores en el JSON
        String textoArranque = "Sistema Iniciado.\n\n" + controlador.obtenerEstadoInicial();
        areaTexto.setText(textoArranque);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        // Botón inferior que lanza el ciclo de alteración de datos de los sensores
        botonEjecutar = new JButton("Evaluar estado de Sensores");
        add(botonEjecutar, BorderLayout.SOUTH);

        // Conectar el botón con el Controlador
        botonEjecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recogemos el texto que nos devuelve el controlador
                String resultado = controlador.ejecutarCicloSimulacion();

                // Lo imprimimos en la pantalla de la ventana
                areaTexto.append(resultado);
                areaTexto.append("--> Ciclo completado.\n\n");

                // Esto hace que el área de texto haga scroll hacia abajo automáticamente
                areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
            }
        });
    }

    /**
     * Hacemos visible la ventana.
     */
    public void mostrar() {
        setVisible(true);
    }
}