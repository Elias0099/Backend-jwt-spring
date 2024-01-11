package pe.edu.proyect.interview.crud.login.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.proyect.interview.crud.login.jwt.entity.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
