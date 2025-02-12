package Model;

import java.util.HashMap;
import java.util.Map;

public class Prototype_Mendoza_Ruiz {
    private final Map<String, Vehiculo_Mendoza_Ruiz> prototipos = new HashMap<>();

    public void registrarPrototipo(String clave, Vehiculo_Mendoza_Ruiz vehiculo) {
        prototipos.put(clave, vehiculo);
    }

    public Vehiculo_Mendoza_Ruiz obtenerPrototipo(String clave) {
        if (prototipos.containsKey(clave)) {
            return prototipos.get(clave).clonar();
        } else {
            // Puedes lanzar una excepción o retornar null
            //throw new IllegalArgumentException("Prototipo con clave " + clave + " no encontrado.");
            // o retornar null y manejar el error fuera de esta clase
            return null;
        }
    }
}

