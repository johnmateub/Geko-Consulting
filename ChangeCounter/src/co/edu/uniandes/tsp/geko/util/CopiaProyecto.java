/**
 * 
 */
package co.edu.uniandes.tsp.geko.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author John
 *
 */
public class CopiaProyecto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//("D:\\desarrollo\\Mateus Bello\\Test\\desarrollo\\src\\Test", "D:\\desarrollo\\NEW");
			copyProjectIntoDirectory("D:\\desarrollo\\Mateus Bello\\Test\\desarrollo\\src\\Test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Método encargado de copiar todo un directorio en uno nuevo
	 * @param path_p, Path del directorio a copiar
	 * @param newPath_p, Path del nuevo directorio
	 * @throws Exception
	 */
	private static void copyProjectNewDirectory(String path_p, String newPath_p) throws Exception
	{
		File source_m = new File(path_p);
		File destiny_m = new File(newPath_p);
		
		if (!source_m.exists())
			throw new Exception("Proyecto no existe");
		else
		{
			try
			{
	        	copyDirectory(source_m,destiny_m);
	        }catch(IOException e){
	        	e.printStackTrace();
	        	//error, just exit
	            System.exit(0);
	        }
		}
	}
	
	/**
	 * Copia el contenido de un directorio origen a uno destino
	 * @param src_p
	 * @param dest_p
	 * @throws IOException
	 */
	public static void copyDirectory(File src_p, File dest_p)throws IOException{

		if(src_p.isDirectory()){
	
			//Si no existe directorio se crea
			if(!dest_p.exists())
			   dest_p.mkdir();
			
			//Obtener el contenido del directorio
			String files_m[] = src_p.list();
	
			for (String file : files_m) {
			   File srcFile_m = new File(src_p, file);
			   File destFile_m = new File(dest_p, file);
			   copyDirectory(srcFile_m,destFile_m);
			}
		}
		else
		{
			InputStream in = new FileInputStream(src_p);
			OutputStream out = new FileOutputStream(dest_p); 
	
		    byte[] buffer = new byte[1024];
	
		    int length;
		    //Copiar archivo en Bytes
		    while ((length = in.read(buffer)) > 0){
		    	out.write(buffer, 0, length);
		    }
	
		    in.close();
		    out.close();
		}
	}
	
	public static void copyProjectIntoDirectory(String path_p) throws Exception
	{
		File source_m = new File(path_p);
		
		if (!source_m.exists())
			throw new Exception("Proyecto no existe");
		else
		{
			try
			{
	        	copySelfDirectory(source_m);
	        }catch(IOException e){
	        	e.printStackTrace();
	        	//error, just exit
	            System.exit(0);
	        }
		}
	}
	
	/**
	 * Copia el contenido de un directorio origen en un directorio temporal dentro del mismo directorio
	 * @param src_p
	 * @param dest_p
	 * @throws IOException
	 */
	public static void copySelfDirectory(File src_p)throws IOException{

		//Verifica que el directorio no sea copia existente
		if(src_p.isDirectory() && src_p.getName().compareTo(".gc") != 0)
		{
			//Obtener el contenido del directorio
			String files_m[] = src_p.list();
	
			for (String file : files_m) {
			   File srcFile_m = new File(src_p, file);
			   copySelfDirectory(srcFile_m);
			}
		}
		else
		{
			String tempDirectory_m = src_p.getParent()+"\\.gc";
			File temp_m = new File (tempDirectory_m);
			//Si no existe directorio se crea
			if(!temp_m.exists())
			   temp_m.mkdir();
		
			
			InputStream in = new FileInputStream(src_p);
			temp_m = new File (tempDirectory_m+"\\"+src_p.getName());
			OutputStream out = new FileOutputStream(temp_m); 
	
		    byte[] buffer = new byte[1024];
	
		    int length;
		    //Copiar archivo en Bytes
		    while ((length = in.read(buffer)) > 0){
		    	out.write(buffer, 0, length);
		    }
	
		    in.close();
		    out.close();
		}
	}
}
