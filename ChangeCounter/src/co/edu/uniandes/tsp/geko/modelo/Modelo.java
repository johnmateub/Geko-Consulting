package co.edu.uniandes.tsp.geko.modelo;

import java.io.File;
import java.util.List;

import co.edu.uniandes.tsp.geko.vo.ClaseVO;
import co.edu.uniandes.tsp.geko.vo.Parte;

public interface Modelo {

	/**
	 * M�todo que copia fuentes del proyecto. Crea una carpeta .gc por cada folder del
	 * proyecto
	 * @param ruta del proyecto
	 */
	public void copiarProyecto(String ruta);
	
	/**
	 * M�todo que recorre todo el proyecto para realizar la comparaci�n
	 * @param ruta del proyecto
	 * @return Listado de Partes
	 */
	public List<Parte> recorrerProyecto(String ruta);
	
	/**
	 * M�todo que realiza la comparaci�n de un archivo original y un archivo copia
	 * @param parteOriginal
	 * @param parteModificada
	 * @return Parte con informaci�n
	 */
	public Parte compararPartes(File parteOriginal, File parteModificada);
	
	/**
	 * M�todo que cuenta las lineas de codigo totales del programa
	 * @param rutaFolder
	 * @param validarCopia
	 * @return
	 */
	public List<ClaseVO> contadorLoc(String rutaFolder, boolean validarCopia);
	
}
