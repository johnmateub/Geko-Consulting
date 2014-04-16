package co.edu.uniandes.tsp.geko.main;

import java.util.List;
import java.util.Scanner;

import co.edu.uniandes.tsp.geko.modelo.Modelo;
import co.edu.uniandes.tsp.geko.modelo.ModeloImpl;
import co.edu.uniandes.tsp.geko.util.Contador;
import co.edu.uniandes.tsp.geko.vo.ClaseVO;
import co.edu.uniandes.tsp.geko.vo.Parte;

public class Main {

	/**
	 * Método inicial del programa
	 * @param args
	 */
	public static void main(String[] args) {
		int opcion = Main.presentaMenu();
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingrese la ubicación del folder del proyecto");
		String rutaFolder = teclado.nextLine();
		
		Modelo modelo = new ModeloImpl();
		
		if(opcion == 1){
			modelo.copiarProyecto(rutaFolder);
			System.out.println("Proyecto copiado satisfactoriamente.");
		}else if (opcion == 2){
			List<Parte> parteList = modelo.recorrerProyecto(rutaFolder);
			for(Parte parte:parteList){
				System.out.println("Parte: "+parte.getNombreParte());
				System.out.println("\tLineas modificadas: "+parte.getCantidadLineasModificadas());
				for(String texto:parte.getLineasModificadas()){
					System.out.println("\t\tTexto: "+texto);
				}
				
				System.out.println("\tLineas agregadas: "+parte.getCantidadLineasAgregadas());
				for(String texto:parte.getLineasAgregadas()){
					System.out.println("\t\tTexto: "+texto);
				}
				
				System.out.println("\tLineas eliminadas: "+parte.getCantidadLineasBorradas());
				for(String texto:parte.getLineasEliminadas()){
					System.out.println("\t\tPosición: "+texto);
				}
			}
			
//			List<ClaseVO> claseVOList = modelo.contadorLoc(rutaFolder, false);
//			int lineasProgramaOriginal = 0;
//			for(ClaseVO claseVO:claseVOList){
//				lineasProgramaOriginal += claseVO.getNumeroLineas();
//			}
			
			List<ClaseVO> claseVOList = modelo.contadorLoc(rutaFolder, true);
			int lineasProgramaCopia = 0;
			for(ClaseVO claseVO:claseVOList){
				lineasProgramaCopia += claseVO.getNumeroLineas();
			}
			
			//System.out.println("Total líneas programa original: "+lineasProgramaOriginal);
			System.out.println("Total líneas programa copia: "+lineasProgramaCopia);
			
		}else{
			System.out.println("Opción Inválida!!!");
		}
	}
	
	/**
	 * Método que presenta el menú al usuario
	 * @return opcion seleccionada
	 */
	private static int presentaMenu(){
		int opcion = 0;
		System.out.println("Digite la opción que desea realizar:");
		System.out.println("1. Copiar proyecto");
		System.out.println("2. Comparar proyecto");
		Scanner teclado = new Scanner(System.in);
		opcion = Integer.parseInt(teclado.nextLine());
		return opcion;
	}

}
