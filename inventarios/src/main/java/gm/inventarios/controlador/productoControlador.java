package gm.inventarios.controlador;

import gm.inventarios.excepcion.recursoNoEncontradoExcepcion;
import gm.inventarios.modelo.Producto;
import gm.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class productoControlador {

    private static final Logger logger = LoggerFactory.getLogger(productoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/productos")
    public List<Producto> obtenerProductos(){
        List<Producto> productos = this.productoServicio.listarProdutos();
        logger.info("Productos Obtenidos:");
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }

    @PostMapping("/productos")
    public void insertarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar: "+ producto);
        this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoById(@PathVariable int id){
        Producto producto =
                this.productoServicio.buscarProductoById(id);

        if(producto!=null) return ResponseEntity.ok(producto);
        else throw new recursoNoEncontradoExcepcion("No se encontro el producto con el id: "+ id);


    }


    @DeleteMapping("/productos/{id}")
    public  ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int id){
        Producto producto = this.productoServicio.buscarProductoById(id);

        if(producto==null){
            throw new recursoNoEncontradoExcepcion("Producto con id " + id + " no encontrado");
        }
        else{
            this.productoServicio.eliminarProductoById(producto.getIdProducto());
            Map<String, Boolean> Respuesta = new HashMap<>();
            return ResponseEntity.ok(Respuesta);
        }

    }


    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto productoRecibido){
        Producto producto = this.productoServicio.buscarProductoById(id);
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

}
