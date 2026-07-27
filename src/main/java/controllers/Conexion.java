/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.Point;

/**
 *
 * @author stephancedillo
 */
public class Conexion {

    private Point a;
    private Point b;

    public Conexion(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public boolean conecta(Point p1, Point p2) {
        return (a.equals(p1) && b.equals(p2))
            || (a.equals(p2) && b.equals(p1));
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }
    
    
}
