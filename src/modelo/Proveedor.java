package modelo;

public class Proveedor {

    private String empresa;
    private String direccion;
    private String comuna;
    private double latitud;
    private double longitud;
    private String descripcion;
    private String telefono;
    
    public Proveedor() {
        this.empresa = "";
        this.direccion = "";
        this.comuna = "";
        this.telefono = "";
        this.latitud = 0;
        this.longitud = 0;
        this.descripcion = "";
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    @Override
    public String toString() {
        return this.empresa + " - " + this.direccion + ", " + this.comuna;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof String) {
            return this.toString().equals(obj);
        }
        return this.comuna.equals(((modelo.Proveedor)obj).getComuna()) &&
                this.direccion.equals(((modelo.Proveedor)obj).getDireccion()) &&
                this.empresa.equals(((modelo.Proveedor)obj).getEmpresa());
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
   
}
