package App;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import Beans.AnimalBean;
import DAO.AnimalBeanDAO;
import Impl.AnimalDaoImpl;
import oracle.net.aso.j;

public class AppAnimales {
	private static AnimalBeanDAO animalBeanDAO = new AnimalDaoImpl();

	public static void main(String[] args) {
		// imprimir();
		int opcion;
		JOptionPane.showMessageDialog(null, "BIENVENIDO SELECCIONE UNA DE LAS OPCIONES: ");

		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "1. MOSTRAR DATOS: " + "\n 2. INSERTAR DATOS"
					+ "\n 3. ACTUALIZAR DATO" + "\n 4. ELIMINAR DATO" + "\n 5.SALIR"));
		} while (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4 && opcion != 5);

		switch (opcion) {
		case 1:
			imprimir();
			break;
		case 2:
			insertar();
			break;
		case 3:
			actualizar();
			break;
		case 4:
			eliminar();
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "HASTA PRONTO :)");
		}
	}

	public static void imprimir() {
		List<AnimalBean> animales;
		try {
			animales = animalBeanDAO.mostrarDatos();
			animales.forEach(System.out::println);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insertar() {
		String nombre, tipo, habitad;
		JOptionPane.showMessageDialog(null, "A CONTINUACION INGRESE LOS DATOS SOLICITADOS...");
		nombre = JOptionPane.showInputDialog("INGRESE EL NOMBRE DEL ANIMAL");
		tipo = JOptionPane.showInputDialog("INGRESE EL TIPO DE ANIMAL");
		habitad = JOptionPane.showInputDialog("INGRESE EL HABITAD DEL ANIMAL");
		AnimalBean animalBean = new AnimalBean();
		animalBean.setNombre(nombre);
		animalBean.setTipo(tipo);
		animalBean.setHabitad(habitad);
		try {
			if (animalBeanDAO.insertarDatos(animalBean)) {
				JOptionPane.showMessageDialog(null, "EXITO AL INSERTAR");
				System.out.println("Mostrando DATOS:");
				imprimir();
			} else {
				JOptionPane.showMessageDialog(null, "NO SE INSERTO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void actualizar() {
		// NOMBRE TIPO HABITAD
		String nombre, tipo, habitad;
		int idAnimal = 0;

		JOptionPane.showMessageDialog(null, "A continuacion ingrese los datos a actualizar: ");
		nombre = JOptionPane.showInputDialog("INGRESE EL NOMBRE DEL ANIMAL: ");
		tipo = JOptionPane.showInputDialog("INGRESE EL TIPO DEL ANIMAL: ");
		habitad = JOptionPane.showInputDialog("INGRESE EL HABITAD DEL ANIMAL: ");

		try {
			do {
				idAnimal = Integer.parseInt(JOptionPane.showInputDialog("INGRESE EL ID DEL ANIMAL: "));

				if (animalBeanDAO.validarConId(idAnimal) == null) {
					System.out.println("No se puede actualizar ese registro digite un id correcto");
				}

			} while (animalBeanDAO.validarConId(idAnimal) == null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		AnimalBean animalBean = new AnimalBean();
		animalBean.setNombre(nombre);
		animalBean.setHabitad(habitad);
		animalBean.setTipo(tipo);
		animalBean.setId(idAnimal);
		try {
			if (animalBeanDAO.actualizarDatos(animalBean)) {
				JOptionPane.showMessageDialog(null, "EXITO AL ACTUALIZAR");
			} else {
				JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void eliminar() {
		int idAnimal;
		idAnimal = Integer.parseInt(JOptionPane.showInputDialog("INGRESE EL ID DEL ANIMAL A ELIMINAR"));
		try {
			if (animalBeanDAO.eliminar(idAnimal)) {
				JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO");
			} else {
				JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
