package co.edu.uniandes.tsp.geko.modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.tsp.geko.util.Contador;
import co.edu.uniandes.tsp.geko.util.CopiaProyecto;
import co.edu.uniandes.tsp.geko.util.FileComparator;
import co.edu.uniandes.tsp.geko.vo.ClaseVO;
import co.edu.uniandes.tsp.geko.vo.Parte;
import difflib.Chunk;

/**
 * @author Ivan
 *
 */
public class ModeloImpl implements Modelo{

	List<Parte> partesList = new ArrayList<Parte>();
	
	
	/* (non-Javadoc)
	 * @see co.edu.uniandes.tsp.geko.modelo.Modelo#copiarProyecto(java.lang.String)
	 */
	public void copiarProyecto(String ruta){
		try{
			CopiaProyecto.copyProjectIntoDirectory(ruta);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see co.edu.uniandes.tsp.geko.modelo.Modelo#recorrerProyecto(java.lang.String)
	 */
	public List<Parte> recorrerProyecto(String ruta){
		Parte parte;
		File raiz = new File(ruta);

		for (File file : raiz.listFiles()) {
			if (file.isDirectory()&&!file.getName().equals(".gc")) {
				recorrerProyecto(file.getPath());
			} else {
				parte = new Parte();
				if (file.getName().endsWith(".java")) {
					File raizCopia = new File(file.getParent()+File.separator+".gc");
					for (File fileCopia : raizCopia.listFiles()) {
						if(file.getName().equals(fileCopia.getName())){
							parte = compararPartes(file, fileCopia);
						}
					}
					parte.setNombreParte(file.getName());
					partesList.add(parte);
				}
			}

		}
		
		return partesList;

	}
	
	/* (non-Javadoc)
	 * @see co.edu.uniandes.tsp.geko.modelo.Modelo#compararPartes(java.io.File, java.io.File)
	 */
	public Parte compararPartes(File parteOriginal, File parteModificada){
		Parte parteTotal = new Parte();
		
		Parte parte = new Parte();
		parte = obtenerCambiosEntreArchivos(parteOriginal, parteModificada);
		parteTotal.setCantidadLineasModificadas(parte.getCantidadLineasModificadas());
		parteTotal.setLineasModificadas(parte.getLineasModificadas());
		
		parte = new Parte();
		parte = obtenerInsercionesEntreArchivos(parteOriginal, parteModificada);
		parteTotal.setCantidadLineasAgregadas(parte.getCantidadLineasAgregadas());
		parteTotal.setLineasAgregadas(parte.getLineasAgregadas());
		
		parte = new Parte();
		parte = obtenerEliminacionesEntreArchivos(parteOriginal, parteModificada);
		parteTotal.setCantidadLineasBorradas(parte.getCantidadLineasBorradas());
		parteTotal.setLineasEliminadas(parte.getLineasEliminadas());
		
		
		return parteTotal;
	}
	
	/* (non-Javadoc)
	 * @see co.edu.uniandes.tsp.geko.modelo.Modelo#contadorLoc(java.lang.String, boolean)
	 */
	public List<ClaseVO> contadorLoc(String rutaFolder, boolean validarCopia){
		File directorio = new File(rutaFolder); 
		Contador contador = new Contador();
		return contador.listarDirectorio(directorio, validarCopia);
	}
	
	/**
	 * Método que realiza la comparación de líneas modificadas entre un archivo original y uno modificado
	 * @param original
	 * @param revised
	 * @return
	 */
	public Parte obtenerCambiosEntreArchivos(File original, File revised) {

		final FileComparator comparator = new FileComparator(original, revised);

		try {
			
			Parte parte = new Parte();
			final List<Chunk> changesFromOriginal = comparator.getChangesFromOriginal();
			parte.setCantidadLineasModificadas(changesFromOriginal.size());

			String linea = "";
			for(Chunk change:changesFromOriginal){
				linea = "Linea "+(change.getPosition() + 1) +"";
				linea += " Cambios: ";
				for(Object texto:change.getLines()){
					linea += texto.toString();
				}
				parte.getLineasModificadas().add(linea);
			}
			
			return parte;
			
		} catch (IOException ioe) {
			//fail("Error running test shouldGetChangesBetweenFiles " + ioe.toString());
		}
		return null;
	}
	
	/**
	 * Método que realiza la comparación de líneas insertadas entre un archivo original y uno modificado
	 * @param original
	 * @param revised
	 * @return
	 */
	public Parte obtenerInsercionesEntreArchivos(File original, File revised) {
		 
        final FileComparator comparator = new FileComparator(original, revised);
 
        try {
        	Parte parte = new Parte();
            final List<Chunk> insertsFromOriginal = comparator.getInsertsFromOriginal();
            parte.setCantidadLineasAgregadas(insertsFromOriginal.size());
 
            String linea = "";
			for(Chunk change:insertsFromOriginal){
				linea = "Linea "+(change.getPosition() + 1) +"";
				linea += " Inserción: ";
				for(Object texto:change.getLines()){
					linea += texto.toString();
				}
				parte.getLineasAgregadas().add(linea);
			}
			
            return parte;
        } catch (IOException ioe) {
        	//fail("Error running test shouldGetInsertsBetweenFiles " + ioe.toString());
        }
        return null;
    }

	/**
	 * Método que realiza la comparación de líneas eliminadas entre un archivo original y uno modificado
	 * @param original
	 * @param revised
	 * @return
	 */
	public Parte obtenerEliminacionesEntreArchivos(File original, File revised) {
		 
        final FileComparator comparator = new FileComparator(original, revised);
 
        try {
        	Parte parte = new Parte();
            final List<Chunk> deletesFromOriginal = comparator.getDeletesFromOriginal();
            parte.setCantidadLineasBorradas(deletesFromOriginal.size());
 
            String linea = "";
			for(Chunk change:deletesFromOriginal){
				linea = "Linea "+(change.getPosition() + 1) +"";
				parte.getLineasEliminadas().add(linea);
			}
			
            return parte;
        } catch (IOException ioe) {
        	//fail("Error running test shouldGetDeletesBetweenFiles " + ioe.toString());
        }
        return null;
    }
	
}
