package fes.aragon.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import fes.aragon.conexion.Conexion;
import fes.aragon.modelo.Usuario;
import fes.aragon.utilerias.Utilerias;

@Named("Imagenes")
@ViewScoped
public class Imagenes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> rutaImagenes = new ArrayList<>();
	private List<StreamedContent> imgs = new ArrayList<>();
	private int id_usuario = ((Usuario) Utilerias.getManagedBean("usuario", Usuario.class)).getId_usuario();
	private StreamedContent imagen;
	private StreamedContent bajar;

	@PostConstruct
	public void inicio() {
		try {
			Conexion consulta = new Conexion();
			rutaImagenes = consulta.sacarRutas(id_usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < rutaImagenes.size(); i++) {
			String dato = rutaImagenes.get(i);
			StreamedContent imagen = DefaultStreamedContent.builder().contentType("image/jpeg").stream(() -> {
				try {
					return new FileInputStream(dato);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}).build();
			imgs.add(imagen);
		}
	}

	public Imagenes() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getRutaImagenes() {
		return rutaImagenes;
	}

	public void setRutaImagenes(List<String> rutaImagenes) {
		this.rutaImagenes = rutaImagenes;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public List<StreamedContent> getImgs() {
		return imgs;
	}

	public void setImgs(List<StreamedContent> imgs) {
		this.imgs = imgs;
	}

	public StreamedContent getImagen() {
		return imagen;
	}

	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

	public StreamedContent getBajar() {
		return bajar;
	}

	public void setBajar(StreamedContent bajar) {
		this.bajar = bajar;
	}

	public StreamedContent descargaImagen(String ruta) {
		bajar = DefaultStreamedContent.builder().contentType("image/jpeg").name("Imagen.jpg").stream(() -> {
			try {
				return new FileInputStream(ruta);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).build();

		return bajar;
	}
	
	public void mandaImagen(String ruta) {
		if(ruta != null) {
			Utilerias.setManagedBeanInSession("archivo", ruta);
			imagen = DefaultStreamedContent.builder().contentType("image/jpeg").stream(() -> {
				try {
					return new FileInputStream(ruta);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return null;
			}).build();
		}
		else {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("ha ocurrido un error"));
		}
		
	}
	
}
