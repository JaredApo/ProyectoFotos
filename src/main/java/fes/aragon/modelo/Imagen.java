package fes.aragon.modelo;

import java.io.Serializable;

public class Imagen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_imagen;
	private int id_usuario;
	private String rutaImagenes;
	
	public Imagen() {
		// TODO Auto-generated constructor stub
	}

	public Imagen(int id_usuario, int id_imagen, String rutaImagenes) {
		super();
		this.id_usuario = id_usuario;
		this.rutaImagenes = rutaImagenes;
		this.id_imagen = id_imagen;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getRutaImagenes() {
		return rutaImagenes;
	}

	public void setRutaImagenes(String rutaImagenes) {
		this.rutaImagenes = rutaImagenes;
	}

	public int getId_imagen() {
		return id_imagen;
	}

	public void setId_imagen(int id_imagen) {
		this.id_imagen = id_imagen;
	}

}