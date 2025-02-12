package Model;

import java.util.ArrayList;
import java.util.List;

public class AdapterModificacionAuto_Mendoza_Ruiz implements ModificacionVehiculo_Mendoza_Ruiz{
    private final Auto_Mendoza_Ruiz auto;
    private final List<String> modificaciones;

    public AdapterModificacionAuto_Mendoza_Ruiz(Auto_Mendoza_Ruiz auto, List<String> modificaciones) {
        this.auto = auto;
        this.modificaciones = new ArrayList<>();
    }
    
    @Override
    public void agregarModificacion(String piezas, String descripcion) {
        modificaciones.add("Pieza: " + piezas + " | Modificación: " + descripcion);
    }

    @Override
    public void mostrarModificaciones() {
        System.out.println("\nModificaciones realizadas en el auto " + auto.getModelo() + ":");
        for (String mod : modificaciones) {
            System.out.println("Adaptar ->" + mod);
        }
    }

    

}
