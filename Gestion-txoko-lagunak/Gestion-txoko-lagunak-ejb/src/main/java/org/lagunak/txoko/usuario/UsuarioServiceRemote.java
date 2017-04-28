package org.lagunak.txoko.usuario;

import java.util.List;

import javax.ejb.Remote;

import org.lagunak.txoko.persisence.Usuario;

@Remote
public interface UsuarioServiceRemote {
	
	public List<Usuario> getAll();
	
	public Usuario getById(long codigo);
	
	public Usuario update(Usuario usuario);
	
	public Usuario create(Usuario usuario);
	
	public void delete (long codigo);
}
