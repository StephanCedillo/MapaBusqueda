package memoria;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashSet;
import java.util.Set;

import structures.Node;
import structures.graphs.Graphs;

/**
 * @author stephancedillo
 */
public class NodeDAOArchivo implements NodeDAO {

    private File rutaDireccion;
    private Graphs<String> graph; 


    private static final int TAM_VALUE = 15;  
    private static final int TAM_ESTADO = 10; 
    
    
    private static final int TAM_REGISTRO = (TAM_VALUE * 2) + 4 + 4 + (TAM_ESTADO * 2);

    public NodeDAOArchivo() {
        this.graph = new Graphs<>();

        try {

            String home = System.getProperty("user.home"); 
            rutaDireccion = new File(home + File.separator + "Archivos" + File.separator + "Grafos");

            if (!rutaDireccion.exists()) {
                rutaDireccion.mkdirs();
            }


            File archivo = new File(rutaDireccion, "nodos.bin");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura: " + ex.getMessage());
        }
    }

    @Override
    public void crear(Node<String> node) {

        try (RandomAccessFile archivo = new RandomAccessFile(new File(rutaDireccion, "nodos.bin"), "rw")) {
            
            
            archivo.seek(archivo.length());

            String valuePadded = completarTexto(node.getValue(), TAM_VALUE);
            String estadoPadded = completarTexto(node.getEstado(), TAM_ESTADO);

          
            archivo.writeChars(valuePadded); 
            archivo.writeInt(node.getX());     
            archivo.writeInt(node.getY());     
            archivo.writeChars(estadoPadded);  

         
            graph.add(node);

        } catch (IOException e) {
            System.out.println("Error al crear: " + e.getMessage());
        }
    }

    @Override
    public Set<Node<String>> listar() {
        Set<Node<String>> listaNodos = new LinkedHashSet<>();
        File archivoFisico = new File(rutaDireccion, "nodos.bin");
        if (!archivoFisico.exists()) {
            return listaNodos;
        }
        try (RandomAccessFile archivo = new RandomAccessFile(new File(rutaDireccion, "nodos.bin"), "r")) {
            
          
            long totalRegistros = archivo.length() / TAM_REGISTRO;
            

            
            for (int i = 0; i < totalRegistros; i++) {
              
                archivo.seek(i * TAM_REGISTRO);

                
                String value = leerTexto(archivo, TAM_VALUE).trim();
                int x = archivo.readInt();
                int y = archivo.readInt();
                String estado = leerTexto(archivo, TAM_ESTADO).trim();

              
                Node<String> nodoRecuperado = new Node<>(value, x, y);
                nodoRecuperado.setEstado(estado);
                
                listaNodos.add(nodoRecuperado);
                
               
                graph.add(nodoRecuperado); 
            }

        } catch (IOException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return listaNodos;
    }

    @Override
    public void borrar(Node<String> node) {
        File archivoOriginal = new File(rutaDireccion, "nodos.bin");
        File archivoTemporal = new File(rutaDireccion, "temp.bin");
        boolean eliminado = false;

        try (RandomAccessFile lectura = new RandomAccessFile(archivoOriginal, "r"); 
             RandomAccessFile escritura = new RandomAccessFile(archivoTemporal, "rw")) {

            long totalRegistros = lectura.length() / TAM_REGISTRO;

            for (int i = 0; i < totalRegistros; i++) {
                lectura.seek(i * TAM_REGISTRO);

                String value = leerTexto(lectura, TAM_VALUE).trim();
                int x = lectura.readInt();
                int y = lectura.readInt();
                String estado = leerTexto(lectura, TAM_ESTADO).trim();

              
                if (value.equals(node.getValue())) {
                    eliminado = true;
                    continue; 
                }

          
                escritura.writeChars(completarTexto(value, TAM_VALUE));
                escritura.writeInt(x);
                escritura.writeInt(y);
                escritura.writeChars(completarTexto(estado, TAM_ESTADO));
            }

        } catch (IOException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

     
        if (eliminado) {
            if (archivoOriginal.delete()) {
                archivoTemporal.renameTo(archivoOriginal);
            }
            graph.remove(node);
        } else {
            archivoTemporal.delete();
        }
    }

   
    private String completarTexto(String texto, int tamaño) {
        if (texto == null) texto = "";
        
        if (texto.length() > tamaño) {
            return texto.substring(0, tamaño);
        }
        
        StringBuilder sb = new StringBuilder(texto);
        while (sb.length() < tamaño) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private String leerTexto(RandomAccessFile archivo, int tamaño) throws IOException {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < tamaño; i++) {
            texto.append(archivo.readChar());
        }
        return texto.toString();
    }

    // ==========================================
    // MÉTODOS DE LA INTERFAZ QUE NO USAN ARCHIVOS
    // ==========================================

    @Override
    public void uniConexion(Node<String> node, Node<String> node2) {
        graph.addEdgeUni(node, node2);
    }

    @Override
    public void biConexion(Node<String> node, Node<String> node2) {
        graph.addEdge(node, node2);
    }

    @Override
    public Graphs<String> obtenerGrafo() {
        return graph;
    }

     @Override
    public void crearPredefinidos() {


        crear(new Node<>("1", 475, 10));
        crear(new Node<>("2", 549, 23));
        crear(new Node<>("3", 615, 47));
        crear(new Node<>("4", 688, 63));
        crear(new Node<>("5", 756, 81));
        crear(new Node<>("6", 185, 13));
        crear(new Node<>("7", 258, 36));
        crear(new Node<>("8", 332, 57));
        crear(new Node<>("9", 389, 81));
        crear(new Node<>("10", 463, 102));
        crear(new Node<>("11", 532, 105));
        crear(new Node<>("12", 605, 139));
        crear(new Node<>("13", 673, 159));
        crear(new Node<>("14", 746, 173));
        crear(new Node<>("15", 22, 48));
        crear(new Node<>("16", 97, 71));
        crear(new Node<>("17", 168, 102));
        crear(new Node<>("18", 236, 135));
        crear(new Node<>("19", 308, 151));
        crear(new Node<>("20", 375, 175));
        crear(new Node<>("21", 448, 190));
        crear(new Node<>("22", 520, 206));
        crear(new Node<>("23", 590, 233));
        crear(new Node<>("24", 660, 248));
        crear(new Node<>("25", 734, 271));
        crear(new Node<>("26", 13, 145));
        crear(new Node<>("27", 82, 168));
        crear(new Node<>("28", 162, 198));
        crear(new Node<>("29", 223, 222));
        crear(new Node<>("30", 302, 258));
        crear(new Node<>("31", 369, 262));
        crear(new Node<>("32", 433, 283));
        crear(new Node<>("33", 502, 309));
        crear(new Node<>("34", 582, 334));
        crear(new Node<>("35", 647, 360));
        crear(new Node<>("36", 718, 380));
        crear(new Node<>("37", 69, 261));
        crear(new Node<>("38", 144, 295));
        crear(new Node<>("39", 213, 314));
        crear(new Node<>("40", 286, 332));
        crear(new Node<>("41", 350, 360));
        crear(new Node<>("42", 424, 382));
        crear(new Node<>("43", 491, 413));
        crear(new Node<>("44", 563, 433));
        crear(new Node<>("45", 633, 456));
        crear(new Node<>("46", 715, 481));
        crear(new Node<>("47", 54, 363));
        crear(new Node<>("48", 135, 380));
        crear(new Node<>("49", 200, 405));
        crear(new Node<>("50", 278, 428));
        crear(new Node<>("51", 333, 458));
        crear(new Node<>("52", 413, 473));
        crear(new Node<>("53", 475, 503));
        crear(new Node<>("54", 552, 528));
        crear(new Node<>("55", 623, 552));
        crear(new Node<>("56", 693, 570));
        crear(new Node<>("57", 45, 443));
        crear(new Node<>("58", 114, 476));
        crear(new Node<>("59", 185, 498));
        crear(new Node<>("60", 256, 521));
        crear(new Node<>("61", 325, 550));
        crear(new Node<>("62", 392, 567));
        crear(new Node<>("63", 464, 587));
        crear(new Node<>("64", 535, 615));
        crear(new Node<>("65", 607, 637));
        crear(new Node<>("66", 686, 658));
        crear(new Node<>("67", 28, 543));
        crear(new Node<>("68", 100, 567));
        crear(new Node<>("69", 176, 592));
        crear(new Node<>("70", 245, 617));
        crear(new Node<>("71", 300, 639));
        crear(new Node<>("72", 373, 676));
        
    }

    
@Override
    public void borrarTodo() {
        File archivo = new File(rutaDireccion, "nodos.bin");
        if (archivo.exists()) {
            archivo.delete(); 
        }
        graph = new Graphs<>(); 
    }
}