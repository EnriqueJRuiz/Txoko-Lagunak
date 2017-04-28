package org.lagunak.txoko.service.interfaces;

import java.util.List;

import org.lagunak.txoko.persisence.Usuario;
import org.lagunak.txoko.usuario.UsuarioServiceRemote;

public interface UsuarioService {
	
	public void setUsuarioServiceRemote(UsuarioServiceRemote usuarioService);
	
	public List<Usuario> getAll();
	
	public Usuario getById(long codigo);
	
	public Usuario update(Usuario usuario);
	
	public Usuario create(Usuario usuario);
	
	public void delete (long codigo);
}
