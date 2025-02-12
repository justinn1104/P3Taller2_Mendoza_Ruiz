package Controller;

import Model.AdapterModificacionAuto_Mendoza_Ruiz;
import Model.Auto_Mendoza_Ruiz;
import Model.Model_Mendoza_Ruiz;
import Model.Prototype_Mendoza_Ruiz;
import Model.Vehiculo_Mendoza_Ruiz;
import View.View_Mendoza_Ruiz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

public class Controller_Mendoza_Ruiz implements ActionListener {

    protected Model_Mendoza_Ruiz model;
    protected View_Mendoza_Ruiz view;
    private final Prototype_Mendoza_Ruiz gestor;
    protected List<Document> documents;
    protected Auto_Mendoza_Ruiz vehiculo;

    public Controller_Mendoza_Ruiz(Model_Mendoza_Ruiz model, View_Mendoza_Ruiz view) {
        this.model = model;
        this.view = view;
        this.gestor = new Prototype_Mendoza_Ruiz();
        this.view.btnAdaptar.addActionListener(this);
        this.view.btnBuscarClave.addActionListener(this);
        this.view.btnRegistrar.addActionListener(this);
        this.vaciarValidadores();
        this.vaciarTabla();
        view.tbVehiculos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Comprobar que la selección no esta ajustando
                    int row = view.tbVehiculos.getSelectedRow(); // Obtener la fila seleccionada
                    if (row != -1) { // Verificar que se ha seleccionado una fila
                        // Obtener los valores de la fila seleccionada
                        String clave = view.tbVehiculos.getValueAt(row, 0).toString();
                        String marca = view.tbVehiculos.getValueAt(row, 1).toString();
                        String modelo = view.tbVehiculos.getValueAt(row, 2).toString();
                        String piezas = view.tbVehiculos.getValueAt(row, 3).toString();
                        String descripcion = view.tbVehiculos.getValueAt(row, 4).toString();
                        // Dividir la cadena por ", " y convertir a ArrayList
                        ArrayList<String> listaPiezas = new ArrayList<>(Arrays.asList(piezas.split(", ")));
                        // Colocar los valores en los campos de texto
                        model.setCodigo(clave);
                        model.setMarca(marca);
                        model.setModelo(modelo);
                        model.setPiezas(listaPiezas);
                        model.setDetalle(descripcion);
                    }
                }
            }
        });
    }

    public void vaciarTabla() {
        DefaultTableModel model1 = (DefaultTableModel) view.tbVehiculos.getModel();
        model1.setRowCount(0); // Esto vacía todas las filas

    }

    public void iniciarView() {
        view.setVisible(true);
    }

    public void vaciarValidadores() {
        view.errClave.setVisible(false);
        view.errClaveSearch.setVisible(false);
        view.errDescripcion.setVisible(false);
        view.errMarca1.setVisible(false);
        view.errModelo.setVisible(false);
        view.errPiezas.setVisible(false);
    }

    public void vaciarCampos() {
        view.txtClave.setText("");
        view.txtMarca.setText("");
        view.txtModelo.setText("");
    }

    public boolean validarPanelIngresoDeDatos() {
        boolean band = true;
        // Expresión regular para validar solo letras y espacios
        String soloLetrasRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        // Validar campos vacíos y restricciones
        if (model.getMarca().isEmpty()) {
            view.errMarca1.setText("Ingrese la marca del vehículo.");
            view.errMarca1.setVisible(true);
            band = false;
        } else if (!model.getMarca().matches(soloLetrasRegex)) {
            view.errMarca1.setText("La marca solo debe contener letras.");
            view.errMarca1.setVisible(true);
            band = false;
        }else{
            view.errMarca1.setVisible(false);
        }

        if (model.getModelo().isEmpty()) {
            view.errModelo.setText("Ingrese el modelo del vehículo.");
            view.errModelo.setVisible(true);
            band = false;
        } else if (!model.getModelo().matches(soloLetrasRegex)) {
            view.errModelo.setText("El modelo solo debe contener letras.");
            view.errModelo.setVisible(true);
            band = false;
        }else {
            view.errModelo.setVisible(false);
        }

        if (model.getCodigo().isEmpty()) {
            view.errClave.setText("Ingrese el código del vehículo.");
            view.errClave.setVisible(true);
            band = false;
        } else {
            view.errClave.setVisible(false);
        }
        // Si todos los campos están vacíos, mostrar un mensaje general
        if (model.getMarca().isEmpty() && model.getModelo().isEmpty() && model.getCodigo().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            vaciarValidadores();
            band = false; // Detener la ejecución
        }
        return band;
    }

    public void registrarObjetos() {
        // Manejar posibles errores al convertir los valores ingresados
        System.out.println("\nDATOS DEL OBJETO\n");
        model.setMarca(view.txtMarca.getText());
        model.setModelo(view.txtModelo.getText());
        model.setCodigo(view.txtClave.getText());
        boolean band = validarPanelIngresoDeDatos();
        if (band) {
            System.out.println("Datos Ingresados : --> Marca  = " + model.getMarca()
                + "\n                       Modelo = " + model.getModelo()
                + "\n                       Clave  = " + model.getCodigo());
            // Uso de Prototype
            System.out.println("\nUSO DE PATRON PROTOTYPE\n");
            gestor.registrarPrototipo(model.getCodigo(), new Auto_Mendoza_Ruiz(model.getMarca(), model.getModelo()));
            vaciarCampos();
        } else {
            System.out.println("\nERROR: Al validar campos del panel de datos princiales\n");
        }
    }
    
    public boolean validarYGuardarSeleccion() {
        List<String> piezas = new ArrayList<>();
        int contadorSeleccionados = 0;
        boolean band = true;
        // Recorremos los checkboxes y verificamos cuántos están seleccionados
        if (view.checkLlantas.isSelected()) {
            piezas.add(view.checkLlantas.getText());
            contadorSeleccionados++;
        }
        if (view.checkFaros.isSelected()) {
            piezas.add(view.checkFaros.getText());
            contadorSeleccionados++;
        }
        if (view.checkSuspension.isSelected()) {
            piezas.add(view.checkSuspension.getText());
            contadorSeleccionados++;
        }
        if (view.checkVocinas.isSelected()) {
            piezas.add(view.checkVocinas.getText());
            contadorSeleccionados++;
        }
        if (view.checkTurbo.isSelected()) {
            piezas.add(view.checkTurbo.getText());
            contadorSeleccionados++;
        }

        // Validamos si al menos 2 checkboxes están seleccionados
        if (contadorSeleccionados >= 2) {
            // Si la validación es exitosa, mostramos el listado de las piezas seleccionadas
            System.out.println("Piezas seleccionadas: " + piezas);
            // Aquí puedes hacer algo más con la lista de piezas, como guardarla en el modelo
            model.setPiezas((ArrayList<String>) piezas);
            view.errPiezas.setVisible(false);
        } else {
            // Si no hay al menos 2 seleccionados, mostramos un mensaje de error
            System.out.println("ERROR: Debes seleccionar al menos 2 piezas.");
            view.errPiezas.setText("Seleccionar : 2-5");
            view.errPiezas.setVisible(true);
            band = false;
        }
        
        if (model.getDetalle().isEmpty()) {
            view.errDescripcion.setText("Ingrese Informacion de la adapcation.");
            view.errDescripcion.setVisible(true);
            band = false;
        } else {
            view.errDescripcion.setVisible(false);
        }
        
        return band;
    }

    public void adaptarObjetos() {
        //validar piezas y descripcion
        model.setDetalle(view.txtDescripcion.getText());
        System.out.println("\nUSO DE PATRON ADAPTER\n");
        // Uso de Adapter
        boolean band = validarYGuardarSeleccion();
        if (band) {
            vehiculo = new Auto_Mendoza_Ruiz(model.getMarca(), model.getModelo());
            // Adaptar el auto para que pueda recibir modificaciones
            AdapterModificacionAuto_Mendoza_Ruiz autoModificado = new AdapterModificacionAuto_Mendoza_Ruiz(vehiculo, model.getPiezas());
            // Agregar modificaciones
            // Convertir lista a String separado por ", "
            String resultado = String.join(", ", model.getPiezas());
            autoModificado.agregarModificacion(resultado, model.getDetalle());
            // Mostrar las modificaciones
            autoModificado.mostrarModificaciones();
            Document doc = new Document("Clave", model.getCodigoSearch());
            vehiculo.mostrarInfo(view, doc, resultado, model.getDetalle());
        } else {
            System.out.println("ERROR : ERROR AL VALIDAR CAMPOS DE LAS PIEZAS");
        }
    }
    
    public boolean validarBusquedaDePrototios(){
        boolean band = true;
        if (model.getCodigoSearch().isEmpty()) {
            view.errClaveSearch.setText("Ingrese el código del vehículo.");
            view.errClaveSearch.setVisible(true);
            band = false;
        } else {
            view.errClaveSearch.setVisible(false);
        }
        return band;
    }

    public void buscarObjetosClonados() {
        model.setCodigoSearch(view.txtClaveSearch.getText());
        boolean band = validarBusquedaDePrototios();

        if (band) {
            try {
                Vehiculo_Mendoza_Ruiz auto1 = gestor.obtenerPrototipo(model.getCodigoSearch());
                if (auto1 == null) {
                    System.out.println("\nERROR: Al buscar el obje. Prototype\n");
                    JOptionPane.showMessageDialog(view, "Prototipo con clave " + model.getCodigoSearch() + " no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new IllegalArgumentException("Prototipo con clave " + model.getCodigoSearch() + " no encontrado.");
                } else {
                    Document doc = new Document("Clave", model.getCodigoSearch());
                    auto1.mostrarInfo(view, doc, "", "");
                }
            } catch (IllegalArgumentException e) {
                // Mostrar el mensaje de error en la interfaz de usuario o consola
                System.out.println("ERROR: " + e.getMessage());
            }
        } else {
            System.out.println("\nERROR: Al validar el codigo clave del prototipo en el panel de la tabla\n");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnAdaptar) {
            adaptarObjetos();
        } else if (e.getSource() == view.btnBuscarClave) {
            buscarObjetosClonados();
        } else if (e.getSource() == view.btnRegistrar) {
            registrarObjetos();
        }
    }

}
