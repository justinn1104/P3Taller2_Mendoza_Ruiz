package Model;

import View.View_Mendoza_Ruiz;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;  // Importación correcta para documentos de MongoDB

public class Auto_Mendoza_Ruiz implements Vehiculo_Mendoza_Ruiz {

    private String marca;
    private String modelo;

    public Auto_Mendoza_Ruiz(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public Vehiculo_Mendoza_Ruiz clonar() {
        return new Auto_Mendoza_Ruiz(this.marca, this.modelo);
    }

    @Override
    public void mostrarInfo(View_Mendoza_Ruiz view, Document documento, String piezas, String detalle) {
        System.out.println("Datos del auto Prototipo: --> " + marca + " " + modelo);

        // Definir los nombres de las columnas
        String[] columnNames = {"Codigo", "Marca", "Modelo", "Piezas", "Descripcion"};

        // Crear un modelo de tabla
        DefaultTableModel tdm = new DefaultTableModel(columnNames, 0);

        // Crear una fila con los datos del documento
        Object[] row = {
            documento.get("Clave"), // Ajustado para coincidir con la columna
            getMarca(), // Agrega la marca del auto
            getModelo(), // Agrega el modelo del auto
            piezas, // Mantén coherencia con los nombres de las columnas
            detalle,};

        // Agregar la fila a la tabla
        tdm.addRow(row);

        // Asignar el modelo a la tabla en la vista
        view.tbVehiculos.setModel(tdm);
    }
}
