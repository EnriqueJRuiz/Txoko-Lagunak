package org.lagunak.txoko.service;

import java.util.List;

import javax.annotation.Resource;

import org.lagunak.txoko.persisence.Usuario;
import org.lagunak.txoko.service.interfaces.UsuarioService;
import org.lagunak.txoko.usuario.UsuarioServiceRemote;

public class UsuarioServiceImp implements UsuarioService {

	@Resource(name="usuarioServiceRemote")
	private UsuarioServiceRemote usuarioServiceRemote;
	
	@Override
	public void setUsuarioServiceRemote(UsuarioServiceRemote usuarioService) {
		this.usuarioServiceRemote=usuarioService;
		
	}

	@Override
	public List<Usuario> getAll() {
		return usuarioServiceRemote.getAll();
	}

	@Override
	public Usuario getById(long codigo) {
		return usuarioServiceRemote.getById(codigo);
	}

	@Override
	public Usuario update(Usuario usuario) {
		return usuarioServiceRemote.update(usuario);
	}

	@Override
	public Usuario create(Usuario usuario) {
		return usuarioServiceRemote.create(usuario);
	}

	@Override
	public void delete(long codigo) {
		usuarioServiceRemote.delete(codigo);
	}

}
