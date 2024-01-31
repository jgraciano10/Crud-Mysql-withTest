package gm.inventarios.repository;

import gm.inventarios.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends JpaRepository<Producto ,Integer> {

}
