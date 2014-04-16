package co.edu.uniandes.tsp.geko.modelo;

import java.io.File;
import java.util.List;

import co.edu.uniandes.tsp.geko.vo.ClaseVO;
import co.edu.uniandes.tsp.geko.vo.Parte;

public interface Modelo {

	/**
	 * Método que copia fuentes del proyecto. Crea una carpeta .gc por cada folder del
	 * proyecto
	 * @param ruta del proyecto
	 */
	public void copiarProyecto(String ruta);
	
	/**
	 * Método que recorre todo el proyecto para realizar la comparación
	 * @param ruta del proyecto
	 * @return Listado de Partes
	 */
	public List<Parte> recorrerProyecto(String ruta);
	
	/**
	 * Método que realiza la comparación de un archivo original y un archivo copia
	 * @param parteOriginal
	 * @param parteModificada
	 * @return Parte con información
	 */
	public Parte compararPartes(File parteOriginal, File parteModificada);
	
	/**
	 * Método que cuenta las lineas de codigo totales del programa
	 * @param rutaFolder
	 * @param validarCopia
	 * @return
	 */
	public List<ClaseVO> contadorLoc(String rutaFolder, boolean validarCopia);
	
}
