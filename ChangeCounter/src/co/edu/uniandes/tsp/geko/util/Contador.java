/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.tsp.geko.util;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.tsp.geko.vo.ClaseVO;

/**
 *
 * @author iviasus
 */
public class Contador {

	List<ClaseVO> clases;



	public Contador(){
		clases = new ArrayList<ClaseVO>();
	}

	public String[] listarElementosDeCarpeta(String ruta){
		File dir = new File(ruta);
		String[] ficheros = dir.list();

		return (ficheros == null ? new String[0] : ficheros);
	}

	/**
	 * Obtiene lista de clases en el directorio con todos sus atributos y propiedades
	 * @param directorio
	 * @return 
	 */
	public  List<ClaseVO> listarDirectorio(File directorio, boolean validarCopia){

		File[] ficheros = directorio.listFiles();
		ClaseVO claseVo;

		for (File file : ficheros){
			if(validarCopia){
				if(file.isDirectory()&&!file.getName().equals(".gc")){
					listarDirectorio(file,validarCopia);
				}else{
					if (file.getName().equals(".gc"))
					{
						File raizCopia = new File(file.getParent()+File.separator+".gc");
						for (File fileCopia : raizCopia.listFiles()) {
							if (evaluarExtencion(fileCopia,".java")){
								claseVo = calcularClase(fileCopia);
								if (claseVo != null)        
									clases.add(claseVo);
							}
						}
					}
				}
			}
		}
		return clases;
	}

	private boolean evaluarExtencion(File dir, String extension) {
		return dir.getName().endsWith(extension);
	}

	/**
	 * Calcula LOC y numero de metodos de una clase encapsulando los resultados en un Objeto ClaseVo
	 * @param file fichero de texto
	 * @return  ClaseVO objeto con la informacion de conteo de la clase
	 */
	private ClaseVO calcularClase(File file) {
		ClaseVO claseVo = null;
		InputStream is = null;

		try {
			// System.out.println("***************  CLASE : "+file.getName()+"  *************************");
			claseVo = new ClaseVO();
			claseVo.setNombre(file.getName());
			claseVo.setRuta(file.getPath());

			is = new FileInputStream (file);          
			DataInputStream entrada = new DataInputStream(is);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String strLinea;

			while ((strLinea = buffer.readLine()) != null)   {
				evaluarLinea(strLinea,claseVo);     
			}

			entrada.close();


		} catch (Exception ex) {
			Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				is.close();
			} catch (IOException ex) {
				Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return claseVo;
	}


	private void evaluarLinea(String strLinea,ClaseVO claseVo) {

		strLinea = strLinea.trim();


		if(claseVo.getNumeroComentsJdoc() > 0){
			if(strLinea.contains("*/")){  //es un comentario
				claseVo.setNumeroComentsJdoc(0);
			}
		}else if(strLinea.startsWith("//")){
			claseVo.sumarNumeroComentarios(); //es un comentario
		}else if(strLinea.length() == 0){
			claseVo.sumarNumeroEspacios();// es un espacio
		}else if(strLinea.startsWith("/*") && claseVo.getNumeroComentsJdoc() == 0){
			claseVo.sumarNumeroComentsJdoc();// es comentario de java doc
		}else if(strLinea.endsWith("*/")){
			// error de compilacion


		}else if(strLinea.contains("{") && claseVo.getCorchAbiertos() == 0){
			//Inicio de Clase
			claseVo.sumarLinea();
			claseVo.sumarCorchAbiertos();

		}else if(strLinea.contains("}")){
			//FIN de Clase 
			claseVo.sumarLinea();
			claseVo.restarCorchAbiertos();
		}else if(strLinea.contains("{") && claseVo.getCorchAbiertos() == 1){
			claseVo.sumarLinea();
			claseVo.sumarNumeroMetodos();
			claseVo.sumarCorchAbiertos();
		}else{
			claseVo.sumarLinea();
		}


	}
}
