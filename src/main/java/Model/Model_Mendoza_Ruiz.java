package Model;

import java.util.ArrayList;
import java.util.List;

public class Model_Mendoza_Ruiz {

    private String marca, modelo, codigo, detalle, codigoSearch;
    private List<String> piezas;

    public Model_Mendoza_Ruiz(String marca, String modelo, String codigo, String detalle, String codigoSearch, List<String> piezas) {
        this.marca = marca;
        this.modelo = modelo;
        this.codigo = codigo;
        this.detalle = detalle;
        this.codigoSearch = codigoSearch;
        this.piezas = new ArrayList<>();
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getCodigoSearch() {
        return codigoSearch;
    }

    public void setCodigoSearch(String codigoSearch) {
        this.codigoSearch = codigoSearch;
    }

    public List<String> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<String> piezas) {
        this.piezas = piezas;
    }

}
