package fes.aragon.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fes.aragon.modelo.Imagen;
import fes.aragon.modelo.Usuario;

public class Conexion {
	DataSource ds;

	public Conexion() throws NamingException {
		InitialContext contexto = new InitialContext();
		ds = (DataSource) contexto.lookup("jdbc/mysql");
	}

	public Usuario existe(String usuario, String clave) throws Exception {
		Usuario datos = null;
		if (ds == null) {
			throw new Exception("No hay conexion a la base de datos");
		} else {
			Connection cnn = ds.getConnection();
			if (cnn == null) {
				throw new Exception("No hay conexion a la base de datos");
			} else {
				try {
					PreparedStatement loginQuery = cnn
							.prepareStatement("select * from usuario where usuario = ? and clave = ?");
					loginQuery.setString(1, usuario);
					loginQuery.setString(2, clave);
					ResultSet resultado = loginQuery.executeQuery();
					if (resultado.next()) {
						datos = new Usuario();
						datos.setId_usuario(resultado.getInt(1));
						datos.setUsuario(resultado.getString(2));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					cnn.close();
				}
			}
		}
		return datos;
	}

	public boolean registrar(String usuario, String clave) throws Exception {
		if (ds == null) {
			throw new Exception("No hay conexion a la base de datos");
		} else {
			Connection cnn = ds.getConnection();
			if (cnn == null) {
				throw new Exception("No hay conexion a la base de datos");
			} else {
				try {
					PreparedStatement RegisterQuery = cnn
							.prepareStatement("INSERT INTO usuario(usuario,clave) values(?,?)");
					RegisterQuery.setString(1, usuario);
					RegisterQuery.setString(2, clave);
					if (RegisterQuery.executeUpdate() == 1) {
						return true;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					cnn.close();
				}
			}
		}
		return false;
	}

	public void agregarImagen(String rutaImagenes, int id_usuario) throws Exception {
		if (ds == null) {
			throw new Exception("No hay conexion a la base de datos");
		} else {
			Connection cnn = ds.getConnection();
			if (cnn == null) {
				throw new Exception("No hay conexion a la base de datos");
			} else {
				try {
					PreparedStatement addQuery = cnn
							.prepareStatement("INSERT INTO imagen(rutaImagenes,id_usuario) values(?,?)");
					addQuery.setString(1, rutaImagenes);
					addQuery.setInt(2, id_usuario);
					if (addQuery.executeUpdate() == 1) {
						ResultSet resultado = addQuery.executeQuery();
						Imagen imagen = new Imagen();
						imagen.setRutaImagenes(resultado.getString(1));
						imagen.setId_usuario(resultado.getInt(2));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					cnn.close();
				}
			}
		}
	}

	public List<String> sacarRutas(int id_usuario) throws Exception {
		List<String> rutas = new ArrayList<>();
		if (ds == null) {
			throw new Exception("No hay conexion a la base de datos");
		} else {
			Connection cnn = ds.getConnection();
			if (cnn == null) {
				throw new Exception("No hay conexion a la base de datos");
			} else {
				try {
					PreparedStatement sacarQuery = cnn
							.prepareStatement("SELECT rutaImagenes from imagen WHERE id_usuario="+id_usuario);
					ResultSet resultado = sacarQuery.executeQuery();
					if (resultado.next()) {
						/*while (resultado.next()) {
							rutas.add(resultado.getString(1));
						}
						*/
						do {
							rutas.add(resultado.getString(1));
						} while (resultado.next());
					} else {
						FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("No hay rutas"));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					cnn.close();
				}
			}
		}
		return rutas;
	}

}