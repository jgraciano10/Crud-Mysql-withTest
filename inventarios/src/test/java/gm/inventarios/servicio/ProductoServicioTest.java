package gm.inventarios.servicio;

import gm.inventarios.modelo.Producto;
import gm.inventarios.repository.ProductoRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductoServicioTest {
    @Mock
    private ProductoRepository repository;
    @InjectMocks
    private ProductoServicio producto;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_Return_All_Users(){

        List<Producto>  list = List.of(Producto.builder()
                .IdProducto(1)
                .descripcion("Arroz")
                .precio(2840d)
                .Existencia(28)
                .build());

        when(repository.findAll()).thenReturn(list);
        Iterable<Producto> users = producto.listarProdutos();
        Producto obtenerProducto = users.iterator().next();

        assertEquals(Integer.valueOf(1), obtenerProducto.getIdProducto());
    }

    @Test
    public void Should_Return_By_Id(){
        Producto producto1 = Producto.builder()
                .IdProducto(1)
                .descripcion("Arroz")
                .precio(2840d)
                .Existencia(28)
                .build();


        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(producto1));

        Producto productoObtenido = producto.buscarProductoById(2);

        assertEquals(1, productoObtenido.getIdProducto());
    }
    @Test
    public void should_Save_User(){
        Producto producto1 = Producto.builder()
                .IdProducto(1000)
                .descripcion("Arroz")
                .precio(2840d)
                .Existencia(28)
                .build();

        producto.guardarProducto(producto1);

        verify(repository).save(producto1);


    }
    @Test
    public void should_Delete_User_By_Id(){
        Producto producto1 = Producto.builder()
                .IdProducto(1000)
                .descripcion("Arroz")
                .precio(2840d)
                .Existencia(28)
                .build();

        producto.guardarProducto(producto1);

        producto.eliminarProductoById(1000);

        verify(repository).deleteById(1000);


    }

}