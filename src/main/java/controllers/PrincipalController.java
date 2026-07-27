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
import structures.graphs.PathResult;
import structures.graphs.implementss.BFSPathFinder;
import structures.graphs.implementss.DFSPathFinder;
import views.*;

public class PrincipalController {

    private Interfaz principalView;
    private PanelMapaInteractivo panel;
    private NodeDAO daoNode;

    private final int TOLERANCIA_CLIC = 25;

    private final List<Conexion> conexionesPermitidas = List.of(
            new Conexion(new Point(475, 10), new Point(549, 23)),
            new Conexion(new Point(475, 10), new Point(463, 102)),
            new Conexion(new Point(549, 23), new Point(615, 47)),
            new Conexion(new Point(549, 23), new Point(532, 105)),
            new Conexion(new Point(615, 47), new Point(688, 63)),
            new Conexion(new Point(615, 47), new Point(605, 139)),
            new Conexion(new Point(688, 63), new Point(756, 81)),
            new Conexion(new Point(688, 63), new Point(673, 159)),
            new Conexion(new Point(756, 81), new Point(746, 173)),
            new Conexion(new Point(185, 13), new Point(258, 36)),
            new Conexion(new Point(185, 13), new Point(168, 102)),
            new Conexion(new Point(258, 36), new Point(332, 57)),
            new Conexion(new Point(258, 36), new Point(236, 135)),
            new Conexion(new Point(332, 57), new Point(389, 81)),
            new Conexion(new Point(332, 57), new Point(308, 151)),
            new Conexion(new Point(389, 81), new Point(463, 102)),
            new Conexion(new Point(389, 81), new Point(375, 175)),
            new Conexion(new Point(463, 102), new Point(532, 105)),
            new Conexion(new Point(463, 102), new Point(448, 190)),
            new Conexion(new Point(532, 105), new Point(605, 139)),
            new Conexion(new Point(532, 105), new Point(520, 206)),
            new Conexion(new Point(605, 139), new Point(673, 159)),
            new Conexion(new Point(605, 139), new Point(590, 233)),
            new Conexion(new Point(673, 159), new Point(746, 173)),
            new Conexion(new Point(673, 159), new Point(660, 248)),
            new Conexion(new Point(746, 173), new Point(734, 271)),
            new Conexion(new Point(22, 48), new Point(97, 71)),
            new Conexion(new Point(22, 48), new Point(13, 145)),
            new Conexion(new Point(97, 71), new Point(168, 102)),
            new Conexion(new Point(97, 71), new Point(82, 168)),
            new Conexion(new Point(168, 102), new Point(236, 135)),
            new Conexion(new Point(168, 102), new Point(162, 198)),
            new Conexion(new Point(236, 135), new Point(308, 151)),
            new Conexion(new Point(236, 135), new Point(223, 222)),
            new Conexion(new Point(308, 151), new Point(375, 175)),
            new Conexion(new Point(308, 151), new Point(302, 258)),
            new Conexion(new Point(375, 175), new Point(448, 190)),
            new Conexion(new Point(375, 175), new Point(369, 262)),
            new Conexion(new Point(448, 190), new Point(520, 206)),
            new Conexion(new Point(448, 190), new Point(433, 283)),
            new Conexion(new Point(520, 206), new Point(590, 233)),
            new Conexion(new Point(520, 206), new Point(502, 309)),
            new Conexion(new Point(590, 233), new Point(660, 248)),
            new Conexion(new Point(590, 233), new Point(582, 334)),
            new Conexion(new Point(660, 248), new Point(734, 271)),
            new Conexion(new Point(660, 248), new Point(647, 360)),
            new Conexion(new Point(734, 271), new Point(718, 380)),
            new Conexion(new Point(13, 145), new Point(82, 168)),
            new Conexion(new Point(82, 168), new Point(162, 198)),
            new Conexion(new Point(82, 168), new Point(69, 261)),
            new Conexion(new Point(162, 198), new Point(223, 222)),
            new Conexion(new Point(162, 198), new Point(144, 295)),
            new Conexion(new Point(223, 222), new Point(302, 258)),
            new Conexion(new Point(223, 222), new Point(213, 314)),
            new Conexion(new Point(302, 258), new Point(369, 262)),
            new Conexion(new Point(302, 258), new Point(286, 332)),
            new Conexion(new Point(369, 262), new Point(433, 283)),
            new Conexion(new Point(369, 262), new Point(350, 360)),
            new Conexion(new Point(433, 283), new Point(502, 309)),
            new Conexion(new Point(433, 283), new Point(424, 382)),
            new Conexion(new Point(502, 309), new Point(582, 334)),
            new Conexion(new Point(502, 309), new Point(491, 413)),
            new Conexion(new Point(582, 334), new Point(647, 360)),
            new Conexion(new Point(582, 334), new Point(563, 433)),
            new Conexion(new Point(647, 360), new Point(718, 380)),
            new Conexion(new Point(647, 360), new Point(633, 456)),
            new Conexion(new Point(718, 380), new Point(715, 481)),
            new Conexion(new Point(69, 261), new Point(144, 295)),
            new Conexion(new Point(69, 261), new Point(54, 363)),
            new Conexion(new Point(144, 295), new Point(213, 314)),
            new Conexion(new Point(144, 295), new Point(135, 380)),
            new Conexion(new Point(213, 314), new Point(286, 332)),
            new Conexion(new Point(213, 314), new Point(200, 405)),
            new Conexion(new Point(286, 332), new Point(350, 360)),
            new Conexion(new Point(286, 332), new Point(278, 428)),
            new Conexion(new Point(350, 360), new Point(424, 382)),
            new Conexion(new Point(350, 360), new Point(333, 458)),
            new Conexion(new Point(424, 382), new Point(491, 413)),
            new Conexion(new Point(424, 382), new Point(413, 473)),
            new Conexion(new Point(491, 413), new Point(563, 433)),
            new Conexion(new Point(491, 413), new Point(475, 503)),
            new Conexion(new Point(563, 433), new Point(633, 456)),
            new Conexion(new Point(563, 433), new Point(552, 528)),
            new Conexion(new Point(633, 456), new Point(715, 481)),
            new Conexion(new Point(633, 456), new Point(623, 552)),
            new Conexion(new Point(715, 481), new Point(693, 570)),
            new Conexion(new Point(54, 363), new Point(135, 380)),
            new Conexion(new Point(54, 363), new Point(45, 443)),
            new Conexion(new Point(135, 380), new Point(200, 405)),
            new Conexion(new Point(135, 380), new Point(114, 476)),
            new Conexion(new Point(200, 405), new Point(278, 428)),
            new Conexion(new Point(200, 405), new Point(185, 498)),
            new Conexion(new Point(278, 428), new Point(333, 458)),
            new Conexion(new Point(278, 428), new Point(256, 521)),
            new Conexion(new Point(333, 458), new Point(413, 473)),
            new Conexion(new Point(333, 458), new Point(325, 550)),
            new Conexion(new Point(413, 473), new Point(475, 503)),
            new Conexion(new Point(413, 473), new Point(392, 567)),
            new Conexion(new Point(475, 503), new Point(552, 528)),
            new Conexion(new Point(475, 503), new Point(464, 587)),
            new Conexion(new Point(552, 528), new Point(623, 552)),
            new Conexion(new Point(552, 528), new Point(535, 615)),
            new Conexion(new Point(623, 552), new Point(693, 570)),
            new Conexion(new Point(623, 552), new Point(607, 637)),
            new Conexion(new Point(693, 570), new Point(686, 658)),
            new Conexion(new Point(45, 443), new Point(114, 476)),
            new Conexion(new Point(45, 443), new Point(28, 543)),
            new Conexion(new Point(114, 476), new Point(185, 498)),
            new Conexion(new Point(114, 476), new Point(100, 567)),
            new Conexion(new Point(185, 498), new Point(256, 521)),
            new Conexion(new Point(185, 498), new Point(176, 592)),
            new Conexion(new Point(256, 521), new Point(325, 550)),
            new Conexion(new Point(256, 521), new Point(245, 617)),
            new Conexion(new Point(325, 550), new Point(392, 567)),
            new Conexion(new Point(325, 550), new Point(300, 639)),
            new Conexion(new Point(392, 567), new Point(464, 587)),
            new Conexion(new Point(392, 567), new Point(373, 676)),
            new Conexion(new Point(464, 587), new Point(535, 615)),
            new Conexion(new Point(535, 615), new Point(607, 637)),
            new Conexion(new Point(607, 637), new Point(686, 658)),
            new Conexion(new Point(28, 543), new Point(100, 567)),
            new Conexion(new Point(100, 567), new Point(176, 592)),
            new Conexion(new Point(176, 592), new Point(245, 617)),
            new Conexion(new Point(245, 617), new Point(300, 639)),
            new Conexion(new Point(300, 639), new Point(373, 676))
    );
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

       
        actualizarNodos();
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
                Node node = new Node<String>(textoIngresado, interseccionCercana.x, interseccionCercana.y);
                daoNode.crear(node);
                actualizarNodos();
         
                panel.agregarNodoVisual(node);

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
            System.out.println("Clic inválido: Fuera de intersección permitida.");
        }
    }

    private void configurarEventos() {
        configurarBotonCrear();
        configurarBotonConexion();
        configurarBotonCrearPredefinido();
        configurarBotonRecorridos();
        cofigurarBotonLimpiar();

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

                    if (esConexionPermitida(nodoA, nodoClickeado)) {
                        if (tipoConexion == 0) {
                            daoNode.uniConexion(nodoA, nodoClickeado);
                            panel.agregarConexion(new Conexion(
                                    new Point(nodoA.getX(), nodoA.getY()),
                                    new Point(nodoClickeado.getX(), nodoClickeado.getY())
                            ), false);
                        } else if (tipoConexion == 1) {
                            daoNode.biConexion(nodoA, nodoClickeado);
                            panel.agregarConexion(new Conexion(
                                    new Point(nodoA.getX(), nodoA.getY()),
                                    new Point(nodoClickeado.getX(), nodoClickeado.getY())
                            ), true);
                        }

                        panel.repaint();

                    } else {
                        JOptionPane.showMessageDialog(principalView,
                                "Esos nodos no pueden conectarse.");
                    }

                    int respuesta = JOptionPane.showConfirmDialog(
                            principalView,
                            "¿Deseas seguir conectando más nodos?",
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

    private boolean esConexionPermitida(Node<String> nodo1, Node<String> nodo2) {

        Point p1 = new Point(nodo1.getX(), nodo1.getY());
        Point p2 = new Point(nodo2.getX(), nodo2.getY());

        for (Conexion conexion : conexionesPermitidas) {

            if ((conexion.getA().equals(p1) && conexion.getB().equals(p2))
                    || (conexion.getA().equals(p2) && conexion.getB().equals(p1))) {

                return true;
            }
        }

        return false;
    }

    private void actualizarNodos() {
        
        principalView.getComboDestino().removeAllItems();
        principalView.getComboOrigen().removeAllItems();
        

        if (daoNode.listar().isEmpty()) {
            
            return;
        }

        for (Node node : daoNode.listar()) {
            principalView.getComboDestino().addItem(node);
            principalView.getComboOrigen().addItem(node);
           
        }

        
    }

    private void configurarBotonCrearPredefinido() {
           principalView.getBtnCrearMapa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPredefinido();
            } });
         
    }

    private void crearPredefinido() {
      
     

     if(!daoNode.listar().isEmpty()){
         return;
     }
     daoNode.crearPredefinidos();
        for (Node node : daoNode.listar()) {
            panel.agregarNodoVisual(node);
            
        }
        
        for (Conexion conexion : conexionesPermitidas) {
        
            Node<String> nodoA = buscarNodoPorPunto(conexion.getA());
            Node<String> nodoB = buscarNodoPorPunto(conexion.getB());

  
            if (nodoA != null && nodoB != null) {
               
                daoNode.biConexion(nodoA, nodoB);
                
               
                panel.agregarConexion(new Conexion(
                        new Point(nodoA.getX(), nodoA.getY()),
                        new Point(nodoB.getX(), nodoB.getY())
                ), true);
            }
        }
        actualizarNodos();
        panel.repaint();
    }
    private Node<String> buscarNodoPorPunto(Point p) {
        for (Node nodo : daoNode.listar()) {
           
            if (nodo.getX() == p.x && nodo.getY() == p.y) {
                return (Node<String>) nodo;
            }
        }
        return null;
    }

    private void configurarBotonRecorridos() {
     principalView.getBtnBFS().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recorrerBFS();
            } 

        
     });
      principalView.getBtnDFS().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recorrerDFS();
            } 

       
     });
      principalView.getBtnLimpiarR().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarRecorrido();
            } 

       
     });
    }

     private void recorrerBFS() {
         BFSPathFinder bfsPathFinder = new BFSPathFinder();
        PathResult<String> resultado = bfsPathFinder.find(daoNode.obtenerGrafo(),(Node) principalView.getComboOrigen().getSelectedItem(), (Node )principalView.getComboDestino().getSelectedItem());
        
         for (Node node : panel.getNodosColocados()) {
             for (String valorNodo : resultado.getVisitados()) {

                 if (valorNodo.equalsIgnoreCase(String.valueOf(node.getValue()))) {
                     node.setEstado("Visited");
                 }
             }
             for (String valorNodo : resultado.getPath()) {
                 if (valorNodo.equalsIgnoreCase(String.valueOf(node.getValue()))) {
                     node.setEstado("Path");
                 }
             }

         }
         
         principalView.getTxtRespuestas().setText(resultado.toString());
         
         panel.repaint();
         

     }
       private void recorrerDFS() {
           DFSPathFinder dfsPathFinder = new DFSPathFinder();
        PathResult<String> resultado = dfsPathFinder.find(daoNode.obtenerGrafo(),(Node) principalView.getComboOrigen().getSelectedItem(), (Node )principalView.getComboDestino().getSelectedItem());
        
       
         for (Node node : panel.getNodosColocados()) {
             for (String valorNodo : resultado.getVisitados()) {

                 if (valorNodo.equalsIgnoreCase(String.valueOf(node.getValue()))) {
                     node.setEstado("Visited");
                 }
             }
             for (String valorNodo : resultado.getPath()) {
                 if (valorNodo.equalsIgnoreCase(String.valueOf(node.getValue()))) {
                     node.setEstado("Path");
                 }
             }

         }
         
         principalView.getTxtRespuestas().setText(resultado.toString());
         
         panel.repaint();
        
       }
    private void cofigurarBotonLimpiar() {
        
    
    }

    private void limpiarRecorrido() {
         for (Node node : panel.getNodosColocados()) {
            node.setEstado("Create");
         }
                  principalView.getTxtRespuestas().setText("");
         panel.repaint();
   
    }
    
    
    
}
