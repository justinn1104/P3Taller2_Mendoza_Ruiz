package Model;

import View.View_Mendoza_Ruiz;
import java.util.List;
import org.bson.Document;

/*
En Java, la interfaz Cloneable es una marca que indica que una clase permite la 
clonación de objetos. Si una clase no implementa Cloneable e intenta usar clone(), 
se lanzará una excepción CloneNotSupportedException.
*/
public interface Vehiculo_Mendoza_Ruiz extends Cloneable {
    /*
    Aquí, Cloneable permite que cualquier clase que implemente Vehiculo (como Auto) pueda ser clonada.
    */    
    //Metodos del Patron Prototype
    Vehiculo_Mendoza_Ruiz clonar();
    void mostrarInfo(View_Mendoza_Ruiz view, Document documento, String piezas, String detalle);

}
