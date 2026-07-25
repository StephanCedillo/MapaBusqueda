/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PanelMapaInteractivo extends JPanel {

    private Image imagenFondo;

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

    private final List<Point> nodosColocados = new ArrayList<>();

    private final int RADIO_INTERSECCION = 20;
    private final int TOLERANCIA_CLIC = 25;

    public PanelMapaInteractivo() {

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
                validarYColocarNodo(e.getPoint());
                System.out.println("new Point(" + e.getX() + ", " + e.getY() + "),");
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

            if (!nodosColocados.contains(interseccionCercana)) {
                nodosColocados.add(interseccionCercana);
                System.out.println("Nodo colocado en: " + interseccionCercana);
                repaint();
            }
        } else {
            System.out.println("Clic inválido: Fuera de intersección permitida.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (imagenFondo != null) {
            g2d.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }

        g2d.setColor(new Color(150, 150, 150, 100));
        for (Point p : interseccionesPermitidas) {
            g2d.drawOval(p.x - RADIO_INTERSECCION / 2, p.y - RADIO_INTERSECCION / 2, RADIO_INTERSECCION, RADIO_INTERSECCION);
        }

        g2d.setColor(Color.RED);
        for (Point p : nodosColocados) {
            int diametro = 24;
            g2d.fillOval(p.x - diametro / 2, p.y - diametro / 2, diametro, diametro);
        }
    }

}
