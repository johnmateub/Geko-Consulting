package co.edu.uniandes.tsp.geko.vo;

import java.util.ArrayList;
import java.util.List;

public class Parte{

	private String nombreParte;
	private int contadorItems;
	private int contadorLOC;
	private int cantidadLineasAgregadas;
	private int cantidadLineasModificadas;
	private int cantidadLineasBorradas;
	private List<String> lineasAgregadas;
	private List<String> lineasModificadas;
	private List<String> lineasEliminadas;
	
	public Parte() {
		lineasAgregadas = new ArrayList<String>();
		lineasModificadas = new ArrayList<String>();
		lineasEliminadas = new ArrayList<String>();
	}
	
	public int getCantidadLineasAgregadas() {
		return cantidadLineasAgregadas;
	}
	public void setCantidadLineasAgregadas(int cantidadLineasAgregadas) {
		this.cantidadLineasAgregadas = cantidadLineasAgregadas;
	}
	public int getCantidadLineasModificadas() {
		return cantidadLineasModificadas;
	}
	public void setCantidadLineasModificadas(int cantidadLineasModificadas) {
		this.cantidadLineasModificadas = cantidadLineasModificadas;
	}
	public int getCantidadLineasBorradas() {
		return cantidadLineasBorradas;
	}
	public void setCantidadLineasBorradas(int cantidadLineasBorradas) {
		this.cantidadLineasBorradas = cantidadLineasBorradas;
	}
	public List<String> getLineasAgregadas() {
		return lineasAgregadas;
	}
	public void setLineasAgregadas(List<String> lineasAgregadas) {
		this.lineasAgregadas = lineasAgregadas;
	}
	public List<String> getLineasModificadas() {
		return lineasModificadas;
	}
	public void setLineasModificadas(List<String> lineasModificadas) {
		this.lineasModificadas = lineasModificadas;
	}
	public List<String> getLineasEliminadas() {
		return lineasEliminadas;
	}
	public void setLineasEliminadas(List<String> lineasEliminadas) {
		this.lineasEliminadas = lineasEliminadas;
	}
	public String getNombreParte() {
		return nombreParte;
	}
	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}
	public int getContadorItems() {
		return contadorItems;
	}
	public void setContadorItems(int contadorItems) {
		this.contadorItems = contadorItems;
	}
	public int getContadorLOC() {
		return contadorLOC;
	}
	public void setContadorLOC(int contadorLOC) {
		this.contadorLOC = contadorLOC;
	}
	
}
