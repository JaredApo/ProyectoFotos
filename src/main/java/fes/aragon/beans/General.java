package fes.aragon.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fes.aragon.modelo.Usuario;
import fes.aragon.utilerias.Utilerias;

@Named("verificar")
@ApplicationScoped
public class General {
	public void verificarSesion() {

		Usuario usr = (Usuario) Utilerias.getManagedBean("usuario", Usuario.class);
		if (usr == null) {
			ConfigurableNavigationHandler salto = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance()
					.getApplication().getNavigationHandler();
			salto.performNavigation("/index?faces-redirect=true");
		}
	}
}