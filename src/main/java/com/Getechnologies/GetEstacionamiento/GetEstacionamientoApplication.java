package com.Getechnologies.GetEstacionamiento;

import com.Getechnologies.GetEstacionamiento.service.EstacionamientoService;
import com.Getechnologies.GetEstacionamiento.service.InformePagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GetEstacionamientoApplication implements CommandLineRunner {

	@Autowired
	private EstacionamientoService estacionamientoService;

	@Autowired
	private InformePagosService informePagosService;

	public static void main(String[] args) {
		SpringApplication.run(GetEstacionamientoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		mostrarMenu();
	}

	private void mostrarMenu() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("----------- Menú -----------");
			System.out.println("1. Registrar entrada");
			System.out.println("2. Registrar salida");
			System.out.println("3. Calcular importe");
			System.out.println("4. Generar informe de pagos");
			System.out.println("5. Dar de alta vehículo de residente");
			System.out.println("6. Dar de alta vehículo oficial");
			System.out.println("7. Comenzar nuevo mes");
			System.out.println("8. Salir");
			System.out.print("Seleccione una opción: ");

			int opcion = scanner.nextInt();
			scanner.nextLine(); // Consumir la nueva línea después de nextInt

			switch (opcion) {
				case 1:
					System.out.print("Ingrese el número de placa: ");
					String placaEntrada = scanner.nextLine();
					estacionamientoService.registrarEntrada(placaEntrada);
					break;
				case 2:
					System.out.print("Ingrese el número de placa: ");
					String placaSalida = scanner.nextLine();
					estacionamientoService.registrarSalida(placaSalida);
					break;
				case 3:
					System.out.print("Ingrese el número de placa: ");
					String placaCalculo = scanner.nextLine();
					double importe = estacionamientoService.calcularImporte(placaCalculo);
					System.out.println("El importe es: " + importe);
					break;
				case 4:
					System.out.print("Ingrese el nombre del archivo para el informe: ");
					String nombreArchivo = scanner.nextLine();
					informePagosService.generarInforme(nombreArchivo);
					break;
				case 5:
					System.out.print("Ingrese el número de placa del vehículo de residente: ");
					String placaResidente = scanner.nextLine();
					estacionamientoService.darAltaVehiculoResidente(placaResidente);
					System.out.println("Vehículo de residente dado de alta.");
					break;
				case 6:
					System.out.print("Ingrese el número de placa del vehículo oficial: ");
					String placaOficial = scanner.nextLine();
					estacionamientoService.registrarVehiculoOficial(placaOficial);
					System.out.println("Vehículo oficial dado de alta.");
					break;
				case 7:
					estacionamientoService.comenzarNuevoMes();
					System.out.println("Nuevo mes iniciado.");
					break;
				case 8:
					System.out.println("Saliendo del programa. ¡Hasta luego!");
					System.exit(0);
				default:
					System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
			}
		}
	}
}































