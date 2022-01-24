package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {

	public static void main(String[] args) throws OperationNotSupportedException {
		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpcion());
	}
	public final static int NUM_MAX_CITAS = 20;
	private static Citas citasClinica = new Citas(NUM_MAX_CITAS);
	
	private static void mostrarCitasDia() throws OperationNotSupportedException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = Consola.leerFecha();
		System.out.println("");
		System.out.println("Lista de citas para fecha: " + fecha.format(formato));
		System.out.println("");
		Cita[] citasFecha = citasClinica.getCitas(fecha);
		boolean hayCitasFecha = false;
		for (int i = 0; i <= citasFecha.length - 1; i++) {
			if (citasFecha[i] != null) {
				hayCitasFecha = true;
			}
		}

		if (citasFecha.length == 0 || !hayCitasFecha) {
			System.out.println("No hay existen citas para esta fecha");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} else {

			for (Cita cita : citasFecha) {
				if (cita != null) {
					System.out.println(cita);

				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}
	
	private static void mostrarCitas() throws OperationNotSupportedException {
		System.out.println("");
		System.out.println("Lista de citas");
		System.out.println("");

		if (citasClinica.getTamano() == 0) {
			System.out.println("");
			System.out.println("No hay citas para esa fecha que mostrar");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} else {
			Cita[] citas = citasClinica.getCitas();

			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}

	
	private static void borrarCita() throws OperationNotSupportedException {

		LocalDateTime fechaHora = Consola.leerFechaHora();
		Paciente paciente = new Paciente("paciente", "75261569A", "000000000");
		Cita cita = new Cita(paciente, fechaHora);
		try {

			citasClinica.borrar(cita);
			System.out.println("");
			System.out.println("Cita borrada");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());

		} catch (OperationNotSupportedException excep) {
			System.out.println("");
			System.out.println(excep.getMessage());
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}

	}
	private static void insertarCita() {

		try {
			Cita cita = Consola.leerCita();
			citasClinica.insertar(cita);
			System.out.println("Cita asignada");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} catch (Exception excepci) {
			System.out.println("Se ha producido un error: " + excepci.getMessage());}
		}
		private static void buscarCita() throws OperationNotSupportedException {

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime fecha = Consola.leerFechaHora();
			Paciente paciente = new Paciente("paciente", "75261569A", "000000000");
			Cita cita = new Cita(paciente, fecha);
			Cita citaComprobada;
			citaComprobada = citasClinica.buscar(cita);

			if (citaComprobada == null) {
				System.out.println("");
				System.out.println("No existen citas para la fecha: " + fecha.format(formato));
				System.out.println("");
			} else {

				Cita[] citas = citasClinica.getCitas(fecha.toLocalDate());
				for (Cita citaPasada : citas) {
					if (cita.equals(citaPasada)) {
						System.out.println("");
						System.out.println("La cita es " + citaPasada);
						System.out.println("");
					}
				}

			}

			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
		
		private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {
			switch (opcion) {
			case SALIR:
				System.out.print("EXIT");
				break;
			case INSERTAR_CITA:
				insertarCita();
				break;
			case BUSCAR_CITA:
				buscarCita();
				break;
			case BORRAR_CITA:
				borrarCita();
				break;
			case MOSTRAR_CITAS_DIA:
				mostrarCitasDia();
				break;
			case MOSTRAR_CITAS:
				mostrarCitas();
				break;
			}
		}
}
