package modelo;

public class Producto {
    private String marca;
    private String tipo;
    private String formato;
    private int medida;
    
    public Producto(){
        this.marca = "";
        this.tipo = "";
        this.formato = "";
        this.medida = 0;
    }
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getMedida() {
        return medida;
    }

    public void setMedida(int medida) {
        this.medida = medida;
    }
}
