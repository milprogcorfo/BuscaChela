package modelo;

public class Precio {
    private double precio;
    private int idProducto;
    private int idLocal;
    private Producto producto;
    private Proveedor proveedor;
    
    public Precio() {
        this.precio = 0;
        this.idProducto = 0;
        this.idLocal = 0;
        this.producto = new Producto();
        this.proveedor = new Proveedor();
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
}   