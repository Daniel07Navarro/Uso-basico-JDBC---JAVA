package Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.AnimalBean;
import DAO.AnimalBeanDAO;
import Util.BD;

public class AnimalDaoImpl implements AnimalBeanDAO {

	private static final String SQL_SELECT = "SELECT ID,NOMBRE,TIPO,HABITAD FROM ANIMALES";

	private static final String SQL_DELETE = "DELETE FROM ANIMALES WHERE ID= ?";

	private static final String SQL_UPDATE = "UPDATE ANIMALES SET NOMBRE = ?, TIPO = ?, HABITAD = ? WHERE ID = ?";

	private static final String SQL_INSERT = "INSERT INTO ANIMALES (ID,NOMBRE,TIPO,HABITAD) VALUES (SEQ_ANIMALES.NEXTVAL,?,?,?)";

	@Override
	public List<AnimalBean> mostrarDatos() throws SQLException {
		Connection cn = BD.getConnection();
		PreparedStatement ps = cn.prepareStatement(SQL_SELECT);
		ResultSet rs = ps.executeQuery();
		List<AnimalBean> animales = new ArrayList<>();
		while (rs.next()) {
			AnimalBean animalBean = new AnimalBean();
			animalBean.setId(rs.getInt("ID"));
			animalBean.setNombre(rs.getString("NOMBRE"));
			animalBean.setTipo(rs.getString("TIPO"));
			animalBean.setHabitad(rs.getString("HABITAD"));
			animales.add(animalBean);
		}

		BD.Close(cn);
		BD.Close(ps);
		BD.Close(rs);
		return animales;
	}
	
	public List<AnimalBean> mostrarDatosV2() throws SQLException{
		Connection cn = BD.getConnection();
		PreparedStatement ps = cn.prepareStatement(SQL_SELECT);
		ResultSet rs = ps.executeQuery();
		List<AnimalBean> animales = new ArrayList<>();
		while(rs.next()) {
			AnimalBean animal = new AnimalBean();
			animal.setId(rs.getInt("ID"));
			animales.add(animal);
		}
		
		return animales;
	}

	@Override
	public boolean insertarDatos(AnimalBean animalBean) throws SQLException {
		Connection cn = BD.getConnection();
		PreparedStatement ps = cn.prepareStatement(SQL_INSERT);
		ps.setString(1, animalBean.getNombre());
		ps.setString(2, animalBean.getTipo());
		ps.setString(3, animalBean.getHabitad());
		ps.executeUpdate();

		BD.Close(cn);
		BD.Close(ps);
		return true;
	}

	@Override
	public boolean actualizarDatos(AnimalBean animalBean) throws SQLException {
		Connection cn = BD.getConnection();
		PreparedStatement ps = cn.prepareStatement(SQL_UPDATE);
		ps.setString(1, animalBean.getNombre());
		ps.setString(2, animalBean.getTipo());
		ps.setString(3, animalBean.getHabitad());
		ps.setInt(4, animalBean.getId());
		ps.executeUpdate();

		BD.Close(cn);
		BD.Close(ps);
		return true;
	}

	@Override
	public boolean eliminar(int id) throws SQLException {
		Connection cn = BD.getConnection();
		PreparedStatement ps = cn.prepareStatement(SQL_DELETE);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		BD.Close(cn);
		BD.Close(ps);
		return true;
	}
	
	@Override
	public List<AnimalBean> validarConId(int id) throws SQLException{
		Connection cn = BD.getConnection();
		String sql = "SELECT ID FROM ANIMALES WHERE ID =? ";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<AnimalBean> animales = new ArrayList<AnimalBean>();
		while(rs.next()) {
			AnimalBean animalBean = new AnimalBean();
			animalBean.setId(rs.getInt("ID"));
			animales.add(animalBean);
		}
		BD.Close(cn);
		BD.Close(ps);
		BD.Close(rs);
		if(animales.size()==1) {
			return animales;
		}else {
			return null;
		}
	}

}
