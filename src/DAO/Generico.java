package DAO;

import java.sql.SQLException;
import java.util.List;

public interface Generico<T> {
	List<T> mostrarDatos() throws SQLException;

	boolean insertarDatos(T t) throws SQLException;

	boolean actualizarDatos(T t) throws SQLException;

	boolean eliminar(int id) throws SQLException;
	
	List<T> validarConId(int id) throws SQLException;
}
