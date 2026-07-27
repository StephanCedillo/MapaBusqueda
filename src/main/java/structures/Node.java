/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures;



/**
 *
 * @author stephancedillo
 */

public class Node<T> {
    private T value;
    private int x;
    private int y;
    private String estado;
    
    private final int RADIO_INTERSECCION = 20;

    //Creo un Node
    
    public Node(T value) {
        this.value = value;  
    }
    
    
    public Node(T value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        estado = "Create";
    }


    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
   

    @Override
    public String toString() {
        return " Nodo " + value + "";
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node<T> other = (Node<T>) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public boolean contiene(int mouseX, int mouseY) {

        int radioDelNodo = 20; // Ajusta esto al tamaño visual de tu nodo
        double distancia = Math.sqrt(Math.pow(this.x - mouseX, 2) + Math.pow(this.y - mouseY, 2));
        return distancia <= radioDelNodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
