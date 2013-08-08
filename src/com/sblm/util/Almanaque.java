package com.sblm.util;

import java.io.Serializable;
import java.util.Calendar;

public class Almanaque implements Serializable {

	private static final long serialVersionUID = -310383516571744171L;
	private final static String[] mes; 	
	private int idmes;
	private String nombreMes;
	

	
	static {  
        mes = new String[12];  
        mes[0] = "Enero";  
        mes[1] = "Febrero";  
        mes[2] = "Marzo";  
        mes[3] = "Abril";  
        mes[4] = "Mayo";  
        mes[5] = "Junio";  
        mes[6] = "Julio";  
        mes[7] = "Agosto";  
        mes[8] = "Septiembre";  
        mes[9] = "Octubre";  
        mes[10] = "Noviembre";  
        mes[11] = "Diciembre";  
 }
	
	public String obtenerMesActual(){
		Calendar fecha = Calendar.getInstance();
		
		return mes[fecha.getTime().getMonth()];
		
	}
	public String obtenerNombreMes(int nroMes){
		
		return mes[nroMes];
		
		
	}
	
	public static String[] getMes() {
		return mes;
	}


	public String getNombreMes() {
		return nombreMes;
	}

	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}

	public int getIdmes() {
		return idmes;
	}

	public void setIdmes(int idmes) {
		this.idmes = idmes;
	}
	
	

}
