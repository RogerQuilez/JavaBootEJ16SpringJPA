package es.babel.curso.main;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.babel.curso.cfg.ConfigurationApp;
import es.babel.curso.entity.Coche;
import es.babel.curso.service.CocheService;

public class Main {

	private static ApplicationContext context;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		context = new AnnotationConfigApplicationContext(ConfigurationApp.class);
		CocheService cocheService = context.getBean("cocheServiceImpl", CocheService.class);
		
		Integer opcion = 1;
		
		do {
			printMenu();
			opcion = Integer.parseInt(sc.nextLine());
			HashMap<String, String> message = new HashMap<>();
			
			switch(opcion) {
			
				case 0:
					break;
			
				case 1:
					message = cocheService.alta(getCar(new Coche()));
					printMessage(message);
					break;
					
				case 2:
					printListCoche(cocheService.listar());
					break;
					
				case 3:
					printCar(getCarById(cocheService));
					break;
					
				case 4:
					Coche updateCoche = getCarById(cocheService);
					message = cocheService.modificar(getCar(updateCoche));
					printMessage(message);
					break;
					
				case 5:
					Coche deleteCoche = getCarById(cocheService);
					message = cocheService.baja(deleteCoche.getId());
					printMessage(message);
					break;
			}
			
		} while(opcion!=0);
	}
	
	/**
	 * Print main Menu 
	 */
	public static void printMenu() {
		System.out.println("-------  Select an option  --------\n"
				+ "\t 1. Insert new Car\n"
				+ "\t 2. List all Cars\n"
				+ "\t 3. Find Car By Id\n"
				+ "\t 4. Update existent car\n"
				+ "\t 5. Delete a car\n"
				+ "\t 0. Exit");
	}
	
	/**
	 * @param sc -> Scanner para leer por console
	 * @param coche -> Objeto coche con el que trabajar� el m�todo
	 * @return coche -> Devuelve un objeto coche enviado por parametros con los atributos asignados
	 * por el usuario
	 */
	public static Coche getCar(Coche coche) {
		
		try {
			
			System.out.println("Introcuce Marca of car");
			String marca = sc.nextLine();
			
			System.out.println("Introduce Model of car");
			String model = sc.nextLine();
			System.out.println("Introduce Km's of car");
			Integer km = Integer.parseInt(sc.nextLine());
			System.out.println("Introduce Matricula of car");
			String matricula = sc.nextLine();
			
			coche.setMarca(marca);
			coche.setModel(model);
			coche.setKm(km);
			coche.setMatricula(matricula);
			return coche;
			
		} catch (NumberFormatException e) {
			System.out.println("Error Null -> El campo kil�metros solo acepta n�meros");
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param c -> Objeto Coche
	 * Printa los atributos del objeto Coche
	 */
	public static void printCar(Coche c) {
		System.out.println("ID: " + c.getId());
		System.out.println("Marca: " + c.getMarca());
		System.out.println("Model: " + c.getModel());
		System.out.println("KM'S: " + c.getKm());
		System.out.println("Matricula: " + c.getMatricula());
		System.out.println("-------------------");
	}
	
	/**
	 * @param listCoche -> Par�metro que contiene la lista de los coches de la BBDD
	 * Printa la lista de los coches disponibles en la BBDD
	 */
	public static void printListCoche(List<Coche> listCoche) {
		for (Coche c: listCoche) {
			printCar(c);
		}
	}
	
	/**
	 * @param sc -> Scanner para leer por consola
	 * @param cocheService -> Servicio para poder obtener el objeto Coche a trav�s de la id
	 * @return Retorna un coche obtenido por el id que proporciona el cliente
	 */
	public static Coche getCarById(CocheService cocheService) {
		Integer id = null;
		Coche coche;
		
		do {
			System.out.println("Por favor, introduce la ID del coche");
			id = Integer.parseInt(sc.nextLine());
			coche = cocheService.obtener(id);
			
			if (coche == null) 
				System.out.println("-----------ID Inv�lida----------");
			
		} while(coche == null);
		
		return coche;
	}
	
	/**
	 * @param errors -> HashMap con los errores o las confirmaciones del servicio coche
	 * Printa todos los mensajes que tenga el objeto message
	 */
	public static void printMessage(HashMap<String, String> message) {
		System.out.println("***************************************");
		message.forEach((k, v) -> System.out.println(k + v));
		System.out.println("***************************************");
	}
}
