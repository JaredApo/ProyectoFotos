package fes.aragon.paginas.etiquetas;

import java.io.File;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

@Named(value = "CrearArchivoBean")
@RequestScoped
public class CrearArchivos {
	private File carpeta;
	
	public CrearArchivos() {
		// TODO Auto-generated constructor stub
	}

	public File getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(File carpeta) {
		this.carpeta = carpeta;
	}
	
	public void crearCapeta(String usuario) {
		carpeta = new File("C:\\" + usuario);
		if(carpeta.mkdir()) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Carpeta creada con exito"));
			PrimeFaces.current().executeScript("PF('dlg').hide()");
		}else {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("No fue posible crear los archivos necesarios"));
			PrimeFaces.current().executeScript("PF('dlg').hide()");
		}
		
	}
	
}