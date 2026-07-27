/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import controllers.Conexion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import structures.Node;

public class PanelMapaInteractivo extends JPanel {

    private Image imagenFondo;

    private Map<Conexion, Boolean> conexiones;
    // Intersecciones válidas 
    private final List<Point> interseccionesPermitidas = Arrays.asList(
            new Point(22, 48),
            new Point(97, 71),
            new Point(168, 102),
            new Point(185, 13),
            new Point(258, 36),
            new Point(332, 57),
            new Point(389, 81),
            new Point(308, 151),
            new Point(375, 175),
            new Point(463, 102),
            new Point(448, 190),
            new Point(475, 10),
            new Point(549, 23),
            new Point(532, 105),
            new Point(531, 115),
            new Point(520, 206),
            new Point(530, 121),
            new Point(605, 139),
            new Point(615, 47),
            new Point(590, 233),
            new Point(660, 248),
            new Point(673, 159),
            new Point(688, 63),
            new Point(756, 81),
            new Point(746, 173),
            new Point(734, 271),
            new Point(718, 380),
            new Point(647, 360),
            new Point(633, 456),
            new Point(715, 481),
            new Point(693, 570),
            new Point(623, 552),
            new Point(607, 637),
            new Point(686, 658),
            new Point(535, 615),
            new Point(552, 528),
            new Point(563, 433),
            new Point(582, 334),
            new Point(502, 309),
            new Point(491, 413),
            new Point(475, 503),
            new Point(464, 578),
            new Point(373, 642),
            new Point(392, 567),
            new Point(413, 469),
            new Point(424, 382),
            new Point(433, 283),
            new Point(449, 193),
            new Point(371, 169),
            new Point(373, 169),
            new Point(369, 262),
            new Point(350, 360),
            new Point(333, 458),
            new Point(325, 550),
            new Point(300, 639),
            new Point(245, 617),
            new Point(256, 521),
            new Point(278, 428),
            new Point(286, 332),
            new Point(302, 258),
            new Point(315, 148),
            new Point(330, 46),
            new Point(236, 135),
            new Point(223, 222),
            new Point(213, 314),
            new Point(200, 405),
            new Point(185, 491),
            new Point(176, 592),
            new Point(100, 567),
            new Point(28, 543),
            new Point(45, 443),
            new Point(114, 476),
            new Point(135, 380),
            new Point(54, 363),
            new Point(69, 261),
            new Point(144, 295),
            new Point(162, 198),
            new Point(82, 168),
            new Point(13, 145),
            new Point(30, 45)
    );

    private  List<Node> nodosColocados = new ArrayList<>();
    private Map<Node, String> nodosDireccion= new HashMap<Node, String>();
    private final int RADIO_INTERSECCION = 20;

    public PanelMapaInteractivo() {
        conexiones = new HashMap<>();

        try {
            java.net.URL urlImagen = getClass().getResource("/mapa.png");

            if (urlImagen != null) {
                // ImageIO lee la imagen completamente antes de continuar
                imagenFondo = javax.imageio.ImageIO.read(urlImagen);
                System.out.println("¡Mapa cargado con éxito en memoria!");
            } else {
                System.err.println("ERROR: El archivo mapa.png no está en la ruta compilada.");
            }
        } catch (Exception e) {
            System.err.println("Error técnico leyendo la imagen: " + e.getMessage());
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getPoint());
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        Font fuenteTexto = new Font("Arial", Font.BOLD, 12);
        g2d.setFont(fuenteTexto);
        FontMetrics fm = g2d.getFontMetrics(fuenteTexto);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (imagenFondo != null) {
            g2d.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }

        
        g2d.setColor(new Color(150, 150, 150, 100));

        for (Point p : interseccionesPermitidas) {
            g2d.drawOval(p.x - RADIO_INTERSECCION / 2, p.y - RADIO_INTERSECCION / 2, RADIO_INTERSECCION, RADIO_INTERSECCION);
        }

        for (Node p : nodosColocados) {
            int diametro = 24;
            if(p.getEstado().equals("Path")){
                 g2d.setColor(Color.BLUE);
                
            }else if(p.getEstado().equals("Visited")){
                 g2d.setColor(Color.GREEN);
            } else{
                 g2d.setColor(Color.DARK_GRAY);
            };
           
            g2d.fillOval(p.getX() - diametro / 2, p.getY() - diametro / 2, diametro, diametro);

            String texto = String.valueOf(p.getValue());
            int textX = p.getX() - (fm.stringWidth(texto) / 2);
            int textY = p.getY() + (fm.getAscent() / 2) - 2;

            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, textX, textY);
        }
        
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.RED);

        for (Map.Entry<Conexion, Boolean> entry : conexiones.entrySet()) {

            Conexion c = entry.getKey();
            boolean bidireccional = entry.getValue();

            Point a = c.getA();
            Point b = c.getB();

            dibujarFlecha(g2d, a.x, a.y, b.x, b.y);

            if (bidireccional) {
                dibujarFlecha(g2d, b.x, b.y, a.x, a.y);
            }
        }

    }

    public void agregarNodoVisual(Node node) {
        if (!nodosColocados.contains(node)) {
            nodosColocados.add(node);
            this.repaint();
        }
    }

    public void agregarConexion(Conexion conexion, boolean direccion) {
        // false unidireccional
        // true bidireccional
        conexiones.put(conexion, direccion);
        repaint();
    }

   private void dibujarFlecha(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        // radio
        int radioNodo = 12; 

        // angulo linea
        double dy = y2 - y1;
        double dx = x2 - x1;
        double theta = Math.atan2(dy, dx);

        // punto inicio
        int x1Borde = (int) (x1 + radioNodo * Math.cos(theta));
        int y1Borde = (int) (y1 + radioNodo * Math.sin(theta));

        // punto final
        int x2Borde = (int) (x2 - radioNodo * Math.cos(theta));
        int y2Borde = (int) (y2 - radioNodo * Math.sin(theta));

        // liniecita
        g2d.drawLine(x1Borde, y1Borde, x2Borde, y2Borde);

        // punta flecha
        double phi = Math.toRadians(25);
        int barb = 15;

      
        double rho = theta + phi;
        for (int i = 0; i < 2; i++) {
            int x = (int) (x2Borde - barb * Math.cos(rho));
            int y = (int) (y2Borde - barb * Math.sin(rho));

            g2d.drawLine(x2Borde, y2Borde, x, y);

            rho = theta - phi;
        }
    }

    public Image getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(Image imagenFondo) {
        this.imagenFondo = imagenFondo;
    }

    public Map<Conexion, Boolean> getConexiones() {
        return conexiones;
    }

    public void setConexiones(Map<Conexion, Boolean> conexiones) {
        this.conexiones = conexiones;
    }

    public List<Node> getNodosColocados() {
        return nodosColocados;
    }

    public void setNodosColocados(List<Node> nodosColocados) {
        this.nodosColocados = nodosColocados;
    }

    public Map<Node, String> getNodosDireccion() {
        return nodosDireccion;
    }

    public void setNodosDireccion(Map<Node, String> nodosDireccion) {
        this.nodosDireccion = nodosDireccion;
    }
   
   
}
