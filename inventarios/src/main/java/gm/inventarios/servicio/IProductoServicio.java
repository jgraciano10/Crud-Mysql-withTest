package gm.inventarios.servicio;

import gm.inventarios.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    public List<Producto> listarProdutos();

    public Producto buscarProductoById(Integer IdProducto);

    public void guardarProducto(Producto producto);

    public void eliminarProductoById(Integer IdProducto);
}
