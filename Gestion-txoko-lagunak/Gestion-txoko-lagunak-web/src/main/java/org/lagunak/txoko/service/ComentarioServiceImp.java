package org.lagunak.txoko.service;

import java.util.List;

import javax.annotation.Resource;

import org.lagunak.txoko.comentario.ComentarioServiceRemote;
import org.lagunak.txoko.persisence.Comentario;
import org.lagunak.txoko.service.interfaces.ComentarioService;

public class ComentarioServiceImp implements ComentarioService {

	@Resource(name="comentarioServiceRemote")
	private ComentarioServiceRemote comentarioServiceRemote;
	
	@Override
	public void setComentarioServiceRemote(ComentarioServiceRemote comentarioService) {
		this.comentarioServiceRemote = comentarioService;
		
	}

	@Override
	public List<Comentario> getAll() {
		return comentarioServiceRemote.getAll();
	}

	@Override
	public Comentario getById(long codigo) {
		return comentarioServiceRemote.getById(codigo);
	}

	@Override
	public Comentario update(Comentario comentario) {
		return comentarioServiceRemote.update(comentario);
	}

	@Override
	public Comentario create(Comentario comentario) {
		return comentarioServiceRemote.create(comentario);
	}

	@Override
	public void delete(long codigo) {
		comentarioServiceRemote.delete(codigo);
	}

}
