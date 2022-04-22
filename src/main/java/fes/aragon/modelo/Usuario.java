package fes.aragon.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_usuario;
	private String usuario;
	private String sesionNavegdor;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSesionNavegdor() {
		return sesionNavegdor;
	}

	public void setSesionNavegdor(String sesionNavegdor) {
		this.sesionNavegdor = sesionNavegdor;
	}

	public String cerrarSesion() {		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}
	
}