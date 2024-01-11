package pe.edu.proyect.interview.crud.login.jwt.services;

import pe.edu.proyect.interview.crud.login.jwt.entity.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> obtenerTodosLosProductos();
    Producto obtenerProductoPorId(Long id);
    Producto crearProducto(Producto producto);
    Producto actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);

    // Nuevo método de búsqueda por nombre (insensible a mayúsculas y minúsculas)
}
