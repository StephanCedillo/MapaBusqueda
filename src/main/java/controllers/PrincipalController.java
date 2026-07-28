package controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import memoria.NodeDAO;
import structures.Node;
import views.*;

public class PrincipalController {
    private Interfaz principalView;
    private PanelMapaInteractivo panel;
    private NodeDAO daoNode;

    private final int TOLERANCIA_CLIC = 25;

    // Intersecciones válidas
    private final List<Point> interseccionesPermitidas = Arrays.asList(
            new Point(22, 48), new Point(97, 71), new Point(168, 102),
            new Point(185, 13), new Point(258, 36), new Point(332, 57),
            new Point(389, 81), new Point(308, 151), new Point(375, 175),
            new Point(463, 102), new Point(448, 190), new Point(475, 10),
            new Point(549, 23), new Point(532, 105), new Point(531, 115),
            new Point(520, 206), new Point(530, 121), new Point(605, 139),
            new Point(615, 47), new Point(590, 233), new Point(660, 248),
            new Point(673, 159), new Point(688, 63), new Point(756, 81),
            new Point(746, 173), new Point(734, 271), new Point(718, 380),
            new Point(647, 360), new Point(633, 456), new Point(715, 481),
            new Point(693, 570), new Point(623, 552), new Point(607, 637),
            new Point(686, 658), new Point(535, 615), new Point(552, 528),
            new Point(563, 433), new Point(582, 334), new Point(502, 309),
            new Point(491, 413), new Point(475, 503), new Point(464, 578),
            new Point(373, 642), new Point(392, 567), new Point(413, 469),
            new Point(424, 382), new Point(433, 283), new Point(449, 193),
            new Point(371, 169), new Point(373, 169), new Point(369, 262),
            new Point(350, 360), new Point(333, 458), new Point(325, 550),
            new Point(300, 639), new Point(245, 617), new Point(256, 521),
            new Point(278, 428), new Point(286, 332), new Point(302, 258),
            new Point(315, 148), new Point(330, 46), new Point(236, 135),
            new Point(223, 222), new Point(213, 314), new Point(200, 405),
            new Point(185, 491), new Point(176, 592), new Point(100, 567),
            new Point(28, 543), new Point(45, 443), new Point(114, 476),
            new Point(135, 380), new Point(54, 363), new Point(69, 261),
            new Point(144, 295), new Point(162, 198), new Point(82, 168),
            new Point(13, 145), new Point(30, 45)
    );
    
    private final String[] opciones = {"Unidireccion", "Bidireccion"};
    
    private int tipoConexion;
    private Node<String> nodoA;
    
    private boolean modoCrearNodo = false;
    private boolean modoConectar = false;

    public PrincipalController(Interfaz principalView, PanelMapaInteractivo panel, NodeDAO daoNode) {
        this.principalView = principalView;
        this.daoNode = daoNode;
        this.panel = panel;

        configurarEventos();
        configurarListenerMapaUnico();
    }

    public void configurarBotonCrear() {
        principalView.getBtnCrearNodo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modoCrearNodo = true;
                modoConectar = false;
                nodoA = null;
                principalView.getLblMensajeSeleccion().setText("");
                JOptionPane.showMessageDialog(principalView, "Modo creación activado. Haz clic en las intersecciones del mapa.");
            }
        });
    }

    private void validarYColocarNodo(Point puntoClic) {
        Point interseccionCercana = null;

        for (Point p : interseccionesPermitidas) {
            double distancia = puntoClic.distance(p);
            if (distancia <= TOLERANCIA_CLIC) {
                interseccionCercana = p;
                break;
            }
        }

        if (interseccionCercana != null) {
            String textoIngresado = JOptionPane.showInputDialog(
                    principalView, "Ingresa el nombre del nodo:", "Crear Nodo", JOptionPane.QUESTION_MESSAGE
            );

            if (textoIngresado != null && !textoIngresado.trim().isEmpty()) {
                daoNode.crear(new Node<String>(textoIngresado, interseccionCercana.x, interseccionCercana.y));
                panel.agregarNodoVisual(interseccionCercana);

                System.out.println("Nodo colocado en: " + interseccionCercana);

                int respuesta = JOptionPane.showConfirmDialog(
                        principalView, 
                        "Nodo creado exitosamente. ¿Deseas seguir creando más nodos?", 
                        "Continuar", 
                        JOptionPane.YES_NO_OPTION 
                );

                if (respuesta == JOptionPane.NO_OPTION) {
                    modoCrearNodo = false;
                }
            }
        } else {
            
            JOptionPane.showMessageDialog( principalView, "Clic inválido: Fuera de intersección permitida.");

    }      
 }

    private void configurarEventos() {
        configurarBotonCrear();
        configurarBotonConexion();
    }
    
    public void configurarBotonConexion() {
        principalView.getBtnConectarNodo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seleccion = JOptionPane.showOptionDialog(
                    principalView,
                    "¿Qué conexion deseas elegir?", 
                    "Selección de conexion",    
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE,   
                    null, 
                    opciones, 
                    opciones[0] 
                );
                
                if (seleccion == -1) {
                    modoConectar = false;
                    principalView.getLblMensajeSeleccion().setText("");
                    return;
                }

                tipoConexion = seleccion;
                modoConectar = true;
                modoCrearNodo = false;
                nodoA = null; 
                principalView.getLblMensajeSeleccion().setText("Selecciona el Punto 1");
            }
        });
    }

    private void configurarListenerMapaUnico() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modoCrearNodo) {
                    validarYColocarNodo(e.getPoint());
                    return;
                }

                if (!modoConectar) {
                    return;
                }
                
                Node<String> nodoClickeado = null;
                
                for (Node<String> nodo : daoNode.listar()) {
                    if (nodo.contiene(e.getX(), e.getY())) {
                        nodoClickeado = nodo;
                        break;
                    }
                }
                
                if (nodoClickeado == null) {
                    return; 
                }

                if (nodoA == null) {
                    nodoA = nodoClickeado;
                    principalView.getLblMensajeSeleccion().setText("Selecciona el Punto 2");
                } else if (nodoA != nodoClickeado) {
                    if (tipoConexion == 0) {
                        daoNode.uniConexion(nodoA, nodoClickeado);
                    } else if (tipoConexion == 1) {
                        daoNode.biConexion(nodoA, nodoClickeado);
                    }
                    
                    panel.repaint();

                    int respuesta = JOptionPane.showConfirmDialog(
                            principalView, 
                            "Conexión creada exitosamente. ¿Deseas seguir conectando más nodos?", 
                            "Continuar", 
                            JOptionPane.YES_NO_OPTION
                    );
                    
                    if (respuesta == JOptionPane.NO_OPTION) {
                        modoConectar = false;
                        nodoA = null;
                        principalView.getLblMensajeSeleccion().setText("");
                    } else {
                        nodoA = null;
                        principalView.getLblMensajeSeleccion().setText("Selecciona el Punto 1");
                    }
                } else {
                    JOptionPane.showMessageDialog(principalView, "No puedes seleccionar el mismo nodo.");
                    nodoA = null;
                    principalView.getLblMensajeSeleccion().setText("Selecciona el Punto 1");
                }
            }
        });
    }
}