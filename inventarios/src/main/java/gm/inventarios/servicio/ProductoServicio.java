package gm.inventarios.servicio;

import gm.inventarios.modelo.Producto;
import gm.inventarios.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio{

    @Autowired
    private ProductoRepository productorepository;

    @Override
    public List<Producto> listarProdutos() {

        return this.productorepository.findAll();
    }

    @Override
    public Producto buscarProductoById(Integer IdProducto) {
        Producto producto =
                this.productorepository.findById(IdProducto).orElse(null);
        return producto;
    }

    @Override
    public void guardarProducto(Producto producto) {
        this.productorepository.save(producto);
    }

    @Override
    public void eliminarProductoById(Integer IdProducto) {
        this.productorepository.deleteById(IdProducto);
    }
}
